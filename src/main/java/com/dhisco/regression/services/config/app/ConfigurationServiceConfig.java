package com.dhisco.regression.services.config.app;

import com.dhisco.regression.core.exceptions.P2DRSException;
import com.dhisco.regression.services.config.base.BaseConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Lazy
@Getter @Setter @ToString(includeFieldNames=true)  @Log4j2
@Configuration @PropertySource("configuration-service.properties") @ConfigurationProperties(prefix = "cfs")  public class ConfigurationServiceConfig
		extends BaseConfig {

	private String port;
	private String debeziumHost;
	private String startServCommand;
	private String host;

	@PostConstruct public void init() {
		super.init();
	}

	@Override public void afterPropertiesSet() throws Exception {
		try {
			startProcess();
		}catch (P2DRSException e){
			log.error(e.getMessage(),e);
		}
	}

	@PreDestroy public void cleanup() {
		try {
			stopProcess();
		}catch (P2DRSException e){
			log.error(e.getMessage(),e);
		}
		super.cleanup();
	}

	public List<String> getStartServCommand() {
		String[] ports=port.split(",");
		List<String> commands=new ArrayList<>();
		for (String s : ports) {
			commands.add(startServCommand.replace("cfs_port",s));
		}
		return	commands;
	}

}
