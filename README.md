# Kafka Connect Elasticsearch

kafka-connect-elasticsearch is a Kafka Connector for loading data from Kafka into Elasticsearch.

# Prerequisites

- a Linux console or Apple OSX console (not tested on windows, but adaptable with little effort)
- a Git client to fetch the project
- Docker Compose
- Apache Maven installed.
- git clone https://github.com/hannesstockner/kafka-connect-elasticsearch kafka-connect-elasticsearch

# Quickstart

1. mvn clean package
2. open a console and export env variable
```
export DOCKER_IP={YOUR_DOCKER_IP_ADDRESS}
```
3. start docker containers
```
docker-compose up
```
3. open another console window and export DOCKER_IP env variable (same as 2.)
4. run connector
```
./run_standalone.sh
```
5. go to http://{YOUR_DOCKER_IP_ADDRESS}:9200/kafka_recipes/_search to check your imported recipes

