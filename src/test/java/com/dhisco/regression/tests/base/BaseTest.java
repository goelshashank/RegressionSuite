package com.dhisco.regression.tests.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dhisco.ptd.dj.PushCoreJson;
import com.dhisco.regression.core.LogTime;
import com.dhisco.regression.core.exceptions.P2DRSException;
import com.dhisco.regression.core.util.RegressionUtils;
import com.dhisco.regression.services.ManageConfigurations;
import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SchedulerConfig;
import com.dhisco.regression.services.config.app.ServerConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.base.BaseConfig;
import com.dhisco.regression.services.config.base.RemoteConnector;
import com.dhisco.regression.services.config.db.CassandraConfig;
import com.dhisco.regression.services.config.db.MariaDBConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;
import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		ServletTestExecutionListener.class }) @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = ManageConfigurations.class) @Log4j2 @Getter
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseTest
		extends AbstractTestNGSpringContextTests {

	@Autowired public ManageConfigurations manageConfigurations;
	@Autowired public RemoteConnector remoteConnector;
	@Autowired public Environment env;

	@Value("#{T(Integer).parseInt('${sleep.time}')}") public Integer sleepTime;
	private String benchmarkPath;
	private String outPath;
	private String inputScriptsPath;
	private Boolean sendMail;
	private String reportName;
	private  String reportPath;
	private String reportFilePath;
	private Boolean clearOut;

	public CassandraConfig cassandraConfig;
	public MariaDBConfig mariaDbConfig;
	public KafkaConfig kafkaConfig;
	public ChannelMessageProcessorConfig channelMessageProcessorConfig;
	public SupplyRuleProcessorConfig supplyRuleProcessorConfig;
	public ConfigurationServiceConfig configurationServiceConfig;
	public ServerConfig serverConfig;
	public SchedulerConfig schedulerConfig;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String suiteName;
	public String testClassName;

	public ITestContext iTestContext;

	public String getSuiteParam(String str){
		Map<String,String> map=iTestContext.getCurrentXmlTest().getAllParameters();
		return map.get(str);
	}

	public String getClassParam(String str){
		Map<String,String> classParamsMap=((TestRunner) iTestContext).getTest().getXmlClasses().get(0).getAllParameters();
		return classParamsMap.get(str);
	}

	public void beforeTest(ITestContext iTestContext) throws IOException {
		this.iTestContext=iTestContext;

		suiteName=iTestContext.getName();

		this.outPath = getSuiteParam("outPath");
		System.setProperty("test.out.path",outPath);

		this.benchmarkPath = getSuiteParam("benchmarkPath");
		this.inputScriptsPath = getSuiteParam("inputScriptsPath");
		this.sendMail = Boolean.valueOf(getSuiteParam("sendMail"));
		this.reportPath= getSuiteParam("reportPath");
		this.clearOut=Boolean.valueOf(getSuiteParam("clearOut"));
		log.info("Loaded all test parameters");

	}

	private void initReporting(String OS, String browser) {
		log.info("Initializing reporting");

		htmlReporter = new ExtentHtmlReporter(reportFilePath);

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("OS", OS);
		extent.setSystemInfo("Browser", browser);

		//configuration items to change the look and feel
		//add content, manage tests etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		log.info("Reporting initialized");
	}

	public void beforeClass(ITestContext iTestContext) throws Exception{

		this.testClassName=getClassParam("testName");
		this.reportName = testClassName+".html";
		this.reportFilePath=this.reportPath+SLASH_FW+this.reportName;

		initReporting(getSuiteParam("OS"), getSuiteParam("browser"));
	}

	public void beforeMethod() throws Exception {
		log.debug("Before method");
	}



	public <T> T loadBean(Class<T> type) {
		return manageConfigurations.loadBean(type);
	}

	public void startProcesses(List<BaseConfig> processes) {
		processes.stream().forEach(t -> {
			try {
				t.startProcess();
			} catch (P2DRSException e) {
				log.error(e.getMessage(), e);
			}
		});
	}

	public void stopProcesses(List<BaseConfig> processes) {
		processes.stream().forEach(t -> {
			try {
				t.stopProcess();
			} catch (P2DRSException e) {
				log.error(e.getMessage(), e);
			}
		});
	}

	public void sleep(int seconds, String message) throws InterruptedException {
		log.info("... {} and sleeping for {} seconds ....", message, seconds);
		TimeUnit.SECONDS.sleep(seconds);
	}

	public InputStream getResourceRelPath(String relativePath) {
		return getClass().getResourceAsStream(relativePath);
	}

	public String getResourceAsStrRelPath(String relativePath) throws IOException {
		return IOUtils.toString(getClass().getResourceAsStream(relativePath), StandardCharsets.UTF_8);
	}

	public void assertJson(String in, String out, JSONCompareMode jsonCompareMode) throws IOException, JSONException {
		JSONAssert
				.assertEquals(RegressionUtils.getResourceAsStrFromAbsPath(in), RegressionUtils.getResourceAsStrFromAbsPath(out),
						jsonCompareMode);
	}

	public JSONCompareResult compareJSON(String in, String out, JSONCompareMode jsonCompareMode)
			throws IOException, JSONException {
		return JSONCompare
				.compareJSON(RegressionUtils.getResourceAsStrFromAbsPath(in), RegressionUtils.getResourceAsStrFromAbsPath(out),
						jsonCompareMode);
	}

	public void sendMail(String filePath) {

		final String username = "Abhishek.Chauhan@rategain.com";
		final String password = "####"; //provide password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.ad.dhisco.com");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Abhishek.Chauhan@rategain.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Abhishek.Chauhan@rategain.com"));
			message.setSubject("P2DRegressionSuite Report");
			message.setText("P2DRegressionSuite Test Report");

			Multipart multipart = new MimeMultipart();
			MimeBodyPart	messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(reportName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			log.info("Sending Mail");

			Transport.send(message);

			log.info("Sent Mail");

		} catch (Exception e) {
			log.info("Mail not sent");
			log.info(e.getMessage(), e);
		}
	}

	@LogTime public void loadMariaDB(String filePath) throws IOException {
		log.info("--------------- Loading Maria DB ------------------");
		log.info("Input script file - {}", filePath);
		mariaDbConfig = loadBean(MariaDBConfig.class);
		mariaDbConfig.executeScript(RegressionUtils.getResourceStreamFromAbsPath(filePath));
		//todo: execute command to explicity update url test db
		log.info("--------------- Loaded Maria DB ------------------ ");
	}


	public String getCompareFileName(String dataFile) throws IOException {
		PushCoreJson pushCoreJson = RegressionUtils.getObjFromResourceJsonAbsPath(dataFile, PushCoreJson.class);
		String toCompareFileName = (RegressionUtils.isNotEmpty(pushCoreJson.getOtaHotelRatePlanNotifRQ())?
				pushCoreJson.getOtaHotelRatePlanNotifRQ().getEchoToken():
				pushCoreJson.getOtaHotelAvailNotifRQ().getEchoToken() ) +".json";

		return toCompareFileName ;
	}

	public void outDataCleanUp() {
		serverConfig=loadBean(ServerConfig.class);
		clearRemoteDir(serverConfig,getOutPath());
	}


	public void clearRemoteDir(BaseConfig config,String path){
		log.info("clearing dir- {} , in {}",path,config);
		config.executeSSHCommands(asList("rm -r "+path+"/*"));
	}

	public void removeRemoteFile(BaseConfig config,String filePath){
		log.info("removing file - {} , in {}",filePath,config);
		config.executeSSHCommands(asList("rm "+filePath));
	}



	private void destroyConfig(BaseConfig baseConfig){
		manageConfigurations.destroyConfig(baseConfig);
	}

	public void afterMethod(ITestResult result) throws  Exception{

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		log.info("tearing down test");
	}

	public void afterClass() {

	}

	public void afterTest() {
		extent.flush();

		if (sendMail)
			sendMail( reportFilePath);
		else
			log.info("Mailing not enabled");
	}

}
