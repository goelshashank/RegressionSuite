package com.dhisco.regression.services.generators;

import com.dhisco.ptd.dj.util.PushCoreJsonWithChannel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Log4j2
@Lazy
@Component
public class CustomKafkaConsumer {

	ObjectMapper mapper = new ObjectMapper();

	private PushCoreJsonWithChannel getPushCoreJsonFromString(String value) {
		try {
			return mapper.readValue(value, PushCoreJsonWithChannel.class);
		}catch(Exception e) {
			//LOGGER.error("exception mapping kafka record to pushcore JSON ", e);
		}
		return null;
	}

	private void putRemainingTopics( Set<String> topics, Map<String, List<PushCoreJsonWithChannel>> input ){
		topics.forEach( t -> {
			if( !input.containsKey( t ) ){
				input.put( t, new ArrayList<>() );
			}
		} );
	}

	public Map<String, List<PushCoreJsonWithChannel>> fetchData(Set<String> topics, KafkaConsumer consumer, int timeOut ) {
		consumer.subscribe(topics);

		Map<String, List<PushCoreJsonWithChannel>> result = new HashMap<String, List<PushCoreJsonWithChannel>>();
		String topic, value;
		List<PushCoreJsonWithChannel> list;

		long stopNano = System.nanoTime()+ TimeUnit.SECONDS.toNanos(timeOut);
		try {
			while ( System.nanoTime() < stopNano ) {
				ConsumerRecords<String, String> records = consumer.poll(100 );
				for (ConsumerRecord<String, String> record : records)
				{
					topic = record.topic();
					value = record.value();
					PushCoreJsonWithChannel pushCoreJson = getPushCoreJsonFromString( value );
					log.info( topic + ":" + value );
					list = result.get( topic );
					if( list == null ){
						list = new ArrayList<>();
					}
					list.add( pushCoreJson );
					result.put( topic, list );
				}
			}
		} finally {
			//    consumer.close();
		}
		putRemainingTopics( topics, result );
		return result;
	}
	
	
	public Map<String, List<String>> readDataFromTopic(List<String> topics, KafkaConsumer consumer, int timeOut ) {
		consumer.subscribe(topics);

		Map<String, List<String>> result = new HashMap<String, List<String>>();
		String topic, value;
		List<String> list;

		long stopNano = System.nanoTime()+ TimeUnit.SECONDS.toNanos(timeOut);
		try {
			while ( System.nanoTime() < stopNano ) {
				ConsumerRecords<String, String> records = consumer.poll(100 );
				for (ConsumerRecord<String, String> record : records)
				{
					topic = record.topic();
					value = record.value();
					log.info( topic + ":" + value );
					list = result.get( topic );
					if( list == null ){
						list = new ArrayList<>();
					}
					list.add(value);
					result.put( topic, list );
				}
			}
		} finally {
			consumer.close();
		}
		return result;
	}
	
	
}
