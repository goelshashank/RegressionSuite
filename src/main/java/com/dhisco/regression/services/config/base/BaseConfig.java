package com.dhisco.regression.services.config.base;

import com.dhisco.regression.core.BasePojo;
import com.dhisco.regression.core.exceptions.P2DRSException;
import com.dhisco.regression.services.RemoteConnector;
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

import static com.dhisco.regression.core.util.CommonUtils.isEmpty;
import static com.dhisco.regression.core.util.CommonUtils.isNotEmpty;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Log4j2 @Getter @Setter @ToString(includeFieldNames = true) public abstract class BaseConfig extends BasePojo
		implements InitializingBean,ExceptionInterceptor {

	private final String className = this.getClass().getSimpleName().substring(0,
			this.getClass().getSimpleName().indexOf("$")<=0?this.getClass().getSimpleName().length():
					this.getClass().getSimpleName().indexOf("$"));

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


	public void copyRemoteToLocal(String from, String to, String fileName) {
		remoteConnector.copyRemoteToLocal(getHost(),from,to, fileName);
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

	public void stopProcess() throws P2DRSException {
		/*String methodName = className + "." + "stopProcess ";
		log.info(methodName);
		if (isEmpty(getPort())) {
			log.info(methodName + "port is null, {}");
			return;
		}

		int i = 0;
		String processid=null;
		while (i < 5) {
			if (isProcessDown()) {
				log.info(methodName + "process is down , {}");
				return;
			} else {
				processid = getProcessID();
				if (isNotEmpty(processid)) {
					log.info(methodName + "killing process with pid , {}", processid);
					executeSSHCommands(Arrays.asList("kill -9 " + processid));
				}
				sleep(2);
				i++;
			}
		}

		throw new P2DRSException(methodName + "process can not be stopped with pid "+ processid);*/

		int i=0;
		while (i<2) {
			executeSSHCommands(Arrays.asList("kill -9  $(pgrep -f 8081)"));
			sleep(1);
			i++;
		}

	}

	public void startProcess() throws P2DRSException{
		log.info("Starting Process: {}",className);
	/*	String methodName = className + "." + "startProcess ";
		try {
			stopProcess();
		} catch (P2DRSException e) {
			log.error(methodName+e.getMessage(), e);
			return;
		}
		int i = 0;
		while (i < 3) {
			if (!isProcessDown()) {
				log.info(methodName+"process is already started ");
				return;
			} else
				executeSSHCommands(Arrays.asList(getStartServCommand()));
			sleep(5);
			i++;
		}

		throw new P2DRSException(methodName + "process can not be started");*/
		stopProcess();
		executeSSHCommands(Arrays.asList(getStartServCommand()));
	}

	private void sleep(int seconds) {
		log.info(className+" "+"sleeping for seconds {}", seconds);
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	public boolean isProcessDown() {
		String methodName = className + "." + "isProcessDown ";
		String processID = getProcessID();
		if (isEmpty(processID)) {
			log.info(methodName + " process is down with pid {}", processID);
			return true;
		}
		log.info(methodName + " process is not down with pid {}", processID);
		return false;
	}

	private String getProcessID() {
		String methodName = className + "." + "getProcessID ";
		Map outBuffer = executeSSHCommands(Arrays.asList("pgrep -f " + getPort()));
		if (isEmpty(outBuffer))
			return null;

		log.info(methodName + "process id  {}", outBuffer.values().toArray()[0].toString());
		return outBuffer.values().toArray()[0].toString();
	}

}
