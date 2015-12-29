package com.hannesstockner.connect.es;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ElasticsearchSinkTask extends SinkTask {

  private static final Logger log = LoggerFactory.getLogger(ElasticsearchSinkTask.class);

  private String indexPrefix;
  private final String TYPE = "kafka";

  private Client client;

  @Override
  public void start(Map<String, String> props) {
    final String esHost = props.get(ElasticsearchSinkConnector.ES_HOST);
    indexPrefix = props.get(ElasticsearchSinkConnector.INDEX_PREFIX);
    try {
      client = TransportClient
        .builder()
        .build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), 9300));

      client
        .admin()
        .indices()
        .preparePutTemplate("kafka_template")
        .setTemplate(indexPrefix + "*")
        .addMapping(TYPE, new HashMap<String, Object>() {{
          put("date_detection", false);
          put("numeric_detection", false);
        }})
        .get();
    } catch (UnknownHostException ex) {
      throw new ConnectException("Couldn't connect to es host", ex);
    }
  }

  @Override
  public void put(Collection<SinkRecord> records) {
    for (SinkRecord record : records) {
      log.info("Processing {}", record.value());

      log.info(record.value().getClass().toString());

      client
        .prepareIndex(indexPrefix + record.topic(), TYPE)
        .setSource(record.value().toString())
        .get();
    }
  }

  @Override
  public void flush(Map<TopicPartition, OffsetAndMetadata> offsets) {
  }

  @Override
  public void stop() {
    client.close();
  }

  @Override
  public String version() {
    return new ElasticsearchSinkConnector().version();
  }
}
