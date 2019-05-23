package com.dhisco.regression.tests.srp;

import com.dhisco.ptd.dj.PushCoreJson;
import com.dhisco.ptd.dj.util.PushCoreJsonWithChannel;
import com.dhisco.regression.core.util.ExcelUtils;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import com.dhisco.regression.tests.base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static java.util.Arrays.asList;

@Log4j2
public class SRPTest extends BaseTest {

	private ExcelUtils ExcelUtils;
	private static int SHEET_NUM = 0;

	public static String INPUT_COLUMN = "Input";
	public static String QUEUE_EXPECTED_COLUMN = "Queue-expected-output";
	public static String QUEUE_ACTUAL_COLUMN = "Queue-actual-output";
	public static String QUEUE_DIFF_COLUMN = "Queue-diff";
	public static String HADOOP_EXPECTED_COLUMN = "Hadoop-expected-output";
	public static String HADOOP_ACTUAL_COLUMN = "Hadoop-actual-output";
	public static String CASSANDRA_EXPECTED_COLUMN = "Cassandra-expected-output";
	public static String CASSANDRA_ACTUAL_COLUMN = "Cassandra-actual-output";

	public static String PASSED_STRING = "PASSED";

	String[] listTopics={"VS_Brand_2_test"};
	private String[] topicsToRead = new String[]{"BookingDotCom2_test", "RoyalArabians_test"};;
	public static final int KAFKA_READ_TIMEOUT = 30;

	@BeforeTest @Override public void beforeTest(ITestContext iTestContext) throws IOException {
		super.beforeTest(iTestContext);
	}

	@BeforeClass  @Override public void beforeClass(ITestContext iTestContext) throws Exception{
		super.beforeClass(iTestContext);

		String fullScriptPath=getInputScriptsPath()+SLASH_FW+testClassName+SLASH_FW+getClassParam("scriptFileName");

		if (Boolean.valueOf(getClassParam("loadMariaDB"))) {
			loadMariaDB(fullScriptPath);
		}

	}

	@BeforeMethod
	public void beforeMethod( ) throws Exception {
		super.beforeMethod();

		ExcelUtils = loadBean(ExcelUtils.class);

		kafkaConfig = loadBean(KafkaConfig.class);
		asList(listTopics).forEach(t -> kafkaConfig.deleteTopic(t));
		asList(topicsToRead).forEach(t -> kafkaConfig.deleteTopic(t));

		sleep(5, "Waiting for cleanup of Kafka topics");
		asList(listTopics).forEach(t -> kafkaConfig.createTopic(t));
		asList(topicsToRead).forEach(t -> kafkaConfig.createTopic(t));
		//outDataCleanUp();

	}


	@AfterMethod @Override public void afterMethod(ITestResult result) {

		asList(listTopics).forEach(t -> kafkaConfig.deleteTopic(t));
		asList(topicsToRead).forEach(t -> kafkaConfig.deleteTopic(t));
		//        super.afterMethod(result);
	}


	@Test(dataProviderClass = SRPDP.class,dataProvider = "rowNumber")
	public void test1(SRPInput srpInput ) throws Exception {
		test = extent.createTest(getTestClassName(), getTestClassName());


		log.info("%%%%%%%%%%% start test %%%%%%%%%%%");
		int testCaseNumber = srpInput.getData();
		Sheet sheet = ExcelUtils.getSheet( SHEET_NUM );
		Row currentRow = sheet.getRow( testCaseNumber );
		//        read input from excel
		//String inpJson = ExcelUtils.getColumnFromCell( cellIterator1, 3 );
		Cell inpJsonCell = currentRow.getCell( ExcelUtils.getColumnNoByName(sheet, INPUT_COLUMN));
		String inpJson = inpJsonCell.getStringCellValue();
		//insert into topic1
		InputStream inpStream = new ByteArrayInputStream( inpJson.getBytes());
		for ( String inpTopic : listTopics ) {
			kafkaConfig.publishData(inpTopic, asList(inpStream));
		}
		///        sleep( 5 );

		// run srp, wait for few seconds
		ConfigurationServiceConfig configurationServiceConfig = loadBean(ConfigurationServiceConfig.class);
		///        sleep(10);

		SupplyRuleProcessorConfig supplyRuleProcessorConfig = loadBean(SupplyRuleProcessorConfig.class);

		// read from output topic1, topic2
		Set<String> set = Arrays.stream(topicsToRead).collect(Collectors.toSet());

		Map<String, List<PushCoreJsonWithChannel>> result = kafkaConfig.consumeData( set, KAFKA_READ_TIMEOUT);
		//System.out.println( result.toString() );
		ObjectMapper jackson = new ObjectMapper();
		String actual = jackson.writeValueAsString( result );

		//write output to excel
		Cell expectedCell = currentRow.getCell( ExcelUtils.getColumnNoByName(sheet,QUEUE_EXPECTED_COLUMN));
		String expected = expectedCell.getStringCellValue();

		//Cell actualCell = currentRow.getCell( ExcelUtils.getColumnNoByName(sheet,QUEUE_ACTUAL_COLUMN));
		Cell actualCell = currentRow.createCell( ExcelUtils.getColumnNoByName(sheet,QUEUE_ACTUAL_COLUMN) );
		actualCell.setCellValue( actual );
		// verify output

		JsonNode expectedNode = jackson.readTree( expected );
		JsonNode actualNode = jackson.readTree( actual );
		JsonNode patchNode = JsonDiff.asJson(expectedNode, actualNode);
		String diff = patchNode.toString();

		Cell diffCell = currentRow.createCell( ExcelUtils.getColumnNoByName(sheet,QUEUE_DIFF_COLUMN));
		if( diff.length() > 0 ) {
			diffCell.setCellValue(diff);
		} else {
			diffCell.setCellValue( PASSED_STRING );
		}

		JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);

		log.info("%%%%%%%%%%% end test %%%%%%%%%%%");
	}

	@AfterClass @Override public void afterClass() {
		super.afterClass();
	}

	@AfterTest @Override public void afterTest() {
		super.afterTest();
	}


}