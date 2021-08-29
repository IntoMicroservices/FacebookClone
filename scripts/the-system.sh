#!/bin/bash

source $PROJECT_ROOT/scripts/functions.sh
DOCKER_DIR=$PROJECT_ROOT/docker

if [ "$1" == "up" ]; then
  docker-compose -f $DOCKER_DIR/the-system.yml pull -q && docker-compose -f $DOCKER_DIR/the-system.yml -p the-system up -d
  lauchBrowser http://localhost:9080/actuator/health user-service &
  lauchBrowser http://localhost:9090/actuator/health dummy-service &
else if [ "$1" == "down" ]; then
    docker-compose -f $DOCKER_DIR/the-system.yml -p the-system down -v
  else
    echo "Usage: `basename $0` <up|down>"
    exit 1;
  fi
fi