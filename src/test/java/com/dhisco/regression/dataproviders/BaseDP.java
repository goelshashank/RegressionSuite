package com.dhisco.regression.dataproviders;

import com.dhisco.regression.core.util.CommonUtils;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 25-04-2019
 */
public abstract class BaseDP {

	@DataProvider public static Object[][] baseDP(ITestContext iTestContext) {

		Map<String, String> suiteParamsMap = iTestContext.getCurrentXmlTest().getAllParameters();
		String inputScriptsPath=suiteParamsMap.get("inputScriptsPath");
		String dataPath= suiteParamsMap.get("dataPath");

		Map<String,String> classParamsMap=((TestRunner) iTestContext).getTest().getXmlClasses().get(0).getAllParameters();
		String testName=classParamsMap.get("testName");
		Boolean loadDB=Boolean.valueOf(classParamsMap.get("loadDB"));

		File folder = new File(inputScriptsPath+SLASH_FW+testName);
		File[] listOfFiles = folder.listFiles();

		//todo: Search files recursively in sub folders
		Object[][]o=new Object[listOfFiles.length][1];
		int i=0;
		for (File file : listOfFiles) {
			BaseInput baseInput=new BaseInput();
			String scriptName=file.getName();

			baseInput.setScriptFile(inputScriptsPath+SLASH_FW+testName+SLASH_FW+scriptName);

			File folder2 = new File(dataPath+SLASH_FW+testName+SLASH_FW+scriptName.split("\\.")[0]);
			File[] listOfFiles2 = folder2.listFiles();

			if(CommonUtils.isEmpty(listOfFiles2))continue;
			baseInput.setDataFiles(Arrays.stream(listOfFiles2).map(t-> dataPath+SLASH_FW+testName+SLASH_FW+scriptName.split("\\.")[0]+SLASH_FW+t.getName()).collect(Collectors.toList()));

			baseInput.setLoadDB(loadDB);
			o[i][0]=baseInput;
			i++;
		}
		return o;
	}

}
