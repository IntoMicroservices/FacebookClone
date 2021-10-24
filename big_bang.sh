#!/bin/bash
set -e

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && (pwd -W 2> /dev/null || pwd))

source "$SCRIPT_DIR"/scripts/functions.sh

source "$SCRIPT_DIR"/scripts/local-registry.sh up
(cd "$SCRIPT_DIR"; mvn clean install -P docker-images)

source "$SCRIPT_DIR"/scripts/the-system.sh up

read -p "************ Press enter to shutdown and cleanup everything ******************"
source "$SCRIPT_DIR"/scripts/the-system.sh down

#remove our images from local docker cache
docker rmi $(docker images --filter=reference="localhost:5000/*" -q)

source "$SCRIPT_DIR"/scripts/local-registry.sh down