#!/bin/bash

DOCKER_DIR=$PROJECT_ROOT/docker

if [ "$1" == "up" ]; then
  docker-compose -f $DOCKER_DIR/the-system.yml pull && docker-compose -f $DOCKER_DIR/the-system.yml -p the-system up -d
else if [ "$1" == "down" ]; then
    docker-compose -f $DOCKER_DIR/the-system.yml -p the-system down -v
  else
    echo "Usage: `basename $0` <up|down>"
    exit 1;
  fi
fi


