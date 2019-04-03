package com.dhisco;

import com.dhisco.config.app.ChannelMessageProcessorConfig;
import com.dhisco.config.app.ConfigurationServiceConfig;
import com.dhisco.config.app.SupplyRuleProcessorConfig;
import com.dhisco.config.db.DbConfig;
import com.dhisco.config.db.KafkaConfig;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2 public class Tess extends BaseTest {



	@BeforeClass public void setupCl() {

	}


	@Override @BeforeMethod public void setUp() throws Exception{
		super.setUp();
		dbConfig = loadBean(DbConfig.class);
		kafkaConfig = loadBean(KafkaConfig.class);
		dbConfig.executeScript("C:\\Users\\shashank.goel\\IdeaProjects\\P2DRegressionSuite\\src\\test\\resources"
				+ "\\scripts\\devdump2.sql");
	}

	@Override @AfterMethod public void tearDown() {
		super.tearDown();
	}

	//@Test(dataProviderClass = DataProvider1.class, dataProvider = "testArgs")
	@Test public void tess() throws Exception {

		ConfigurationServiceConfig configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(10);
		SupplyRuleProcessorConfig supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		sleep(10);
		ChannelMessageProcessorConfig channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);
		sleep(2);
		System.out.println("end test");
	}





}
