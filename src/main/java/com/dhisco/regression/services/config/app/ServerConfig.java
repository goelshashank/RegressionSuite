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

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 24-04-2019
 */@Lazy
@Getter @Setter @ToString(includeFieldNames=true)  @Log4j2
@Configuration @PropertySource("server.properties") @ConfigurationProperties(prefix = "server")
public class ServerConfig extends BaseConfig {

	private String host;
	private String port;

	@PostConstruct public void init() {
		super.init();
	}

	@Override public void afterPropertiesSet() throws Exception {
	}


	@PreDestroy public void cleanup() {
		super.cleanup();
	}

}
