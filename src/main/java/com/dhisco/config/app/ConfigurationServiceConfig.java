package com.dhisco.config.app;

import com.dhisco.P2DRSException;
import com.dhisco.config.BaseConfig;
import com.google.common.base.MoreObjects;
import lombok.Data;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

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
		remoteConnector.addSession(host);
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

}
