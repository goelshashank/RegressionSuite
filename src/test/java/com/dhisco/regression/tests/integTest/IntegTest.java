package com.dhisco.regression.tests.integTest;

import com.dhisco.regression.core.util.CommonUtils;
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

	private String[] topics = { "VS_Brand_2_test", "M4_Brand_topic_test", "RoyalArabians_test", "BookingDotCom2_test" };

	//	private String[] topics = null;

	@BeforeTest @Override public void beforeTest(ITestContext iTestContext) throws IOException {
/*
		String s="[{\"id\":1,\"createdAt\":1547510400000,\"updatedAt\":1547510400000,\"code\":\"VS\","
				+ "\"topic\":\"VS_Brand_2\",\"shadowed\":false},{\"id\":2,\"createdAt\":1547510400000,\"updatedAt\":1547510400000,\"code\":\"M4\",\"topic\":\"M4_Brand_topic_test\",\"shadowed\":false},{\"id\":5,\"createdAt\":1554201862000,\"updatedAt\":1554201862000,\"code\":\"VS_DR\",\"topic\":\"VS_Brand_2_test\",\"shadowed\":false},{\"id\":6,\"createdAt\":1554201862000,\"updatedAt\":1554201862000,\"code\":\"VS_LBR\",\"topic\":\"VS_Brand_2_test\",\"shadowed\":false},{\"id\":7,\"createdAt\":1554201862000,\"updatedAt\":1554201862000,\"code\":\"ZZ_DR\",\"topic\":\"ZZ_Brand_2\",\"shadowed\":false},{\"id\":8,\"createdAt\":1554201862000,\"updatedAt\":1554201862000,\"code\":\"ZZ_LBR\",\"topic\":\"ZZ_Brand_2\",\"shadowed\":false},{\"id\":3,\"createdAt\":1554249600000,\"updatedAt\":1554249600000,\"code\":\"ZZ\",\"topic\":\"ZZ_Brand_2\",\"shadowed\":false}]";

		String s1="[{\"id\":1,\"createdAt\":1547510400000,\"updatedAt\":1547510400000,\"name\":\"RoyalArabians\","
				+ "\"topic\":\"RoyalArabians_test\",\"sgaCode\":\"9z\",\"consumerCount\":4,\"channelConcurrency\":100,\"channelHotelConcurrency\":15,\"appInstances\":1,\"publicRatesAllowed\":false,\"shadowed\":true},{\"id\":2,\"createdAt\":1547510400000,\"updatedAt\":1547510400000,\"name\":\"Booking.com\",\"topic\":\"BookingDotCom2_test\",\"sgaCode\":\"01\",\"consumerCount\":1,\"channelConcurrency\":100,\"channelHotelConcurrency\":20,\"appInstances\":1,\"publicRatesAllowed\":true,\"shadowed\":false}]";

*/

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
	/*	List<String> brands=CommonUtils.getRestCall("http://valstpdevcfs01a.asp.dhisco.com:8081/p2d/brands");
		List<String> channels=CommonUtils.getRestCall("http://valstpdevcfs01a.asp.dhisco.com:8081/p2d/channels");

		topics =Arrays.asList(brands,channels).stream().toArray(String[] ::new);
*/
		kafkaConfig = loadBean(KafkaConfig.class);
		asList(topics).forEach(t -> kafkaConfig.deleteTopic(t));
		sleep(5, "Waiting for cleanup of Kafka topics");
		asList(topics).forEach(t -> kafkaConfig.createTopic(t));

		if (getClearOut()) {
			outDataCleanUp();
		}
	}

	@Test(dataProviderClass = IntegDP.class, dataProvider = "integDP") public void integTest(IntegInput baseInput)
			throws Exception {
		test = extent.createTest(getTestClassName(), getTestClassName());

		log.info("%%%%%%%%%%% start test: {} %%%%%%%%%%%", getTestClassName());

		baseInput.getDataFiles().forEach(t -> {
			try {
				kafkaConfig.publishData("VS_Brand_2_test", asList(CommonUtils.getResourceStreamFromAbsPath(t)));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		});

		sleep(10, "Waiting for configuration service to load up");

		supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);

		sleep(sleepTime, "Waiting for the pipeline to process the messages");

		for (String t : baseInput.getDataFiles()) {

			String compareFileName = getCompareFileName(t);
			assertJson(getBenchmarkPath() + SLASH_FW + compareFileName, getOutPath() + SLASH_FW + compareFileName,
					JSONCompareMode.STRICT);
		}
		log.info("%%%%%%%%%%% end test: {} %%%%%%%%%%%", getTestClassName());
	}

	@AfterMethod @Override public void afterMethod(ITestResult result) throws Exception {
		log.info("in after method ");
		asList(topics).forEach(t -> kafkaConfig.deleteTopic(t));

	/*	Arrays.asList(kafkaConfig, configurationServiceConfig, supplyRuleProcessorConfig, channelMessageProcessorConfig)
				.forEach(t -> {
					if (isNotEmpty(t)) {
						destroyConfig(t);
					}
				});
		sleep(10, "Sleeping after destroying configs");*/
		super.afterMethod(result);
	}

	@AfterClass @Override public void afterClass() {
		super.afterClass();
	}

	@AfterTest @Override public void afterTest() {
		super.afterTest();
	}

}
