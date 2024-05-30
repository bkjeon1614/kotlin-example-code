#!/bin/sh
echo "==========================================="
echo "Install Start"
echo "==========================================="

echo -ne '                          (0%)\r'
sleep 1
project_path=$(pwd)/docker
cd ${project_path}
docker-compose -f docker-compose.yml up &
wait

echo -ne '#####                     (33%)\r'
sleep 1

echo -ne '#############             (66%)\r'
sleep 1

echo -ne '#######################   (100%)\r'
sleep 1
echo -ne '\n'

echo "==========================================="
echo "FINISHED"
echo "==========================================="