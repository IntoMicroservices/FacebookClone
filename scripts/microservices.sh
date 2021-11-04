#!/bin/bash

SCRIPTS_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && (pwd -W 2> /dev/null || pwd))
DOCKER_DIR="$SCRIPTS_DIR"/../docker

source "$SCRIPTS_DIR"/functions.sh

if [ "$1" == "up" ]; then
  docker-compose -f "$DOCKER_DIR"/microservices.yml pull -q && docker-compose -f "$DOCKER_DIR"/microservices.yml \
    -p microservices up -d

#  launchBrowser http://localhost:9020/actuator/health user-service &
#  launchBrowser http://localhost:9010/actuator/health dummy-service &


launchBrowser http://localhost:9000/getAB dummya-mono-service &
launchBrowser http://localhost:9010/getB dummyb-service &

elif [ "$1" == "down" ]; then
    docker-compose -f "$DOCKER_DIR"/microservices.yml -p microservices down -v
elif [ "$1" == "build" ]; then
#images are pushed to local registry
  (cd "$SCRIPTS_DIR"/.. || exit; mvn -pl dummya-mono-app,dummyb-app -am install -P docker-images || exit)

else
    echo "Usage: $(basename "$0") <up|down>"
    exit 1;
fi
