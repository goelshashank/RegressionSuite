package com.dhisco.reports;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Log4j2 public class CustomisedListener implements ITestListener {

	@Override public void onFinish(ITestContext context) {
		log.info("PASSED TEST CASES");
		context.getPassedTests().getAllResults().forEach(result -> {
			log.info(result.getName());
		});
		log.info("FAILED TEST CASES");
		context.getFailedTests().getAllResults().forEach(result -> {
			log.info(result.getName());
		});
		log.info("Test completed on: " + context.getEndDate().toString());
	}

	@Override public void onStart(ITestContext arg0) {
		log.info("Started testing on: " + arg0.getStartDate().toString());
	}

	@Override public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override public void onTestFailure(ITestResult arg0) {
		log.info("Failed : " + arg0.getName());

	}

	@Override public void onTestSkipped(ITestResult arg0) {
		log.info("Skipped Test: " + arg0.getName());

	}

	@Override public void onTestStart(ITestResult arg0) {
		log.info("Testing: " + arg0.getName());

	}

	@Override public void onTestSuccess(ITestResult arg0) {
		long timeTaken = ((arg0.getEndMillis() - arg0.getStartMillis()));
		log.info("Tested: " + arg0.getName() + " Time taken:" + timeTaken + " ms");

	}

}
