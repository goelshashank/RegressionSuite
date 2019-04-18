package com.dhisco.tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 17-04-2019
 */
@Log4j2
public class Tess2 extends BaseTest{


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

	@Test
	public void test1() throws Exception{
		test = extent.createTest("Test Case 1", "PASSED test case");
		Assert.assertTrue(true);
	}

	@Test
	public void test2() throws Exception{
		test = extent.createTest("Test Case 2", "PASSED test case");
		Assert.assertTrue(false);
	}

}
