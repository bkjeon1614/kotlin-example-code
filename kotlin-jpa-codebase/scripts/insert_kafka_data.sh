#!/bin/sh

echo "==========================================="
echo "Data Setting (Kafka)"
echo "==========================================="

echo '                          (0%)\r'
sleep 1

echo "==========================================="
echo "Create Topic"
echo "==========================================="
docker exec -it kafka /bin/bash -c "kafka-topics.sh --create --topic bkjeon_topic --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092"

echo "==========================================="
echo "Topic List"
echo "==========================================="
docker exec -it kafka /bin/bash -c "kafka-topics.sh --bootstrap-server localhost:9092 --list"

echo '##############################################   (100%)\r'
sleep 1
echo '\n'

echo "==========================================="
echo "FINISHED (Kafka)"
echo "==========================================="