#!/bin/bash

SCRIPT_DIR=$(dirname $(readlink -f $0 || greadlink -f $0))
echo $SCRIPT_DIR
#docker-compose -f $SCRIPT_DIR/local-registry.yml -p local-registry up -d