#!/bin/sh

mvn clean package

CONNECT_PROCESS_FILE=connect-standalone.properties
CONNECT_FILE_SOURCE=connect-file-source.properties
CONNECT_ES_SINK=connect-elasticsearch-sink.properties

CLASSPATH=target/*-jar-with-dependencies.jar
exec java -cp $CLASSPATH org.apache.kafka.connect.cli.ConnectStandalone $CONNECT_PROCESS_FILE $CONNECT_FILE_SOURCE $CONNECT_ES_SINK
