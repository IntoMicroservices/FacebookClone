#!/bin/bash

source "$PROJECT_ROOT"/scripts/functions.sh
DOCKER_DIR=$PROJECT_ROOT/docker

if [ "$1" == "up" ]; then
  docker-compose -f "$DOCKER_DIR"/the-system.yml pull -q && docker-compose -f "$DOCKER_DIR"/the-system.yml \
    -p the-system up -d

  launchBrowser 'http://localhost:10010/add/?name=local_redis\&host=redis\&port=6379' redisinsight &
  launchBrowser http://localhost:10020/actuator/health config-server &
  launchBrowser http://localhost:10020/dummy-service/default config-server &
  launchBrowser http://localhost:9020/actuator/health user-service &
  launchBrowser http://localhost:9010/actuator/health dummy-service &
  launchBrowser http://localhost:10030 service-registry &
  feedRedis redis &

elif [ "$1" == "down" ]; then
    docker-compose -f "$DOCKER_DIR"/the-system.yml -p the-system down -v
else
    echo "Usage: $(basename "$0") <up|down>"
    exit 1;
fi