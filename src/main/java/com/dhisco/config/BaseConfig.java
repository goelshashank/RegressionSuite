package com.dhisco.config;

import com.dhisco.BasePojo;
import com.dhisco.util.RemoteConnector;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dhisco.util.CommonUtils.isEmpty;
import static com.dhisco.util.CommonUtils.isNotEmpty;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Log4j2 @Getter @Setter @ToString(includeFieldNames = true) public abstract class BaseConfig extends BasePojo
		implements InitializingBean {

	@Autowired public RemoteConnector remoteConnector;
	@Autowired public ConfigurableApplicationContext applicationContext;

	public void shutdownApp() {
		applicationContext.registerShutdownHook();
	}

	/**
	 * This method is called first after bean construction.
	 */
	public void init() {
	}

	/**
	 * This method  is called before bean destruction.
	 */
	public void cleanup() {
		remoteConnector.destroySession(getHost());
	}

	/**
	 * This method will be called after {@link BaseConfig#init()}.
	 *
	 * @throws Exception
	 */
	@Override public void afterPropertiesSet() throws Exception {

	}

	public Map<String, String> executeSSHCommands(List<String> commands) {
		return remoteConnector.executeSSHCommands(getHost(), commands);
	}

	public String getHost() {
		return null;
	}

	public String getPort() {
		return null;
	}

	public String getStartServCommand() {
		return null;
	}

	public void stopProcess() {

		/*if (isEmpty(getPort())) {
			log.debug("port is null, {}", this.getClass());
			return;
		}

*//*		int i = 0;
		while (i < 3) {

			if (isProcessDown()) {
				log.debug("process is down , {}  ", this.getClass());
				break;
			} else {*//*
		String processid = getProcessID();
		if (isNotEmpty(processid))
			executeSSHCommands(Arrays.asList("kill -9 " + processid));
			//executeSSHCommands(Arrays.asList("sudo pkill java"));
			sleep(5);
		*//*	i++;
		}*/
	}

	private String getProcessID() {
		Map outBuffer = executeSSHCommands(Arrays.asList("netstat -nlp|grep " + getPort()));
		if (isEmpty(outBuffer))
			return null;
		String str1 = outBuffer.values().toArray()[0].toString();
		String[] str2Arr = str1.split(" ")[str1.split(" ").length - 1].split("/");
		return str2Arr[0];
	}

	public void startProcess() {
		//stopProcess(); //stop previous processes
		//sleep(5);
	/*	int i = 0;
		while (i < 3) {

			if (!isProcessDown()) {
				log.debug("process is already started , {} ", this.getClass());
				break;
			} else*/
		executeSSHCommands(Arrays.asList(getStartServCommand()));
		sleep(5);
		/*	i++;
		}*/
	}

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	public boolean isProcessDown() {

		String processID = getProcessID();
		if (isEmpty(processID)) {
			return true;
		}
		return false;
	}

}
