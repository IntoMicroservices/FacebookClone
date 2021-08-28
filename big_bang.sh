#!/bin/bash
set -e

PROJECT_ROOT="$(pwd)"

source ./scripts/local-registry.sh up
mvn clean install
source ./scripts/local-registry.sh down