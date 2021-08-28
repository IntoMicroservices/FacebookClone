#!/bin/bash
SCRIPT_DIR=$(dirname $(readlink -f $0))
docker-compose -f $SCRIPT_DIR/local-registry.yml -p local-registry down -v