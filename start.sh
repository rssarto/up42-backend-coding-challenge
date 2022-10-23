#!/bin/bash

echo "###########################################################"
echo "Init procedures to start up42 backend challenge code"
echo "###########################################################"

echo
echo "--> Building the application code..."
echo
./gradlew clean build -x test

echo
echo "--> Cleaning previous mongodb docker container running"
echo
mongodbContainerId="$(docker ps | grep :27017 | awk '{print $1}')"
if [[ ! -z "$mongodbContainerId" ]]; then
  echo "killing mongodb container $mongodbContainerId"
  docker rm -f $mongodbContainerId
fi

echo 
echo "--> Running tests..."
echo
./gradlew test

echo
echo "--> Starting mongodb container..."
echo
docker run -d -p 27017:27017 --name up42-mongo-instance mongo:4.4.2

echo
echo "--> Starting Up42 challenge application..."
echo
java -jar ./build/libs/backend-coding-challenge-0.0.1-SNAPSHOT.jar


