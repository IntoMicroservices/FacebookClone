#!/bin/bash
set -e

PROJECT_ROOT="$(pwd)"

source ./scripts/local-registry.sh up
mvn clean install -P docker-images

source ./scripts/the-system.sh up
read -p "Press enter to shutdown and cleanup everything"
source ./scripts/the-system.sh down

source ./scripts/local-registry.sh down