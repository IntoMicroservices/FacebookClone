#!/bin/bash

SCRIPTS_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && (pwd -W 2> /dev/null || pwd))
DOCKER_DIR="$SCRIPTS_DIR"/../docker

source "$SCRIPTS_DIR"/functions.sh

