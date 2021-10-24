#!/bin/bash
set -e

PROJECT_ROOT=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && (pwd -W 2> /dev/null || pwd))

source "$PROJECT_ROOT"/scripts/functions.sh

source "$PROJECT_ROOT"/scripts/local-registry.sh up
(cd "$PROJECT_ROOT"; mvn clean install -P docker-images)

source "$PROJECT_ROOT"/scripts/the-system.sh up

read -p "************ Press enter to shutdown and cleanup everything ******************"
source "$PROJECT_ROOT"/scripts/the-system.sh down

#remove our images from local docker cache
docker rmi $(docker images --filter=reference="localhost:5000/*" -q)

source "$PROJECT_ROOT"/scripts/local-registry.sh down