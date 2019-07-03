package com.dhisco.regression.tests.supAndChIntegTest;

import com.dhisco.regression.dataproviders.BaseDP;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static com.dhisco.regression.core.util.RegressionUtils.getAllFiles;
import static com.dhisco.regression.core.util.RegressionUtils.isEmpty;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
public class SupAndChIntegDP extends BaseDP {

	@DataProvider public static Object[][] supAndChIntegDP(ITestContext iTestContext) {

		Map<String, String> suiteParamsMap = iTestContext.getCurrentXmlTest().getAllParameters();
		String inputScriptsPath=suiteParamsMap.get("inputScriptsPath");
		String dataPath= suiteParamsMap.get("dataPath");

		Map<String,String> classParamsMap=((TestRunner) iTestContext).getTest().getXmlClasses().get(0).getAllParameters();
		String testName=classParamsMap.get("testName");
		Boolean loadMariaDB=Boolean.valueOf(classParamsMap.get("loadMariaDB"));

		File folder = new File(inputScriptsPath+SLASH_FW+testName);
		File[] listOfFiles = folder.listFiles();

		//todo: Search files recursively in sub folders
		Object[][]o=new Object[listOfFiles.length][1];
		int i=0;
		for (File file : listOfFiles) {
			SupAndChIntegInput baseInput=new SupAndChIntegInput();
			String scriptName=file.getName();

			baseInput.setScriptFile(inputScriptsPath+SLASH_FW+testName+SLASH_FW+scriptName);

			List<File> folder2=getAllFiles(dataPath+SLASH_FW+testName+SLASH_FW+scriptName.split("\\.")[0]);
			File[] listOfFiles2= folder2.toArray(new File[folder2.size()]);

			if(isEmpty(listOfFiles2))continue;
			baseInput.setDataFiles(
					Arrays.stream(listOfFiles2).map(t-> t.getAbsolutePath()).collect(
							Collectors.toList()));

			baseInput.setLoadMariaDB(loadMariaDB);
			baseInput.setTestName(testName);
			o[i][0]=baseInput;
			i++;
		}
		return o;
	}

}
