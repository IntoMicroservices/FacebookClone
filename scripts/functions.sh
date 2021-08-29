#!/bin/bash

 function lauchBrowser {
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
        eval "$COMMAND $1"
    fi   


 }