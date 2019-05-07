#!/bin/bash

command[1]='curl -X GET localhost:8080/movie/all'
desc[1]='Listing all movies : '
command[2]='curl -X POST localhost:8080/movie/new?title=Madagaskar'
desc[2]='Add new movie : '
command[3]='curl -X GET localhost:8080/movie/all'
desc[3]='Listing all movies containing new movie : '
command[4]='curl -X GET localhost:8080/screening/all'
desc[4]='listing all screenings : '
command[5]="curl -X POST -H Content-Type:application/json -d {\"movie\":\"Madagaskar\",\"room\":4,\"date\":\"2019-05-15T10:30\"} localhost:8080/screening/new"
desc[5]="add new screening of the 'Madagaskar' movie on 15th May at 10:30"
command[6]='curl -X GET localhost:8080/screening/all'
desc[6]='listing all screenings containing new screening : '

echo -n "Example use of additional features"
printf '\n\n'

for i in {1..6}
do
	printf "\t->now :\n"
	echo ${desc[$i]}
	printf "\t->used command :\n"
	echo ${command[$i]}
	printf "\t->result :\n"
	${command[$i]}
	printf '\n\n'
	read -n 1 -s -r -p "Press any key to continue to next endpoint"
	printf '\n\n'
done

#TEMP NOTES
#curl -i -X POST -H 'Content-Type: application/json' -d '{"movie": "The Shining", "room": 4, "date" : "2019-05-14T10:30"}' localhost:8080/screening/new
