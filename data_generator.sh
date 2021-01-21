#!/bin/bash
declare -a listOfStrings=("a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p" "q" "r" "s" "t" "u" "v" "w" "x" "y" "z")

RESULT=()

i=1
param=$1
max="${param:=10}"

while [ "$i" -le "$max" ]
do
	RESULT+=("${listOfStrings[$RANDOM % ${#listOfStrings[@]}]}")
	i=$(($i + 1))
done

RESULT=($(for l in ${RESULT[@]}; do echo $l; done | sort))

(IFS=,; echo "${RESULT[*]}" > test_data.txt)
