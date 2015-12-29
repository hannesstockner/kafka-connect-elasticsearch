package com.hannesstockner.connect.es;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class OpenRecipePublisher {

  public static void main(String[] args) throws Exception {

    final OpenRecipePublisher publisher = new OpenRecipePublisher();
    publisher.publish();
  }

  private void publish() throws Exception {
    final String host = "docker";
    final int port = 9092;

    final File inputFile = new File(getClass().getClassLoader().getResource("openrecipes2.json").getFile());

    final String topic = "recipes";

    final Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host + ":" + port);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    final KafkaProducer<String, String> producer = new KafkaProducer<>(props);

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
      String line;
      while ((line = br.readLine()) != null) {
        final ProducerRecord<String, String> record = new ProducerRecord<>(topic, line);
        //in production this should be async will callback...
        producer.send(record).get();
      }
    } catch (Exception ex) {
      System.out.println(ex);
    }

  }
}
