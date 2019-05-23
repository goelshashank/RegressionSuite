package com.dhisco.regression.services.config.db;

import com.dhisco.persistence.config.BasePersistenceConfig;
import com.dhisco.persistence.config.LoadPersistenceConfig;
import com.dhisco.persistence.repository.ProductPushCoreDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 22-05-2019
 */
@Import(LoadPersistenceConfig.class)
@Lazy @Log4j2 @Getter @Setter @ToString(includeFieldNames = true)
@Configuration
public class CassandraConfig {
	@Autowired private ProductPushCoreDAO productPushCoreDAO;
	@Autowired private BasePersistenceConfig basePersistenceConfig;

	public void clearDB(){
		String keySpace=basePersistenceConfig.getKeySpace();
		String script1= "DROP KEYSPACE IF EXISTS " + keySpace + ";";
		String script2="   CREATE KEYSPACE "+keySpace
				+ " WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};";
		Arrays.asList(script1,script2).forEach(t->productPushCoreDAO.getCassandraTemplate().getCqlOperations().execute(t));
		log.info("(((( Dropped and created Cassandra keyspace - {} ))))",keySpace);
	}
}
