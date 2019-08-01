package com.dhisco.regression.tests.scheduler;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static com.dhisco.regression.core.util.CommonUtils.getAllFiles;
import static com.dhisco.regression.core.util.CommonUtils.isEmpty;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.DataProvider;

import com.dhisco.regression.dataproviders.BaseDP;

public class SchedulerDP extends BaseDP {

	@DataProvider
	public static Object[][] schedulerDP(ITestContext iTestContext) {

		Map<String, String> suiteParamsMap = iTestContext.getCurrentXmlTest().getAllParameters();
		String inputScriptsPath = suiteParamsMap.get("inputScriptsPath");
		String dataPath = suiteParamsMap.get("dataPath");

		Map<String, String> classParamsMap = ((TestRunner) iTestContext).getTest().getXmlClasses().get(0)
				.getAllParameters();
		String testName = classParamsMap.get("testName");
		Boolean loadMariaDB = Boolean.valueOf(classParamsMap.get("loadMariaDB"));

		File folder = new File(inputScriptsPath + SLASH_FW + testName);
		File[] listOfFiles = folder.listFiles();

		Object[][] o = new Object[listOfFiles.length][1];
		SchedulerInput baseInput = new SchedulerInput();
		String scriptName = listOfFiles[0].getName();
		baseInput.setScriptFile(inputScriptsPath + SLASH_FW + testName + SLASH_FW + scriptName);
		
		
		
		File folder2 = new File(dataPath + SLASH_FW + testName);
        File[] listOfFiles2 = folder2.listFiles();
        List<InputStream> inputFilesStream = new ArrayList<InputStream>();
        for (File file : listOfFiles2) {
            if (file.isFile()) {
                try{
                	inputFilesStream.add(new FileInputStream(file.getPath())); 
               }catch (Exception e) {
                e.printStackTrace();
               }
            }
        }
            
		baseInput.setDataFiles(inputFilesStream);
		baseInput.setLoadMariaDB(loadMariaDB);
		baseInput.setTestName(testName);
		o[0][0] = baseInput;

		return o;
	}

}
