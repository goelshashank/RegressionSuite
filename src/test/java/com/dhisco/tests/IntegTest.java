package com.dhisco.tests;

import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import lombok.extern.log4j.Log4j2;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2
 public class IntegTest extends BaseTest {

	@BeforeTest
	@Override
	@Parameters({ "OS", "browser" })
	public void setup(String OS, String browser) {
		super.setup(OS,browser);
	}

	@AfterTest
	@Override
	public void tearDown() {
		super.tearDown();
	}

	@AfterMethod
	@Override public void afterMethod(ITestResult result) {
		super.afterMethod(result);
	}

	@Override @BeforeMethod public void beforeMethod() throws Exception{

		super.beforeMethod();
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

	//@Test(dataProviderClass = DataProvider1.class, dataProvider = "testArgs")
	@Test public void integTest() throws Exception {
		test = extent.createTest("Integration Test", "Integration Test");

		kafkaConfig.publishData("VS_Brand_2_test",asList(getResource("/data/ari3.json")));

		ConfigurationServiceConfig configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(10);

		SupplyRuleProcessorConfig supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		ChannelMessageProcessorConfig channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);
		sleep(sleepTime);

		channelMessageProcessorConfig.copyRemoteToLocal("/apps/test/regression/out","C:\\Users\\shashank"
				+ ".goel\\IdeaProjects\\P2DRegressionSuite\\src\\test\\resources\\out","out2.json");

		assertJson("/benchmark/out1.json","/out/out2.json", JSONCompareMode.STRICT);

		log.debug("%%%%%%%%%%% end test %%%%%%%%%%%");

	}





}
