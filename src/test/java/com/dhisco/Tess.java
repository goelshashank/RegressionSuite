package com.dhisco;

import com.dhisco.config.app.ConfigurationServiceConfig;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@Log4j2 public class Tess extends BaseTest {

	@Override @BeforeMethod public void setUp() {
		super.setUp();
	}

	@Override @AfterMethod public void tearDown() {
		super.tearDown();
	}

	//@Test(dataProviderClass = DataProvider1.class, dataProvider = "testArgs")
	@Test public void tess() throws InterruptedException{
		ConfigurationServiceConfig configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		Map map = configurationServiceConfig.executeSSHCommands(asList("pwd"));

		sleep(15);
		System.out.println("end test");
	}





}
