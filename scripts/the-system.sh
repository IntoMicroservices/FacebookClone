#!/bin/bash

source "$PROJECT_ROOT"/scripts/functions.sh
DOCKER_DIR=$PROJECT_ROOT/docker

if [ "$1" == "up" ]; then
  docker-compose -f "$DOCKER_DIR"/the-system.yml pull -q && docker-compose -f "$DOCKER_DIR"/the-system.yml \
    -p the-system up -d
  launchBrowser http://localhost:9010/actuator/health config-server &
  launchBrowser http://localhost:9020/actuator/health user-service &
  launchBrowser http://localhost:9999/actuator/health dummy-service &
  launchBrowser 'http://localhost:9050/add/?name=local_redis\&host=redis\&port=6379' redisinsight &

elif [ "$1" == "down" ]; then
    docker-compose -f "$DOCKER_DIR"/the-system.yml -p the-system down -v
else
    echo "Usage: $(basename "$0") <up|down>"
    exit 1;
fi