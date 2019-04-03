package com.dhisco.util;

import com.dhisco.config.ApplicationConfig;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import static com.dhisco.util.CommonUtils.isEmpty;
import static com.dhisco.util.CommonUtils.isNotEmpty;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 29-03-2019
 */
@Component @Log4j2
public class RemoteConnector {

	private static final Integer RETRY_COUNT = 2;
	@Autowired ApplicationConfig applicationConfig;
	//todo: make it not available to any other class except configuration
	private Map<String, Session> map;

	public void enablePortForwarding(int localPort,String host, int remotePort){
		try {
			int assinged_port = map.get(host).setPortForwardingL(localPort, host, remotePort);
			log.debug("localhost:" + assinged_port + " -> " + host + ":" + remotePort);
			log.debug("Port Forwarded");
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
	}

	public void addSession(String host) {

		if (isNotEmpty(map.get(host)) && map.get(host).isConnected()) {
			return;
		}

		synchronized (host) {
			if (isNotEmpty(map.get(host)) && map.get(host).isConnected()) {
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
				map.put(host, session);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				if (isNotEmpty(session) && session.isConnected())
					session.disconnect();
			}
		}
	}

	public void destroySession(String host) {
		if (isEmpty(map.get(host)) || !map.get(host).isConnected()) return;

		map.get(host).disconnect();
	}

	@PostConstruct public void init() {
		map = new ConcurrentHashMap<>();
	}

	public Map<String, String> executeSSHCommands(String host, List<String> commands) {
		Map<String, String> outBuffer = new HashMap<>();
		Session session = map.get(host);

		if (isEmpty(session) || !session.isConnected()) {
			log.debug("session is not there for host: " + host);
			//System.exit(0);
		}

		for (String command : commands) {
			ChannelExec channel = null;
			try {
				channel = (ChannelExec) session.openChannel("exec");
				BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
				channel.setCommand(command);
				channel.connect();

				String msg = null;
				while ((msg = in.readLine()) != null) {
					log.debug(msg);
					if (isNotEmpty(msg))
						outBuffer.put(command, msg);
				}

				channel.disconnect();
			} catch (Exception e) {
				log.debug(e.getMessage(), e);
				if (isNotEmpty(channel) && channel.isConnected())
					channel.disconnect();
			}
		}

		return outBuffer;
	}
}
