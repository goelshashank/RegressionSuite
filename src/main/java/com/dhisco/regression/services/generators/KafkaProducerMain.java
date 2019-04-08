package com.dhisco.regression.services.generators;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Log4j2 @Lazy
@Component public class KafkaProducerMain {

	public void publishData(String topicName, KafkaProducer producer, List<String> filePaths) {

		for (String filePath : filePaths) {
			File otafile=new File(filePath);
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(otafile);
				byte[] data = new byte[(int) otafile.length()];

				try {
					fis.read(data);
					String str = new String(data, "UTF-8");
			
				ProducerRecord<String, String> record;
				
						record = new ProducerRecord<>(topicName, str);
					producer.send(record, new Callback() {
						@Override public void onCompletion(RecordMetadata recordMetadata, Exception e) {
							if (e == null)
								log.info(
										recordMetadata.toString() + ", " + "Partition: " + recordMetadata.partition()
												+ "Offset: " + recordMetadata.offset());
							else
								log.error("Error while producing" + e);

						}
					});
				} catch (IOException e) {
					log.debug("OTA file path {}",otafile.getAbsolutePath());
					log.error(e.getMessage(),e);
				}

			} catch (FileNotFoundException e) {
				log.error(e.getMessage(),e);
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
				}
			}
		}

		log.debug("Producer closed");

		producer.close();
	}
}

