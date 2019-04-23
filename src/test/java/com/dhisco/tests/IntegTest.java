package com.dhisco.tests;

import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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
@Log4j2 public class IntegTest extends BaseTest {

	String[] list = { "VS_Brand_2_test", "M4_Brand_topic_test", "RoyalArabians_test", "BookingDotCom2_test" };

	@BeforeTest @Override @Parameters({ "OS", "browser" }) public void beforeTest(String OS, String browser) {
		super.beforeTest(OS, browser);
	}

	@AfterTest @Override public void afterTest() {
		super.afterTest();
	}

	@AfterMethod @Override public void afterMethod(ITestResult result) {

		asList(list).forEach(t -> kafkaConfig.deleteTopic(t));
		super.afterMethod(result);
	}

	@Override @BeforeMethod public void beforeMethod() throws Exception {

		super.beforeMethod();
		log.info("%%%%%%%%%%% Loading DB %%%%%%%%%%%%%%%");

		/*
		try{
	String url="http://valptddevsre01a.asp.dhisco.com:8080/pctest/capture-rez-gain";
			String jsonString="    {\n" + "        \"id\": 1,\n" + "        \"createdAt\": 1547510400000,\n"
					+ "        \"updatedAt\": 1547510400000,\n" + "        \"name\": \"RoyalArabians\",\n"
					+ "        \"topic\": \"RoyalArabians_test\",\n" + "        \"sgaCode\": \"9z\",\n"
					+ "        \"consumerCount\": 4,\n" + "        \"channelConcurrency\": 100,\n"
					+ "        \"channelHotelConcurrency\": 15,\n" + "        \"appInstances\": 1,\n"
					+ "        \"publicRatesAllowed\": false,\n" + "        \"shadowed\": true\n" + "    }\n";

			org.apache.http.impl.nio.client.CloseableHttpAsyncClient httpClient=
					HttpAsyncClients.custom().setMaxConnPerRoute(2).setMaxConnTotal(2).build();
			httpClient.start();

			HttpPost request = new HttpPost(url);
			request.addHeader("content-type", "application/json");
			StringEntity entity = new StringEntity(jsonString);
			request.setEntity(entity);

			httpClient.execute(request, new FutureCallback<HttpResponse>() {

				@Override public void cancelled() {

				}

				@Override public void completed(HttpResponse httpResponse) {
					log.debug("adfdsa");
				}

				@Override public void failed(Exception e) {

				}
			});

		}catch (Exception e){
			log.error(e.getMessage(),e);
		}


*/


		/*dbConfig = loadBean(DbConfig.class);
		dbConfig.executeCommand("drop database if exists "+dbConfig.getMariaTestDb());
		dbConfig.executeCommand("create database if not exists "+dbConfig.getMariaTestDb());
		dbConfig.executeScript(getResource("/scripts/test.sql"));*/

		kafkaConfig = loadBean(KafkaConfig.class);
		asList(list).forEach(t -> kafkaConfig.deleteTopic(t));
		sleep(5);
		asList(list).forEach(t -> kafkaConfig.createTopic(t));
	}

	//@Test(dataProviderClass = DataProvider1.class, dataProvider = "testArgs")
	@Test public void integTest() throws Exception {
		test = extent.createTest("Integration Test", "Integration Test");

		kafkaConfig.publishData("VS_Brand_2_test", asList(getResource("/data/ari.json")));

		configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(10);

		supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);
		channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);

		sleep(sleepTime);

	/*	channelMessageProcessorConfig.copyRemoteToLocal("/apps/test/regression/out","C:\\Users\\shashank"
				+ ".goel\\IdeaProjects\\P2DRegressionSuite\\src\\test\\resources\\out","out1.json");
*/
/*
		sleep(10);
		log.info("just above assert");
		assertJson("/benchmark/out1.json","/out/out2.json", JSONCompareMode.STRICT);
*/

		log.info("%%%%%%%%%%% end test %%%%%%%%%%%");

	}

}
