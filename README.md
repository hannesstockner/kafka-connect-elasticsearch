# Kafka Connect Elasticsearch

kafka-connect-elasticsearch is a Kafka Connector for loading data from Kafka into Elasticsearch.   
More explanation can be found in the article [Kafka and Elastic Search, A Perfect Match](https://qbox.io/blog/kafka-and-elasticsearch-a-perfect-match-1).

# Prerequisites

- a Linux console or Apple OSX console (not tested on windows, but adaptable with little effort)
- a Git client to fetch the project
- Docker Compose
- Apache Maven installed.
- git clone https://github.com/hannesstockner/kafka-connect-elasticsearch kafka-connect-elasticsearch

# Quickstart

Start docker containers:
```
docker-compose up
```

Go to http://{YOUR_DOCKER_IP_ADDRESS}:9200/kafka_recipes/_search to check your imported recipes
