package com.dhisco.regression.services;

import com.dhisco.regression.core.LogTime;
import com.dhisco.regression.services.config.base.BaseConfig;
import com.dhisco.regression.core.interceptors.ExceptionInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Log4j2
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = CassandraDataAutoConfiguration.class) @EnableConfigurationProperties @PropertySource("server"
		+ ".properties") @ComponentScan("com.dhisco") public class ManageConfigurations extends
		SpringBootServletInitializer implements
		ServletContextListener, ExceptionInterceptor {

	public static void main(String[] args) {
		SpringApplication.run(ManageConfigurations.class, args);
	}


	@Autowired AbstractApplicationContext applicationContext;

	@Override
	public void contextInitialized(
			ServletContextEvent sce) {
		log.info("context initialised");
	}

	@Override
	public void contextDestroyed(
			ServletContextEvent sce) {
		log.info("context shutdown");
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
