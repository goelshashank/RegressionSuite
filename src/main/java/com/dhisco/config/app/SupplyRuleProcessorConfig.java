package com.dhisco.config.app;

import com.dhisco.config.BaseConfig;
import com.dhisco.util.RemoteConnector;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Lazy
@Getter @Setter @ToString(includeFieldNames=true)  @Log4j2
@Configuration @PropertySource("supply-rule-processor.properties") @ConfigurationProperties(prefix = "srp")  public class SupplyRuleProcessorConfig
		extends BaseConfig {

	private String host;
	private String port;
	private String startServCommand;
	private String applicationId;

	@PostConstruct public void init() {
		super.init();
		remoteConnector.addSession(host);
		startProcess();
	}

	@Override public void afterPropertiesSet() throws Exception {
	}

	@PreDestroy public void cleanup() {
		stopProcess();
		super.cleanup();
	}

}
