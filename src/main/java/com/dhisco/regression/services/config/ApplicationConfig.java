package com.dhisco.regression.services.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 29-03-2019
 */
@Log4j2 @ToString(includeFieldNames = true) @Getter @Setter @Configuration @PropertySource("application.properties") @ConfigurationProperties(prefix = "app")  public class ApplicationConfig
		extends BaseConfig {

	private String sshUser;
	private String sshPassword;

}
