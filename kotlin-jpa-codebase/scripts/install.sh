#!/bin/sh

emma_check=`ps -ef | grep -v "grep" | grep "docker" | wc -l`
echo $emma_check
if [ $emma_check -lt 3 ]; then
    echo "Docker is not running"
    exit 1
fi



echo "==========================================="
echo "Install Start"
echo "==========================================="

echo '                          (0%)\r'
sleep 1
project_path=$(pwd)/docker
cd ${project_path}
docker-compose -p kotlin-jpa-codebase -f docker-compose.yml up -d

echo '##########                     (33%)\r'
sleep 1

echo '##########################             (66%)\r'
sleep 1

echo '##############################################   (100%)\r'
sleep 5
echo '\n'



echo "==========================================="
echo "Data Settig"
echo "==========================================="

echo '                          (0%)\r'
sleep 1

INDEX_NAME="bkjeon_index"
SETTINGS='{
  "settings" : {
    "index" : {
      "number_of_shards" : 3,
      "number_of_replicas" : 1
    }
  },
  "mappings": {
    "properties": {
      "title": { "type": "text" },
      "content": { "type": "text" },
      "author": { "type": "keyword" },
      "age": { "type": "integer" },
      "reg_date": { "format": "yyyy-MM-dd HH:mm:ss||yyyy/MM/dd||epoch_millis", "type": "date" },
      "tags": { "type": "keyword" }
    }
  }
}'
curl -X PUT "http://localhost:9200/$INDEX_NAME" -H 'Content-Type: application/json' -d "$SETTINGS"

echo '##########################             (66%)\r'
sleep 1

for var in {1..30}
do
  curl -X POST "http://localhost:9200/$INDEX_NAME/_doc/$var" -H 'Content-Type: application/json' -d '{
    "title": "Elasticsearch'"$var"'",
    "content": "You know, for Search '"$var"'",
    "author": "bkjeon1",
    "age": '"$var"',
    "reg_date": "'"$(date +"%Y-%m-%d %H:%M:%S")"'",
    "tags": ["elasticsearch", "search"]
  }'
done
for var in {31..62}
do
  curl -X POST "http://localhost:9200/$INDEX_NAME/_doc/$var" -H 'Content-Type: application/json' -d '{
    "title": "Elasticsearch'"$var"'",
    "content": "You know, for Search '"$var"'",
    "author": "bkjeon2",
    "age": '"$var"',
    "reg_date": "2024-06-11 14:45:01",
    "tags": ["elasticsearch", "search"]
  }'
done
for var in {63..115}
do
  curl -X POST "http://localhost:9200/$INDEX_NAME/_doc/$var" -H 'Content-Type: application/json' -d '{
    "title": "Elasticsearch'"$var"'",
    "content": "You know, for Search '"$var"'",
    "author": "bkjeon3",
    "age": '"$var"',
    "reg_date": "2024-06-10 14:45:01",
    "tags": ["elasticsearch", "search"]
  }'
done
echo " Index $INDEX_NAME created successfully with settings."

echo '##############################################   (100%)\r'
sleep 1
echo '\n'



echo "==========================================="
echo "FINISHED"
echo "==========================================="