#!/bin/bash
set -e

PROJECT_ROOT="$(pwd)"

source ./scripts/local-registry.sh up
mvn clean install -P docker-images

source ./scripts/the-system.sh up
read -p "************ Press enter to shutdown and cleanup everything ******************"
source ./scripts/the-system.sh down

#remove our images from local docker cache
docker rmi $(docker images --filter=reference="localhost:5000/*" -q)

source ./scripts/local-registry.sh down