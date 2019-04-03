package com.dhisco.config.db;

import com.dhisco.config.BaseConfig;
import static com.dhisco.util.CommonUtils.*;
import com.dhisco.util.RemoteConnector;
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
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Lazy @Log4j2
@Getter @Setter @ToString(includeFieldNames = true)  @Configuration @PropertySource("db.properties") @ConfigurationProperties(prefix = "db") public class DbConfig
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

	private Connection mariadbConnection;

	@PostConstruct
	@Override public void init() {
		super.init();
		Arrays.asList(mariadbHost,cassandraHost).forEach(t-> remoteConnector.addSession(t));
	}

	@Override public void afterPropertiesSet() {
		remoteConnector.enablePortForwarding(Integer.valueOf(mariadbPort),mariadbHost,Integer.valueOf(mariadbPort));
	}

	@PreDestroy public void cleanup() {
		Arrays.asList(mariadbHost,cassandraHost).forEach(t-> remoteConnector.destroySession(t));
		try {
			if (isNotEmpty(mariadbConnection) && !mariadbConnection.isClosed()) {
				synchronized (this) {
					if (isNotEmpty(mariadbConnection) && !mariadbConnection.isClosed())
						mariadbConnection.close();
				}
			}
		}catch (SQLException e){
			log.error(e.getMessage(),e);
			shutdownApp();
		}
		super.cleanup();
	}

	public String getMariadbUrl() {
		return mariadbUrl.replace("mariadbHostVal", mariadbHost).replace("mariadbPortVal", mariadbPort);
	}

	public void executeScript(String file) throws  IOException{
		InputStreamReader reader=null;

		try {
			Class.forName(mariadbDriverName).newInstance();
			if(isEmpty(mariadbConnection)){
				synchronized (this){
					if(isEmpty(mariadbConnection) || mariadbConnection.isClosed() )
					mariadbConnection=DriverManager.getConnection(getMariadbUrl(), mariadbUsername, mariadbPassword);
				}
			}
			System.out.println("Database connection established");

			ScriptRunner runner=new ScriptRunner(mariadbConnection, false, false);
			 reader = new InputStreamReader(new FileInputStream(file));
			runner.runScript(reader);
			reader.close();
		} catch (Exception e){
			log.error(e.getMessage(),e);
		} finally {
				if (isNotEmpty(reader))
					reader.close();
		}
	}
}
