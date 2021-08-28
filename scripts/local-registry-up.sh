#!/bin/bash

SCRIPT_DIR=$(dirname $(greadlink -f $0 2> /dev/null || readlink -f $0 2> /dev/null))
echo $SCRIPT_DIR
#docker-compose -f $SCRIPT_DIR/local-registry.yml -p local-registry up -d