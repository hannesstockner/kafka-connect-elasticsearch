package com.hannesstockner.connect.es;

import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticsearchSinkConnector extends SinkConnector {

  public static final String ES_HOST = "es.host";
  public static final String INDEX_PREFIX = "index.prefix";

  private String esHost;
  private String indexPrefix;

  @Override
  public String version() {
    return AppInfoParser.getVersion();
  }

  @Override
  public void start(Map<String, String> props) {
    esHost = props.get(ES_HOST);
    indexPrefix = props.get(INDEX_PREFIX);
  }

  @Override
  public Class<? extends Task> taskClass() {
    return ElasticsearchSinkTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int maxTasks) {
    ArrayList<Map<String, String>> configs = new ArrayList<>();
    for (int i = 0; i < maxTasks; i++) {
      Map<String, String> config = new HashMap<>();
      if (esHost != null)
        config.put(ES_HOST, esHost);
      if (indexPrefix != null)
        config.put(INDEX_PREFIX, indexPrefix);
      configs.add(config);
    }
    return configs;
  }

  @Override
  public void stop() {
    //not implemented
  }
}
