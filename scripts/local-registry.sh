#!/bin/bash

# for macs do
# brew install coreutils
# to get greadlink
SCRIPT_DIR=$(dirname $(greadlink -f $0 2> /dev/null || readlink -f $0 2> /dev/null))
DOCKER_DIR=$SCRIPT_DIR/../docker

if [ "$1" == "up" ]; then
  docker-compose -f $DOCKER_DIR/local-registry.yml -p local-registry up -d
else if [ "$1" == "down" ]; then
  docker-compose -f $DOCKER_DIR/local-registry.yml -p local-registry down -v
else
  echo "Usage: `basename $0` <up|down>"
  exit 1;
fi
fi

