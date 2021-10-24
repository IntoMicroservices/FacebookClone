#!/bin/bash

source "$PROJECT_ROOT"/scripts/functions.sh


DOCKER_DIR=$PROJECT_ROOT/docker

if [ "$1" == "up" ]; then
  docker-compose -f "$DOCKER_DIR"/local-registry.yml -p local-registry up -d
  launchBrowser http://localhost:7080 docker-registry-browser &
elif [ "$1" == "down" ]; then
  docker-compose -f "$DOCKER_DIR"/local-registry.yml -p local-registry down -v
else
  echo "Usage: $(basename $0) <up|down>"
  exit 1;
fi


