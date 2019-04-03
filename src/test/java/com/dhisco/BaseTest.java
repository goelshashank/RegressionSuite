package com.dhisco;

import com.dhisco.config.BaseConfig;
import com.dhisco.config.ManageConfigurations;
import com.dhisco.config.app.ChannelMessageProcessorConfig;
import com.dhisco.config.app.ConfigurationServiceConfig;
import com.dhisco.config.app.SupplyRuleProcessorConfig;
import com.dhisco.config.db.DbConfig;
import com.dhisco.config.db.KafkaConfig;
import com.dhisco.util.RemoteConnector;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@ImportAutoConfiguration(classes = ManageConfigurations.class) @Log4j2 public abstract class BaseTest
		extends AbstractTestNGSpringContextTests {

	@Autowired public ManageConfigurations manageConfigurations;
	@Autowired public RemoteConnector remoteConnector;

	public DbConfig dbConfig;
	public KafkaConfig kafkaConfig;
	public ChannelMessageProcessorConfig channelMessageProcessorConfig;
	public SupplyRuleProcessorConfig supplyRuleProcessorConfig;
	public ConfigurationServiceConfig configurationServiceConfig;

	public void setUp() throws Exception{
	}

	public void tearDown() {
		System.out.println("tearing down test");
	}

	public <T> T loadBean(Class<T> type){
		return manageConfigurations.loadBean(type);
	}

	public void startProcesses(List<BaseConfig> processes) {
		processes.stream().forEach(t -> t.startProcess());
	}

	public void stopProcesses(List<BaseConfig> processes) {
		processes.stream().forEach(t -> t.stopProcess());
	}


	public void sleep(int seconds) throws InterruptedException{
		TimeUnit.SECONDS.sleep(seconds);
	}
}
