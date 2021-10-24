#!/bin/bash

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && (pwd -W 2> /dev/null || pwd))

source "$SCRIPT_DIR"/functions.sh
DOCKER_DIR="$SCRIPT_DIR"/../docker

function feedRedis {
  if [[ -n "$1" ]]; then
     waitForHealthyContainer "$1"
     docker run --rm -d \
     --network="container:redis" \
     --mount type=bind,source="${DOCKER_DIR}"/redis-data.txt,target=/tmp/redis-data.txt \
     redis \
     sh -c "cat /tmp/redis-data.txt | redis-cli -h redis -p 6379 --pipe"
   else
      echo "redis container name missing"
      exit 1;
   fi
 }

if [ "$1" == "up" ]; then
  docker-compose -f "$DOCKER_DIR"/microservice_infrastructure.yml -p microservice_infrastructure up -d || exit
  launchBrowser 'http://localhost:10010/add/?name=local_redis\&host=redis\&port=6379' redisinsight &
  launchBrowser http://localhost:10020/actuator/health config-server &
  launchBrowser http://localhost:10020/dummy-service/default config-server &
  launchBrowser http://localhost:10030 service-registry &
  feedRedis redis &
elif [ "$1" == "down" ]; then
  docker-compose -f "$DOCKER_DIR"/microservice_infrastructure.yml -p microservice_infrastructure down -v || exit
elif [ "$1" == "build" ]; then
#images are pushed to local registry
  (cd "$SCRIPT_DIR"/.. || exit; mvn -pl config-server,service-registry -am install -P docker-images || exit)
else
  echo "Usage: $(basename "$0") <build|up|down>"
  exit 1;
fi

