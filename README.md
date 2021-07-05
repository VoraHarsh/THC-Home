# THC-Home

This is THC-Home Application

## To Start ZooKeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

## To Start Kafka Server
.\bin\windows\kafka-server-start.bat .\config\server.properties

## To Create Topic
.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic store-topic


## To list Topics
.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092