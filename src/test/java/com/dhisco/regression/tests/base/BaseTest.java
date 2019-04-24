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
import com.dhisco.regression.core.util.CommonUtils;
import com.dhisco.regression.services.ManageConfigurations;
import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.ServerConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.base.BaseConfig;
import com.dhisco.regression.services.config.base.RemoteConnector;
import com.dhisco.regression.services.config.db.DBConfig;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.testng.ITestResult;

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
		ServletTestExecutionListener.class }) @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = ManageConfigurations.class) @Log4j2 @Getter public abstract class BaseTest
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


	public DBConfig dbConfig;
	public KafkaConfig kafkaConfig;
	public ChannelMessageProcessorConfig channelMessageProcessorConfig;
	public SupplyRuleProcessorConfig supplyRuleProcessorConfig;
	public ConfigurationServiceConfig configurationServiceConfig;
	public ServerConfig serverConfig;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void beforeTest(String OS, String browser, String reportPath, String outPath, String benchmarkPath,
			String reportName, String inputScriptsPath, Boolean sendMail) {

		this.outPath = outPath;
		System.setProperty("test.out.path",outPath);
		this.benchmarkPath = benchmarkPath;
		this.inputScriptsPath = inputScriptsPath;
		this.sendMail = sendMail;
		this.reportName = reportName;
		this.reportPath=reportPath;
		log.info("Test parameters incorporated");

		initReporting(OS, browser);
	}

	private void initReporting(String OS, String browser) {
		log.info("Initializing reporting");

		htmlReporter = new ExtentHtmlReporter(reportName);

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

	public void afterTest() {
		extent.flush();

		if (sendMail)
			sendMail( reportName);
		else
			log.info("Mailing not enabled");
	}

	public void beforeMethod() throws Exception {
		log.debug("Before method");
	}

	public void afterMethod(ITestResult result) {

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
				.assertEquals(CommonUtils.getResourceAsStrFromAbsPath(in), CommonUtils.getResourceAsStrFromAbsPath(out),
						jsonCompareMode);
	}

	public JSONCompareResult compareJSON(String in, String out, JSONCompareMode jsonCompareMode)
			throws IOException, JSONException {
		return JSONCompare
				.compareJSON(CommonUtils.getResourceAsStrFromAbsPath(in), CommonUtils.getResourceAsStrFromAbsPath(out),
						jsonCompareMode);
	}

	public void sendMail(String filePath) {

		final String username = "shashank.goel@rategain.com";
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
			message.setFrom(new InternetAddress("shashank.goel@rategain.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shashank.goel@rategain.com"));
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

	@LogTime public void loadMariaDB(String fileName) throws IOException {
		log.info("--------------- Loading Maria DB ------------------");
		log.info("Input script file - {}", fileName);
		dbConfig = loadBean(DBConfig.class);
		dbConfig.executeCommand("drop database if exists " + dbConfig.getMariaTestDb());
		dbConfig.executeCommand("create database if not exists " + dbConfig.getMariaTestDb());
		dbConfig.executeScript(CommonUtils.getResourceStreamFromAbsPath(inputScriptsPath + SLASH_FW + fileName));
		//todo: execute command to explicity update url test db
		log.info("--------------- Loaded Maria DB ------------------ ");
	}


	public String getCompareFileName(String dataFile) throws IOException {
		PushCoreJson pushCoreJson = CommonUtils.getObjFromResourceJsonAbsPath(dataFile, PushCoreJson.class);
		String toCompareFileName = (CommonUtils.isNotEmpty(pushCoreJson.getOtaHotelRatePlanNotifRQ())?
				pushCoreJson.getOtaHotelRatePlanNotifRQ().getEchoToken():
				pushCoreJson.getOtaHotelAvailNotifRQ().getEchoToken() ) +".json";

		return toCompareFileName ;
	}

	public void outDataCleanUp() {
		serverConfig=loadBean(ServerConfig.class);
		serverConfig.executeSSHCommands(asList("rm -r "+getOutPath()+"/*"));
	}


}
