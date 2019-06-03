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
import java.util.Arrays;
import java.util.List;

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
	private String brandCode;

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
		String[] brandCodes = brandCode.split(",");
		String[] ports = port.split(",");

		List<String> commands = new ArrayList<>();
		int i = 0;
		for (String s : ports) {
			commands.add(startServCommand.replace("srp_brand_code", brandCodes[i]));
			commands.add(startServCommand.replace("srp_port", s));
			i++;
		}
		return commands;
	}
}
