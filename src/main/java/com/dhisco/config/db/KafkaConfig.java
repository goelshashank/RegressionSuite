package com.dhisco.config.db;

import com.dhisco.config.BaseConfig;
import com.dhisco.generators.KafkaProducerMain;
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
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.errors.InvalidTopicException;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.serialization.Serdes;
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
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
@Lazy @Log4j2 @Getter @Setter @ToString(includeFieldNames = true) @Configuration @PropertySource("kafka.properties") @ConfigurationProperties(prefix = "kafka") public class KafkaConfig
		extends BaseConfig {

	@Autowired KafkaProducerMain kafkaProducerMain;

	private String stateDir;
	private String bootstrapServers;
	private String numKafkaStreamThreads;
	private String applicationId;
	private String numPartitions;
	private String replicationFactor;

	private Properties props;
	private KafkaProducer<String, String> producer;
	private AdminClient admin;
	private Set<String> topics;

	@Override public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		producer = new KafkaProducer(props);
		admin = AdminClient.create(props);
		topics = Sets.newConcurrentHashSet();
	}

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

	@PreDestroy @Override public void cleanup() {
		topics.stream().forEach(t -> {
			deleteTopic(t);
		});
		super.cleanup();
	}

	public void publishData(String topicName, List<String> listOfFiles) {
		kafkaProducerMain.publishData(topicName, producer, listOfFiles);
	}

	void deleteTopic(String topicName) {
		DeleteTopicsResult delete = admin.deleteTopics(asList(topicName));
		logTopicCRUDData(delete);
	}

	private void logTopicCRUDData(DeleteTopicsResult result) {
		for (Map.Entry<String, KafkaFuture<Void>> entry : result.values().entrySet()) {
			try {
				entry.getValue().get();
				log.debug("topic {} deleted", entry.getKey());
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
				log.debug("topic {} created" + entry.getKey());
			} catch (InterruptedException | ExecutionException e) {
				if (Throwables.getRootCause(e) instanceof TopicExistsException) {
					log.error("topic {} existed" + entry.getKey());
				}
			}
		}
	}

}
