package com.dhisco.regression.services.config.db;

import com.dhisco.regression.services.config.BaseConfig;
import com.dhisco.regression.services.RemoteConnector;
import com.ibatis.common.jdbc.ScriptRunner;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static com.dhisco.regression.core.util.CommonUtils.isEmpty;
import static com.dhisco.regression.core.util.CommonUtils.isNotEmpty;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Lazy @Log4j2 @Getter @Setter @ToString(includeFieldNames = true) @Configuration @PropertySource("db.properties") @ConfigurationProperties(prefix = "db") public class DbConfig
		extends BaseConfig {

	@Autowired RemoteConnector remoteConnector;
	private String mariadbUsername;
	private String mariadbPassword;
	private String mariadbPort;
	private String cassandraHost;
	private String cassandraPort;
	private String mariadbHost;
	private String mariadbUrl;
	private String mariadbDriverName;
	private String mariaTestDb;
	private String mariadbLocalPort;

	private Connection mariadbConnection;

	@PostConstruct @Override public void init() {
		super.init();
		Arrays.asList(mariadbHost, cassandraHost).forEach(t -> remoteConnector.addSession(t));
	}

	@Override public void afterPropertiesSet() {
		remoteConnector.enablePortForwarding(Integer.valueOf(mariadbLocalPort), mariadbHost, Integer.valueOf(mariadbPort));
		//executeCommand("drop database if exists "+getMariaTestDb());
		//executeCommand("create database if not exists "+getMariaTestDb());
	}

	@PreDestroy public void cleanup() {
		//executeCommand("drop database if exists "+getMariaTestDb());
		try {
			if (isNotEmpty(mariadbConnection) && !mariadbConnection.isClosed()) {
				synchronized (this) {
					if (isNotEmpty(mariadbConnection) && !mariadbConnection.isClosed())
						mariadbConnection.close();
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			shutdownApp();
		}
		Arrays.asList(mariadbHost, cassandraHost).forEach(t -> remoteConnector.destroySession(t));
		super.cleanup();
	}

	public void executeCommand(String command) {
		Statement stmt = null;
		try {
			loadMariaDBConnection();

			log.info("Executing command , {}"+command);
			stmt = mariadbConnection.createStatement();

			stmt.executeUpdate(command);
			log.info("Command executed successfully , {}"+command);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (isNotEmpty(stmt) || !stmt.isClosed())
					stmt.close();
			}catch (SQLException e){
				log.error(e.getMessage(),e);
			}
		}

	}

	public void executeScript(InputStream inputStream) throws IOException {
		InputStreamReader reader = null;

		try {
			loadMariaDBConnection();

			ScriptRunner runner = new ScriptRunner(mariadbConnection, false, false);
			reader = new InputStreamReader(inputStream);
			runner.runScript(reader);
			log.info("SQL inputStream executed successfully, {}"+inputStream);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			inputStream.close();
		} finally {
			if (isNotEmpty(reader))
				reader.close();
		}
	}

	private void loadMariaDBConnection()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (isNotEmpty(mariadbConnection))  return;

			synchronized (this) {
				if(isNotEmpty(mariadbConnection)) return;
				Class.forName(mariadbDriverName).newInstance();
				if (isEmpty(mariadbConnection) || mariadbConnection.isClosed())
					mariadbConnection = DriverManager.getConnection(getMariadbUrl(), mariadbUsername, mariadbPassword);
					log.info("Database connection established");
			}
	}
}
