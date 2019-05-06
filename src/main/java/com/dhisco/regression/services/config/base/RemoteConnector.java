package com.dhisco.regression.services.config.base;

import com.dhisco.regression.core.exceptions.P2DRSException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static com.dhisco.regression.core.util.CommonUtils.isEmpty;
import static com.dhisco.regression.core.util.CommonUtils.isNotEmpty;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 29-03-2019
 */
@Component @Log4j2 @Getter public class RemoteConnector {

	private static final Integer RETRY_COUNT = 2;
	@Autowired ApplicationConfig applicationConfig;
	//todo: make it not available to any other class except configuration
	private Map<String, Session> sessionMap;

	public void enablePortForwarding(int localPort, String host, int remotePort) {
		try {
			int assinged_port = sessionMap.get(host).setPortForwardingL(localPort, host, remotePort);
			log.info("localhost:" + assinged_port + " -> " + host + ":" + remotePort);
			log.info("Port Forwarded");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void addSession(String host) {

		if (isEmpty(host)  || ( isNotEmpty(sessionMap.get(host)) && sessionMap.get(host).isConnected())) {
			return;
		}

		synchronized (host) {
			if (isNotEmpty(sessionMap.get(host)) && sessionMap.get(host).isConnected()) {
				return;
			}

			JSch jsch = new JSch();

			Session session = null;
			try {
				session = jsch.getSession(applicationConfig.getSshUser(), host, 22);
				session.setPassword(applicationConfig.getSshPassword());
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				config.put("PreferredAuthentications", "password");
				session.setConfig(config);
				int retry = 1;
				while (retry <= RETRY_COUNT) {
					try {
						session.connect();
					} catch (Exception e) {
						if (e instanceof ConnectException)
							log.error(e.getMessage(), e);
					}
					if (session.isConnected())
						break;
					retry++;
				}
				sessionMap.put(host, session);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				if (isNotEmpty(session) && session.isConnected())
					session.disconnect();
			}
		}
	}

	public void destroySession(String host) {
		if (isEmpty(host) || isEmpty(sessionMap) || isEmpty(sessionMap.get(host)) || !sessionMap.get(host)
				.isConnected())
			return;

		sessionMap.get(host).disconnect();
	}

	@PostConstruct public void init() {
		sessionMap = new ConcurrentHashMap<>();
	}

	public Map<String, String> executeSSHCommands(String host, List<String> commands) {
		Map<String, String> outBuffer = new HashMap<>();
		Session session = sessionMap.get(host);

		if (isEmpty(session) || !session.isConnected()) {
			log.info("session is not there for host: " + host);
			return null;
		}

		for (String command : commands) {
			ChannelExec channel = null;
			InputStream err = null;
			try {
				executeCommand(host, command);
			} catch (Exception e) {
				//log.error(e.getMessage(), e);
			} finally {
				if (isNotEmpty(channel) && channel.isConnected())
					channel.disconnect();
			}

		}

		return outBuffer;
	}

	private void executeCommand(String host, String command) throws JSchException, IOException {
		log.info("Running command- {}",command);
		ChannelExec channel = (ChannelExec) sessionMap.get(host).openChannel("exec");
		channel.setCommand(command);
		channel.connect();
		int sleepCount = 0;
		// Sleep for up to 5 seconds while we wait for the command to execute, checking every 100 milliseconds
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				log.warn("Interrupted exception while waiting for command {} to finish {}",command, e);
			}
		} while (!channel.isClosed() && sleepCount++ < 50);

		int res = channel.getExitStatus();
		if (res != 0) {
			log.debug("Error: Channel is not closed yet for command- {} and exit status- {}", command, res);
			throw new IOException("error in executing command " + res);
		}
		channel.disconnect();
		log.debug("Channel is closed for command- {} and exit status- {}", command, res);
	}

	public void copyRemoteToLocal(String host, String from, String to, String fileName) {

		try {
			Session session = sessionMap.get(host);
			from = from + "/" + fileName;
			String prefix = null;

			if (new File(to).isDirectory()) {
				prefix = to + File.separator;
			}

			// exec 'scp -f rfile' remotely
			String command = "scp -f " + from;
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			byte[] buf = new byte[1024];

			// send '\0'
			buf[0] = 0;
			out.write(buf, 0, 1);
			out.flush();

			while (true) {
				int c = checkAck(in);
				if (c != 'C') {
					break;
				}

				// read '0644 '
				in.read(buf, 0, 5);

				long filesize = 0L;
				while (true) {
					if (in.read(buf, 0, 1) < 0) {
						// error
						break;
					}
					if (buf[0] == ' ')
						break;
					filesize = filesize * 10L + (long) (buf[0] - '0');
				}

				String file = null;
				for (int i = 0; ; i++) {
					in.read(buf, i, 1);
					if (buf[i] == (byte) 0x0a) {
						file = new String(buf, 0, i);
						break;
					}
				}

				log.info("file-size=" + filesize + ", file=" + file);

				// send '\0'
				buf[0] = 0;
				out.write(buf, 0, 1);
				out.flush();

				// read a content of lfile
				FileOutputStream fos = new FileOutputStream(prefix == null ? to : prefix + file);
				int foo;
				while (true) {
					if (buf.length < filesize)
						foo = buf.length;
					else
						foo = (int) filesize;
					foo = in.read(buf, 0, foo);
					if (foo < 0) {
						// error
						break;
					}
					fos.write(buf, 0, foo);
					fos.flush();
					filesize -= foo;
					if (filesize == 0L)
						break;
				}

				if (checkAck(in) != 0) {
					throw new P2DRSException("Exception in copy from Remote to  Local");
				}

				// send '\0'
				buf[0] = 0;
				out.write(buf, 0, 1);
				out.flush();
				log.info("Content has been flushed");

				try {

					if (isNotEmpty(out)) {
						out.flush();
						out.close();
					}

					if (fos != null) {
						fos.flush();
						fos.close();
					}

					log.info("copy remote to local end");
				} catch (Exception ex) {
					log.info(ex);
				}
			}

			channel.disconnect();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void copyLocalToRemote(Session session, String from, String to, String fileName) {
		try {
			boolean ptimestamp = true;
			from = from + File.separator + fileName;

			// exec 'scp -t rfile' remotely
			String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + to;
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);

			// get I/O streams for remote scp
			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();

			channel.connect();

			if (checkAck(in) != 0) {
				throw new P2DRSException("Exception in copy from Local To Remote");
			}

			File _lfile = new File(from);

			if (ptimestamp) {
				command = "T" + (_lfile.lastModified() / 1000) + " 0";
				// The access time should be sent here,
				// but it is not accessible with JavaAPI ;-<
				command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
				out.write(command.getBytes());
				out.flush();
				if (checkAck(in) != 0) {
					throw new P2DRSException("Exception in copy from Local To Remote");
				}
			}

			// send "C0644 filesize filename", where filename should not include '/'
			long filesize = _lfile.length();
			command = "C0644 " + filesize + " ";
			if (from.lastIndexOf('/') > 0) {
				command += from.substring(from.lastIndexOf('/') + 1);
			} else {
				command += from;
			}

			command += "\n";
			out.write(command.getBytes());
			out.flush();

			if (checkAck(in) != 0) {
				throw new P2DRSException("Exception in copy from Local To Remote");
			}

			// send a content of lfile
			FileInputStream fis = new FileInputStream(from);
			byte[] buf = new byte[1024];
			while (true) {
				int len = fis.read(buf, 0, buf.length);
				if (len <= 0)
					break;
				out.write(buf, 0, len);
				out.flush();
			}

			// send '\0'
			buf[0] = 0;
			out.write(buf, 0, 1);

			if (checkAck(in) != 0) {
				throw new P2DRSException("Exception in copy from Local To Remote");
			}
			//out.close();

			try {
				if (isNotEmpty(out)) {
					out.flush();
					out.close();
				}

				if (fis != null)
					fis.close();
			} catch (Exception ex) {
				log.info(ex);
			}

			channel.disconnect();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public int checkAck(InputStream in) throws IOException {
		int b = in.read();
		// b may be 0 for success,
		//          1 for error,
		//          2 for fatal error,
		//         -1
		if (b == 0)
			return b;
		if (b == -1)
			return b;

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				log.info(sb.toString());
			}
			if (b == 2) { // fatal error
				log.info(sb.toString());
			}
		}
		return b;
	}
}




