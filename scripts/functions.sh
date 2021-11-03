#!/bin/bash

 function waitForHealthyContainer {
   if [[ -n "$1" ]]
     then
       MAX_TRIES=10
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
           sleep 5;
       done;
   fi
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
        waitForHealthyContainer "$2" 10
        eval "$COMMAND $1"
    fi   
 }