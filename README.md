# Kafka Connect Elasticsearch

kafka-connect-elasticsearch is a Kafka Connector for loading data from Kafka into Elasticsearch.

# Prerequisites

- a Linux console or Apple OSX console (not tested on windows, but adaptable with little effort)
- a Git client to fetch the project
- Docker Compose
- Apache Maven installed.
- git clone https://github.com/hannesstockner/kafka-connect-elasticsearch kafka-connect-elasticsearch

# Quickstart

Build a package of the code:
```
mvn clean package
```
Open a console and export env variable:
```
export DOCKER_IP={YOUR_DOCKER_IP_ADDRESS}
```
Start docker containers:
```
docker-compose up
```
Open another console window and export DOCKER_IP env variable

Run connector:
```
./run_standalone.sh
```
Go to http://{YOUR_DOCKER_IP_ADDRESS}:9200/kafka_recipes/_search to check your imported recipes

