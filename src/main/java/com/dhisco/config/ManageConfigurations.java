package com.dhisco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Configuration @EnableConfigurationProperties @PropertySource("application"
		+ ".properties") @ComponentScan("com.dhisco") public class ManageConfigurations implements
		ServletContextListener {

	@Autowired AbstractApplicationContext applicationContext;

	@Override
	public void contextInitialized(
			ServletContextEvent sce) {
		// Context Initialised
		System.out.println("adfsdfd");
	}

	@Override
	public void contextDestroyed(
			ServletContextEvent sce) {
		// Here - what you want to do that context shutdown
		System.out.println("sssssss");
	}

	public <T> T loadBean(Class<T> type){
		return applicationContext.getBean(type);
	}

	public void destroyConfig(BaseConfig baseConfig){
		((DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory()).
				destroySingleton(baseConfig.getClass().getName());
		baseConfig=null;
	}
}
