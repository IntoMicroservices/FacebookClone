#!/bin/bash

 function waitForHealthyContainer {
   if [[ -n "$1" ]]
     then
       MAX_TRIES=60
       if [[ -n "$2" ]]
       then
         MAX_TRIES=$2
       fi
       COUNTER=0
       while [ "$(docker inspect -f {{.State.Health.Status}} "$1")" != "healthy" ]; do
           ((COUNTER+=1))
           if [[ $COUNTER -gt $MAX_TRIES ]]; then
               break
           fi
           sleep 1;
       done;
   fi
 }

 function feedRedis {
   waitForHealthyContainer redis
   docker run --rm -d \
   --network="container:redis" \
   --mount type=bind,source="${PROJECT_ROOT}"/docker/redis-data.txt,target=/tmp/redis-data.txt \
   redis \
   sh -c "cat /tmp/redis-data.txt | redis-cli -h redis -p 6379 --pipe"
 }

 function launchBrowser {
   case "$OSTYPE" in
#     solaris*) echo "SOLARIS" ;;
     darwin*)
#       echo "OSX"
        COMMAND="open"
        ;;
     linux*)
#       echo "LINUX"
        COMMAND="xdg-open"
       ;;
#     bsd*)     echo "BSD" ;;
     msys*)
#       echo "WINDOWS"
        COMMAND="start"
        ;;
     cygwin*)
#       echo "ALSO WINDOWS"
        COMMAND="start"
       ;;
     *)
#       echo "unknown: $OSTYPE"
       ;;
   esac

    if [[ -n "$COMMAND" ]]; then
        waitForHealthyContainer "$2" 60
        eval "$COMMAND $1"
    fi   
 }