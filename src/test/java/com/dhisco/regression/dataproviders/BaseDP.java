package com.dhisco.regression.dataproviders;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.util.Map;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 25-04-2019
 */
public abstract class BaseDP {

	@DataProvider public static Object[][] baseDP(ITestContext context) {

		Map<String, String> map = context.getCurrentXmlTest().getAllParameters();
		BaseInput baseInput = new BaseInput();
		baseInput.setDataFile(map.get("dataPath")+SLASH_FW+map.get("dataFileName"));
		return new Object[][] { new Object[] { baseInput },new Object[]{baseInput} };
	}

}
