package com.dhisco.tests;

import com.dhisco.regression.services.config.BaseConfig;
import com.dhisco.regression.services.config.ManageConfigurations;
import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.DbConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import com.dhisco.regression.core.exceptions.P2DRSException;
import com.dhisco.regression.services.RemoteConnector;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.ServletTestExecutionListener;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@TestExecutionListeners(inheritListeners = false, listeners = {
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, ServletTestExecutionListener.class})
@TestPropertySource(properties = {"server.port=8082"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes = ManageConfigurations.class)
@Log4j2 public abstract class BaseTest
		extends AbstractTestNGSpringContextTests {

	@Autowired public ManageConfigurations manageConfigurations;
	@Autowired public RemoteConnector remoteConnector;

	public DbConfig dbConfig;
	public KafkaConfig kafkaConfig;
	public ChannelMessageProcessorConfig channelMessageProcessorConfig;
	public SupplyRuleProcessorConfig supplyRuleProcessorConfig;
	public ConfigurationServiceConfig configurationServiceConfig;

	public void setUp() throws Exception {
	}

	public void tearDown() {
		log.debug("tearing down test");
	}


	public <T> T loadBean(Class<T> type) {
		return manageConfigurations.loadBean(type);
	}

	public void startProcesses(List<BaseConfig> processes) {
		processes.stream().forEach(t -> {
			try {
				t.startProcess();
			} catch (P2DRSException e) {
				log.error(e.getMessage(), e);
			}
		});
	}

	public void stopProcesses(List<BaseConfig> processes) {
		processes.stream().forEach(t -> {
			try {
				t.stopProcess();
			} catch (P2DRSException e) {
				log.error(e.getMessage(), e);
			}
		});
	}

	public void sleep(int seconds) throws InterruptedException {
		log.debug("sleeping for {} seconds",seconds);
		TimeUnit.SECONDS.sleep(seconds);
	}

	public InputStream getResource(String relativePath){
		return  getClass().getResourceAsStream(relativePath);
	}
}
