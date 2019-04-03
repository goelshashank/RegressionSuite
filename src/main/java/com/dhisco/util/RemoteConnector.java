package com.dhisco.util;

import com.dhisco.config.ApplicationConfig;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.dhisco.util.CommonUtils.isEmpty;
import static com.dhisco.util.CommonUtils.isNotEmpty;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 29-03-2019
 */
@Component @Log4j2 @Getter public class RemoteConnector {

	@Autowired ApplicationContext applicationContext;

	private static final Integer RETRY_COUNT = 2;
	@Autowired ApplicationConfig applicationConfig;
	//todo: make it not available to any other class except configuration
	private Map<String, Session> sessionMap;

	public void enablePortForwarding(int localPort, String host, int remotePort) {
		try {
			int assinged_port = sessionMap.get(host).setPortForwardingL(localPort, host, remotePort);
			log.debug("localhost:" + assinged_port + " -> " + host + ":" + remotePort);
			log.debug("Port Forwarded");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void addSession(String host) {

		if (isNotEmpty(sessionMap.get(host)) && sessionMap.get(host).isConnected()) {
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
		if (isEmpty(host) || isEmpty(sessionMap) || isEmpty(sessionMap.get(host)) || !sessionMap.get(host).isConnected())
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
			log.debug("session is not there for host: " + host);
			return null;
		}

		for (String command : commands) {
			ChannelExec channel = null;
			InputStream err=null;
			try {
				channel = (ChannelExec) session.openChannel("exec");
				BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
				channel.setCommand(command);
				 err=channel.getErrStream();
				channel.connect();


				/*getMessageFromChannel(outBuffer, command, in);*/
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						String msg = null;
						try {
							while ((msg = in.readLine()) != null) {
								log.debug(msg);
								if (isNotEmpty(msg))
									outBuffer.put(command, msg);
							}
						}catch (Exception e){
							log.error(e.getMessage(),e);}
					}
				}, 0, 5*1000);

			} catch (Exception e) {
				log.debug(e.getMessage(), e);
			} finally {
				if (isNotEmpty(channel) && channel.isConnected())
					channel.disconnect();
			}
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(err));
			String errMessage = bufferedReader.lines().collect(Collectors.joining());
			log.debug("channel exit status- {} with error message {}",channel.getExitStatus(),errMessage);

		}

		return outBuffer;
	}

	/*private void getMessageFromChannel(Map<String, String> outBuffer, String command, BufferedReader in)
			throws IOException {

		Timer timer = new Timer();
		timer.schedule( task, 15*1000 );

		System.out.println( "Input a string within 10 seconds: " );
		while ((msg = in.readLine()) != null && timer.()) {
			log.debug(msg);
			if (isNotEmpty(msg))
				outBuffer.put(command, msg);
		}
	}

	TimerTask task = new TimerTask()
	{
		public void run()
		{

		}
	};*/

}


