#!/bin/sh
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
sleep 1
echo '\n'

echo "==========================================="
echo "FINISHED"
echo "==========================================="