#!/bin/bash


#remove our images from local docker cache
docker rmi $(docker images --filter=reference="localhost:5000/*" -q)