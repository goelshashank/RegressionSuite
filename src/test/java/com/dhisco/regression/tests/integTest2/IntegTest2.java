package com.dhisco.regression.tests.integTest2;

import com.dhisco.regression.core.util.CommonUtils;
import com.dhisco.regression.dataproviders.BaseInput;
import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import com.dhisco.regression.tests.base.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2 public class IntegTest2 extends BaseTest {

	// take this data from configuration service.
	// url/brand
	// url/channel
	String[] list = { "VS_Brand_2_test", "M4_Brand_topic_test", "RoyalArabians_test", "BookingDotCom2_test" };

	@BeforeTest  @Override public void beforeTest(ITestContext iTestContext) throws IOException {
		super.beforeTest(iTestContext);
	}

	@BeforeMethod  public void beforeMethod(Object[] dp) throws Exception {
		super.beforeMethod();

	/*	if (loadDB) {
			loadMariaDB(scriptFileName);
		}

		kafkaConfig = loadBean(KafkaConfig.class);
		asList(list).forEach(t -> kafkaConfig.deleteTopic(t));
		sleep(5, "Waiting for cleanup of Kafka topics");
		asList(list).forEach(t -> kafkaConfig.createTopic(t));

		outDataCleanUp();*/
	}

	@Test(dataProviderClass = IntegDP2.class, dataProvider = "baseDP") public void integTest(
			BaseInput baseInput) throws Exception {

		log.info("%%%%%%%%%%% start test %%%%%%%%%%%");
		test = extent.createTest("Integration Test1", "Integration Test");
/*

		kafkaConfig.publishData("VS_Brand_2_test",
				asList(CommonUtils.getResourceStreamFromAbsPath(baseInput.getDataFile())));

		configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(10, "Waiting for configuration service to load up");

		supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);

		sleep(sleepTime, "Waiting for the pipeline to process the messages");

		String compareFileName=getCompareFileName(baseInput.getDataFile());

		assertJson(getBenchmarkPath() + SLASH_FW + compareFileName,
				getOutPath() + SLASH_FW + compareFileName, JSONCompareMode.STRICT);
*/

		log.info("%%%%%%%%%%% end test %%%%%%%%%%%");
	}



	@Test(dataProviderClass = IntegDP2.class, dataProvider = "baseDP") public void integTest2(
			BaseInput baseInput) throws Exception {

		log.info("%%%%%%%%%%% start test %%%%%%%%%%%");
		test = extent.createTest("Integration Test2", "Integration Test");
/*

		kafkaConfig.publishData("VS_Brand_2_test",
				asList(CommonUtils.getResourceStreamFromAbsPath(baseInput.getDataFile())));

		configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(10, "Waiting for configuration service to load up");

		supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);

		sleep(sleepTime, "Waiting for the pipeline to process the messages");

		String compareFileName=getCompareFileName(baseInput.getDataFile());

		assertJson(getBenchmarkPath() + SLASH_FW + compareFileName,
				getOutPath() + SLASH_FW + compareFileName, JSONCompareMode.STRICT);
*/

		log.info("%%%%%%%%%%% end test %%%%%%%%%%%");
	}

	@AfterTest @Override public void afterTest() {
		super.afterTest();
	}

	@AfterMethod @Override public void afterMethod(ITestResult result) {

	/*	asList(list).forEach(t -> kafkaConfig.deleteTopic(t));*/
		super.afterMethod(result);
	}

}
