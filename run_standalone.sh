#!/bin/sh

CLASSPATH=target/*-jar-with-dependencies.jar
exec java -Dlog4j.configuration=file:/Users/hannes.stockner/Documents/tools/kafka_2.10-0.9.0.0/config/connect-log4j.properties -cp $CLASSPATH org.apache.kafka.connect.cli.ConnectStandalone connect-standalone.properties connect-elasticsearch-sink.properties