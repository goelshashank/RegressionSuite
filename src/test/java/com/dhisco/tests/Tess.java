package com.dhisco.tests;

import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.DbConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import lombok.extern.log4j.Log4j2;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2
 public class Tess extends BaseTest {

	@BeforeClass public void setupCl() {

		log.info("dasfasf");
		log.debug("dasfaaaaaaaaaaaasf");
	}

	@Override @BeforeMethod public void setUp() throws Exception{

		super.setUp();
		log.debug("%%%%%%%%%%% Loading DB %%%%%%%%%%%%%%%");
	/*	dbConfig = loadBean(DbConfig.class);
		dbConfig.executeCommand("drop database if exists "+dbConfig.getMariaTestDb());
		dbConfig.executeCommand("create database if not exists "+dbConfig.getMariaTestDb());
		dbConfig.executeScript(getResource("/scripts/test2.sql"));*/
		//sleep(400);
		kafkaConfig = loadBean(KafkaConfig.class);
		String[] list={"VS_Brand_2_test","M4_Brand_topic_test","RoyalArabians_test","BookingDotCom2_test"};
		asList(list).forEach(t->kafkaConfig.deleteTopic(t));
		sleep(5);
		asList(list).forEach(t->kafkaConfig.createTopic(t));
	}

	@Override @AfterMethod public void tearDown() {
		super.tearDown();
	}

	//@Test(dataProviderClass = DataProvider1.class, dataProvider = "testArgs")
	@Test public void tess() throws Exception {

		kafkaConfig.publishData("VS_Brand_2_test",asList(getResource("/data/ari3.json")));

		ConfigurationServiceConfig configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(10);

		SupplyRuleProcessorConfig supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		ChannelMessageProcessorConfig channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);
		sleep(sleepTime);

		/*channelMessageProcessorConfig.copyRemoteToLocal("/apps/test/regression/out","C:\\Users\\shashank"
				+ ".goel\\IdeaProjects\\P2DRegressionSuite\\src\\test\\resources\\out","out1.json");
		*/log.debug("%%%%%%%%%%% end test %%%%%%%%%%%");

		JSONCompareResult result =
				JSONCompare.compareJSON(getResourceAsString("/out/out1.json"), getResourceAsString("/benchmark/out1"
								+ ".json"),
						JSONCompareMode.STRICT);
		JSONAssert.assertEquals(getResourceAsString("/out/out1.json"), getResourceAsString("/benchmark/out1"
				+ ".json"),	JSONCompareMode.STRICT);


	}





}
