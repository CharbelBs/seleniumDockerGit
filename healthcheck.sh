#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER


echo "Checking if hub is ready to go - $HUB_HOST"

while [ "$( curl -s http://$HUB_HOST:4444/status | jq -r .value.ready )" != "true" ]

do
    sleep 1
done

# Start the Java command
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG testng.xml
