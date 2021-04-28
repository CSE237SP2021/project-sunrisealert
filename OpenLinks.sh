#!/bin/bash
echo "Type your OS: 'windows', 'mac', 'linux'"
read os

if [[ "$os" == "windows" ]]; then
	while read y 
	do
		start $y
	done < src/main/java/links.txt 

elif [[ "$os" == "linux" ]]; then 
	while read y 
	do
		xdg-open $y
	done < src/main/java/links.txt

elif [[ "$os" == "mac" ]]; then 
	while read y 
	do
		open $y
	done < src/main/java/links.txt

elif [[ -z "$os" ]]; then
	echo "Please type your OS"

else 
	echo "Name of OS not recognized. Did you spell it correctly?"
fi
