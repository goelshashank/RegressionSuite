package com.dhisco.regression.tests.scheduler;

import static com.dhisco.regression.core.BaseConstants.DEV_TEST;
import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static com.dhisco.regression.core.util.CommonUtils.isNotEmpty;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.test.context.ActiveProfiles;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dhisco.regression.core.util.CommonUtils;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SchedulerConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import com.dhisco.regression.tests.base.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.log4j.Log4j2;

@ActiveProfiles(DEV_TEST)
@Log4j2
public class SchedulerTest extends BaseTest {

	private Set<String> topics;
	private String inputTopic;
	private String outputTopic;
	public static final int KAFKA_READ_TIMEOUT = 30;

	@BeforeTest
	@Override
	public void beforeTest(ITestContext iTestContext) throws IOException {
		super.beforeTest(iTestContext);
	}

	@BeforeClass
	public void beforeClass(ITestContext iTestContext) throws Exception {
		super.beforeClass(iTestContext);
	}

	@BeforeMethod
	public void beforeMethod(Object[] o) throws Exception {
		super.beforeMethod();
		
		SchedulerInput baseInput = (SchedulerInput) o[0];

		if (baseInput.getLoadMariaDB()) {
			
			loadMariaDB(baseInput.getScriptFile());
		}

		// CAUTION: Always, load DB beans before apps.
		configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(15, "Waiting for configuration service to load up");

		topics = getTopics(); // topics to be created in kafka

		if (getClearOut()) {
			outDataCleanUp();
		}
		
		kafkaConfig = loadBean(KafkaConfig.class);
		topics.forEach(t -> kafkaConfig.deleteTopic(t));
		sleep(5, "Waiting for cleanup of Kafka topics");
		topics.forEach(t -> kafkaConfig.createTopic(t));
		
	}

	@Test(dataProviderClass = SchedulerDP.class, dataProvider = "schedulerDP")
	public void schedulerShoppingTest(SchedulerInput schedulerInput) throws Exception {
		test = extent.createTest(getTestClassName(), getTestClassName());

		log.info("----......------- Start Test: {} -----.......-------", getTestClassName());

		log.info(" %%%%%% Total Files - {}", schedulerInput.getDataFiles().size());
		for (InputStream t : schedulerInput.getDataFiles()) {
			log.info("#### Publishing Files - {} ####", t);
			assertTrue(isNotEmpty(t));
			kafkaConfig.publishData(inputTopic, asList(t));
		}
		sleep(2, "Waiting After Publishing Data");

		schedulerConfig = loadBean(SchedulerConfig.class);
		sleep(20, "Loading Scheduler App");
		
		List<String> list = Arrays.asList(outputTopic);
		
		
		Map<String, List<String>> result = kafkaConfig.consumeData( list, KAFKA_READ_TIMEOUT);
		System.out.println( result.toString() );
		ObjectMapper jackson = new ObjectMapper();
		String actual = jackson.writeValueAsString( result );

		/*
		 * for (String t : schedulerInput.getDataFiles()) {
		 * 
		 * String compareFileName = getCompareFileName(t); assertJson(getBenchmarkPath()
		 * + SLASH_FW + compareFileName, getOutPath() + SLASH_FW + compareFileName,
		 * JSONCompareMode.STRICT); }
		 */
		log.info("---------........--------- End Test: {} ----------..........----------", getTestClassName());
	}

	
	/*
	 * @Test(dataProviderClass = SchedulerDP.class, dataProvider = "schedulerDP")
	 * public void schedulerSmartShoppingTest(SchedulerInput schedulerInput) throws
	 * Exception { test = extent.createTest(getTestClassName(), getTestClassName());
	 * 
	 * log.info("----......------- Start Test: {} -----.......-------",
	 * getTestClassName());
	 * 
	 * schedulerConfig = loadBean(SchedulerConfig.class); sleep(20,
	 * "Loading Scheduler App");
	 * 
	 * for (String t : schedulerInput.getDataFiles()) {
	 * 
	 * String compareFileName = getCompareFileName(t); assertJson(getBenchmarkPath()
	 * + SLASH_FW + compareFileName, getOutPath() + SLASH_FW + compareFileName,
	 * JSONCompareMode.STRICT); } log.
	 * info("---------........--------- End Test: {} ----------..........----------"
	 * , getTestClassName()); }
	 */
	 

	@AfterMethod
	@Override
	public void afterMethod(ITestResult result) throws Exception {
		log.info("in after method  ");
		super.afterMethod(result);
	}

	@AfterClass
	@Override
	public void afterClass() {
		super.afterClass();
	}

	@AfterTest
	@Override
	public void afterTest() {
		super.afterTest();
	}

	private Set<String> getTopics() {
		Set<String> topics = new HashSet<>();
		try {
		System.out.println("http://" + configurationServiceConfig.getHost() + ":"
				+ configurationServiceConfig.getPort() + "/api" + "/properties" + "/scheduler");
		String schedulerTopics = CommonUtils.getRestCall("http://" + configurationServiceConfig.getHost() + ":"
				+ configurationServiceConfig.getPort() + "/api" + "/properties" + "/scheduler");

		inputTopic = JsonPath.read(schedulerTopics, "$[\"kafka.admin.topic.name\"]");
		outputTopic = JsonPath.read(schedulerTopics, "$[\"kafka.shopping.topic.name\"]");

		topics.add(inputTopic);
		topics.add(outputTopic);
		
		}catch(Exception e) {
			log.info("**************No Data From Configuration Service****************", getTestClassName());
		}
		return topics;
	}

}
