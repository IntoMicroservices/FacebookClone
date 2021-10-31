#!/bin/bash
set -e

SCRIPT_DIRS=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && (pwd -W 2> /dev/null || pwd))/scripts

source "$SCRIPT_DIRS"/local-registry.sh up
source "$SCRIPT_DIRS"/microservice_infrastructure.sh build
source "$SCRIPT_DIRS"/microservice_infrastructure.sh up


read -p "************ Press enter to shutdown and cleanup everything ******************"


source "$SCRIPT_DIRS"/microservice_infrastructure.sh down
source "$SCRIPT_DIRS"/local-registry.sh down


#remove our images from local docker cache
#docker rmi $(docker images --filter=reference="localhost:5000/*" -q)