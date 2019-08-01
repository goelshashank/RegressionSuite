package com.dhisco.regression.services.config.db;

import com.dhisco.ptd.dj.PushCoreJson;
import com.dhisco.ptd.dj.util.PushCoreJsonWithChannel;
import com.dhisco.regression.services.config.base.BaseConfig;
import com.dhisco.regression.services.generators.CustomKafkaConsumer;
import com.dhisco.regression.services.generators.KafkaProducerMain;
import com.google.common.base.Throwables;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.errors.InvalidTopicException;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static com.dhisco.regression.core.util.CommonUtils.isNotEmpty;
import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Lazy @Log4j2 @Getter @Setter @ToString(includeFieldNames = true) @Configuration @PropertySource("kafka.properties") @ConfigurationProperties(prefix = "kafka") public class KafkaConfig
		extends BaseConfig {

	@Autowired KafkaProducerMain kafkaProducerMain;
	@Autowired CustomKafkaConsumer customKafkaConsumer;

	private String stateDir;
	private String bootstrapServers;
	private String numKafkaStreamThreads;
	private String applicationId;
	private String numPartitions;
	private String replicationFactor;

	private Properties props;
	private Properties consumerProps;

	private KafkaProducer<String, String> producer;
	private KafkaConsumer<String, String> consumer;
	private AdminClient admin;
	private Set<String> topics;

	@PostConstruct @Override public void init() {
		super.init();
		props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
		props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, numKafkaStreamThreads);
		props.put(StreamsConfig.STATE_DIR_CONFIG, stateDir);
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		consumerProps = new Properties();
		consumerProps.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
		consumerProps.put( ConsumerConfig.GROUP_ID_CONFIG, "customConsumerProp");
		consumerProps.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class.getName());
		consumerProps.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class.getName());

	}


	@Override public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		producer = new KafkaProducer(props);
		consumer = new KafkaConsumer(consumerProps);
		admin = AdminClient.create(props);
		topics = Sets.newConcurrentHashSet();
	}

	public void createTopic(String topicName) {

		if (topics.contains(topicName))
			return;
		synchronized (topics) {
			if (topics.contains(topicName))
				return;
			CreateTopicsResult result = admin.createTopics(
					asList(new NewTopic(topicName, Integer.valueOf(numPartitions), Short.valueOf(replicationFactor))));
			logTopicCRUDData(result);
			topics.add(topicName);
		}

	}

	public void readTopic(String topicName) {
		synchronized (topics) {
			if (topics.contains(topicName))
				return;
			CreateTopicsResult result = admin.createTopics(
					asList(new NewTopic(topicName, Integer.valueOf(numPartitions), Short.valueOf(replicationFactor))));
			logTopicCRUDData(result);
			topics.add(topicName);
		}

	}


	public void publishData(String topicName, List<InputStream> inputStreamList) {
		kafkaProducerMain.publishData(topicName, producer, inputStreamList);
	}

	public Map<String, List<PushCoreJsonWithChannel>> consumeData(Set<String> iTopics, int timeout ){
		return customKafkaConsumer.fetchData( iTopics, consumer, timeout );
	}
	
	public Map<String, List<String>> consumeData(List<String> iTopics, int timeout ){
		return customKafkaConsumer.readDataFromTopic( iTopics, consumer, timeout );
	}

	public void deleteTopic(String topicName) {
		DeleteTopicsResult delete = admin.deleteTopics(asList(topicName));
		logTopicCRUDData(delete);
	}

	private void logTopicCRUDData(DeleteTopicsResult result) {
		for (Map.Entry<String, KafkaFuture<Void>> entry : result.values().entrySet()) {
			try {
				entry.getValue().get();
				log.info("topic {} deleted", entry.getKey());
			} catch (InterruptedException | ExecutionException e) {
				if (Throwables.getRootCause(e) instanceof InvalidTopicException) {
					log.error("topic {} invalid", entry.getKey());
				}
			}
		}
	}

	private void logTopicCRUDData(CreateTopicsResult result) {
		for (Map.Entry<String, KafkaFuture<Void>> entry : result.values().entrySet()) {
			try {
				entry.getValue().get();
				log.info("topic {} created" + entry.getKey());
			} catch (InterruptedException | ExecutionException e) {
				if (Throwables.getRootCause(e) instanceof TopicExistsException) {
					log.error("topic {} existed" + entry.getKey());
				}
			}
		}
	}


	@PreDestroy @Override public void cleanup() {
		if(isNotEmpty(producer))
			producer.close();

		super.cleanup();
	}

}
