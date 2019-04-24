package com.dhisco.regression.tests.test1;

import com.dhisco.regression.dataproviders.TestDataInput;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.util.Map;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
public class DataProvider1 {

	@DataProvider private static Object[][] testArgs(ITestContext context) {

		Map<String, String> map = context.getCurrentXmlTest().getAllParameters();
		TestDataInput testDataInput = new TestDataInput();
		testDataInput.setDataFile(map.get("dataPath")+SLASH_FW+map.get("dataFileName"));
		return new Object[][] { new Object[] {testDataInput} };
	}

}
