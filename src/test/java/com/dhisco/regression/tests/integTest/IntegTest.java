package com.dhisco.regression.tests.integTest;

import com.dhisco.ptd.dj.PushCoreJson;
import com.dhisco.regression.core.util.CommonUtils;
import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import com.dhisco.regression.tests.base.BaseTest;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static com.dhisco.regression.core.util.CommonUtils.isNotEmpty;
import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2 public class IntegTest extends BaseTest {

	private Set<String> topics;

	@BeforeTest @Override public void beforeTest(ITestContext iTestContext) throws IOException {
		super.beforeTest(iTestContext);
	}

	@BeforeClass public void beforeClass(ITestContext iTestContext) throws Exception {
		super.beforeClass(iTestContext);
	}

	@BeforeMethod public void beforeMethod(Object[] o) throws Exception {
		super.beforeMethod();
		IntegInput baseInput = (IntegInput) o[0];

		if (baseInput.getLoadDB()) {
			loadMariaDB(baseInput.getScriptFile());
		}

		configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(15, "Waiting for configuration service to load up");

		topics = getTopics(); //topics to be created in kafka

		kafkaConfig = loadBean(KafkaConfig.class);
		topics.forEach(t -> kafkaConfig.deleteTopic(t));
		sleep(5, "Waiting for cleanup of Kafka topics");
		topics.forEach(t -> kafkaConfig.createTopic(t));

		if (getClearOut()) {
			outDataCleanUp();
		}

	}

	@Test(dataProviderClass = IntegDP.class, dataProvider = "integDP") public void integTest(IntegInput baseInput)
			throws Exception {
		test = extent.createTest(getTestClassName(), getTestClassName());

		log.info("----......------- Start test: {} -----.......-------", getTestClassName());

		for (String t : baseInput.getDataFiles()) {
			log.info("#### Publishing file - {} ####", t);
			PushCoreJson pushCoreJson = CommonUtils.getObjFromResourceJsonAbsPath(t, PushCoreJson.class);
			Assert.assertTrue(isNotEmpty(pushCoreJson));
			kafkaConfig.publishData("VS_Brand_3_test", asList(CommonUtils.getResourceStreamFromAbsPath(t)));

		}


		supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);

		sleep(sleepTime, "Waiting for the pipeline to process the messages");

		for (String t : baseInput.getDataFiles()) {

			String compareFileName = getCompareFileName(t);
			assertJson(getBenchmarkPath() + SLASH_FW + compareFileName, getOutPath() + SLASH_FW + compareFileName,
					JSONCompareMode.STRICT);
		}
		log.info("---------........--------- End test: {} ----------..........----------", getTestClassName());
	}

	@AfterMethod @Override public void afterMethod(ITestResult result) throws Exception {
		log.info("in after method ");
		super.afterMethod(result);
	}

	@AfterClass @Override public void afterClass() {
		super.afterClass();
	}

	@AfterTest @Override public void afterTest() {
		super.afterTest();
	}

	private Set<String> getTopics() {
		Set<String> topics1 = new HashSet<>();

		String brandsOut = CommonUtils.getRestCall(
				"http://" + configurationServiceConfig.getHost() + ":" + configurationServiceConfig.getPort() + "/p2d"
						+ "/brands");
		String channelsOut = CommonUtils.getRestCall(
				"http://" + configurationServiceConfig.getHost() + ":" + configurationServiceConfig.getPort() + "/p2d"
						+ "/channels");

		Set<String> brands = ((List<String>) JsonPath.read(brandsOut, "$.*.topic")).stream().map(t -> t.toString())
				.collect(Collectors.toList()).stream().filter(t -> t.contains("_test")).collect(Collectors.toSet());
		Set<String> channels = ((List<String>) JsonPath.read(channelsOut, "$.*.topic")).stream().map(t -> t.toString())
				.collect(Collectors.toList()).stream().filter(t -> t.contains("_test")).collect(Collectors.toSet());

		topics1.addAll(brands);
		topics1.addAll(channels);

		return topics1;
	}

}
