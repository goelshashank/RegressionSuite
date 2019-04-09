package com.dhisco.regression.services.config;

import com.dhisco.regression.core.LogTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Log4j2
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = CassandraDataAutoConfiguration.class) @EnableConfigurationProperties @PropertySource("application"
		+ ".properties") @ComponentScan("com.dhisco") public class ManageConfigurations extends
		SpringBootServletInitializer implements
		ServletContextListener, ExceptionInterceptor{

	public static void main(String[] args) {
		SpringApplication.run(ManageConfigurations.class, args);
	}


	@Autowired AbstractApplicationContext applicationContext;

	@Override
	public void contextInitialized(
			ServletContextEvent sce) {
		// Context Initialised
		log.debug("adfsdfd");
	}

	@Override
	public void contextDestroyed(
			ServletContextEvent sce) {
		// Here - what you want to do that context shutdown
		log.debug("sssssss");
	}

	@LogTime
	public <T> T loadBean(Class<T> type) {
		return applicationContext.getBean(type);
	}

	public void destroyConfig(BaseConfig baseConfig){
		((DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory()).
				destroySingleton(baseConfig.getClass().getName());
		baseConfig=null;
	}
}
