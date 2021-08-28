#!/bin/bash

./docker/local-registry-up.sh
mvn clean install
./docker/local-registry-down.sh