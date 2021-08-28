#!/bin/bash

./scripts/local-registry.sh up
mvn clean install
./scripts/local-registry.sh down