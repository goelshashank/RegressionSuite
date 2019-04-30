package com.dhisco.regression.tests.integTest;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static com.dhisco.regression.core.util.CommonUtils.isNotEmpty;
import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2 public class IntegTest extends BaseTest {

/*
	String[] list = { "VS_Brand_2_test", "M4_Brand_topic_test", "RoyalArabians_test", "BookingDotCom2_test" };
*/

	private String[] list = null;

	@BeforeTest @Override public void beforeTest(ITestContext iTestContext) throws IOException {
		super.beforeTest(iTestContext);
	}

	@BeforeClass @Override @Parameters("testName") public void beforeClass(String testClassName) {
		super.beforeClass(testClassName);
	}

	@BeforeMethod public void beforeMethod(Object[] o) throws Exception {
		super.beforeMethod();
		BaseInput baseInput = (BaseInput) o[0];

		if (baseInput.getLoadDB()) {
			loadMariaDB(baseInput.getScriptFile());
		}

		log.info("debug");

		kafkaConfig = loadBean(KafkaConfig.class);
		asList(list).forEach(t -> kafkaConfig.deleteTopic(t));
		sleep(5, "Waiting for cleanup of Kafka topics");
		asList(list).forEach(t -> kafkaConfig.createTopic(t));

		outDataCleanUp();
	}

	@Test(dataProviderClass = IntegDP.class, dataProvider = "baseDP") public void integTest(BaseInput baseInput)
			throws Exception {
		test = extent.createTest("Integration Test1", "Integration Test1");

		log.info("%%%%%%%%%%% start test %%%%%%%%%%%");

		baseInput.getDataFiles().forEach(t -> {
			try {
				kafkaConfig.publishData("VS_Brand_2_test", asList(CommonUtils.getResourceStreamFromAbsPath(t)));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		});

		configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(10, "Waiting for configuration service to load up");

		supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);

		sleep(sleepTime, "Waiting for the pipeline to process the messages");

		baseInput.getDataFiles().forEach(t -> {

			try {
				String compareFileName = getCompareFileName(t);
				assertJson(getBenchmarkPath() + SLASH_FW + compareFileName, getOutPath() + SLASH_FW + compareFileName,
						JSONCompareMode.STRICT);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		});

		log.info("%%%%%%%%%%% end test %%%%%%%%%%%");
	}

	@AfterMethod @Override public void afterMethod(ITestResult result) {

		asList(list).forEach(t -> kafkaConfig.deleteTopic(t));

		Arrays.asList(configurationServiceConfig, supplyRuleProcessorConfig, channelMessageProcessorConfig).
				forEach(t -> {
					if (isNotEmpty(t)) {
						destroyConfig(t);
					}
				});
		super.afterMethod(result);
	}

	@AfterClass @Override public void afterClass() {
		super.afterClass();
	}

	@AfterTest @Override public void afterTest() {
		super.afterTest();
	}

}
