package com.dhisco.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dhisco.regression.core.exceptions.P2DRSException;
import com.dhisco.regression.core.util.CommonUtils;
import com.dhisco.regression.services.config.base.RemoteConnector;
import com.dhisco.regression.services.config.base.BaseConfig;
import com.dhisco.regression.services.config.ManageConfigurations;
import com.dhisco.regression.services.config.app.ChannelMessageProcessorConfig;
import com.dhisco.regression.services.config.app.ConfigurationServiceConfig;
import com.dhisco.regression.services.config.app.SupplyRuleProcessorConfig;
import com.dhisco.regression.services.config.db.DbConfig;
import com.dhisco.regression.services.config.db.KafkaConfig;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 27-03-2019
 */
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		ServletTestExecutionListener.class }) @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = ManageConfigurations.class) @Log4j2 public abstract class BaseTest
		extends AbstractTestNGSpringContextTests {

	@Value("#{T(Integer).parseInt('${sleep.time}')}") public Integer sleepTime;

	@Autowired public ManageConfigurations manageConfigurations;
	@Autowired public RemoteConnector remoteConnector;
	@Autowired public Environment env;

	public DbConfig dbConfig;
	public KafkaConfig kafkaConfig;
	public ChannelMessageProcessorConfig channelMessageProcessorConfig;
	public SupplyRuleProcessorConfig supplyRuleProcessorConfig;
	public ConfigurationServiceConfig configurationServiceConfig;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void beforeTest(String OS, String browser) {
		initReporting(OS, browser);
	}

	private void initReporting(String OS, String browser) {
		htmlReporter = new ExtentHtmlReporter("/apps/test/regression/test-output"
				+ "/testReport.html");

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
	}

	public void afterTest() {
		extent.flush();

		//send mail

	/*	sendMail(System.getenv("SystemDrive")+"/apps/test/regression/test-output"
				+ "/testReport.html");*/
	}

	public void beforeMethod() throws Exception {
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

	public void sleep(int seconds) throws InterruptedException {
		log.info("sleeping for {} seconds", seconds);
		TimeUnit.SECONDS.sleep(seconds);
	}

	public InputStream getResource(String relativePath) {
		return getClass().getResourceAsStream(relativePath);
	}

	public String getResourceAsStrRelPath(String relativePath) throws IOException {
		return IOUtils.toString(getClass().getResourceAsStream(relativePath), StandardCharsets.UTF_8);
	}

	public void assertJson(String fPath1, String fPath2, JSONCompareMode jsonCompareMode)
			throws IOException, JSONException {
			InputStream is1 = new FileInputStream(fPath1);
			InputStream is2 = new FileInputStream(fPath2);
		JSONAssert.assertEquals(IOUtils.toString(is1, "UTF-8"),IOUtils.toString(is2, "UTF-8"), jsonCompareMode);
	}

	public JSONCompareResult compareJSON(String input, String output, JSONCompareMode jsonCompareMode)
			throws IOException, JSONException {
		return 	JSONCompare.compareJSON(CommonUtils.getResourceAsStrAbsPath(input),
				CommonUtils.getResourceAsStrAbsPath(output),
				jsonCompareMode);
	}


	public   void sendMail(String filePath){

		final String username = "shashank.goel@rategain.com";
		final String password = "####"; //provide password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.ad.dhisco.com");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress( "shashank.goel@rategain.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse( "shashank.goel@rategain.com"));
			message.setSubject("P2DRegressionSuite Report");
			message.setText("P2DRegressionSuite Test Report");

			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			String fileName = "testReport.html";
			DataSource source = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			log.info("Sending Mail");

			Transport.send(message);

			log.info("Sent Mail");

		} catch (Exception e) {
			log.info("could not send mail");
			log.info(e.getMessage(),e);
		}
	}

}
