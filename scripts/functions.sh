#!/bin/bash

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

    if [[ ! -z "$COMMAND" ]]; then
        if [[ ! -z "$2" ]]
          then
            COUNTER=0
            while [ "$(docker inspect -f {{.State.Health.Status}} $2)" != "healthy" ]; do
                let COUNTER+=1;
                if [[ $COUNTER -gt 60 ]]; then
                    break
                fi
                sleep 2;
            done;
        fi

        eval "$COMMAND $1"
    fi   
 }