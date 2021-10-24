#!/bin/bash
set -e

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && (pwd -W 2> /dev/null || pwd))

echo "$SCRIPT_DIR"

( echo "SUBSHELL PID: $$" )
echo "PID: $$"

(
# Inside parentheses, and therefore a subshell . . .
while [ true ]  # Endless loop.
do
  true
#  echo "Subshell running . . ."
done
)
  echo "PID: $$"