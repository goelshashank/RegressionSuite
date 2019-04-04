package com.dhisco;

import com.dhisco.config.app.ChannelMessageProcessorConfig;
import com.dhisco.config.app.ConfigurationServiceConfig;
import com.dhisco.config.app.SupplyRuleProcessorConfig;
import com.dhisco.config.db.DbConfig;
import com.dhisco.config.db.KafkaConfig;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2
 public class Tess extends BaseTest {

	private static final String TEST_RESOURES_ABSOLUTE_PATH ="C:\\Users\\shashank.goel\\IdeaProjects\\P2DRegressionSuite\\src\\test"
			+ "\\resources\\";


	@BeforeClass public void setupCl() {

	}

	@Override @BeforeMethod public void setUp() throws Exception{
		super.setUp();
		log.debug("%%%%%%%%%%% Loading DB %%%%%%%%%%%%%%%");
		dbConfig = loadBean(DbConfig.class);
		//dbConfig.executeScript(TEST_RESOURES_ABSOLUTE_PATH +"scripts\\test2.sql");

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

		ConfigurationServiceConfig configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		sleep(20);

		kafkaConfig.publishData("VS_Brand_2_test",asList((TEST_RESOURES_ABSOLUTE_PATH +"data\\ari3.json")));

		SupplyRuleProcessorConfig supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);

		ChannelMessageProcessorConfig channelMessageProcessorConfig = loadBean(ChannelMessageProcessorConfig.class);
		sleep(20);
		log.debug("%%%%%%%%%%% end test %%%%%%%%%%%");
	}





}
