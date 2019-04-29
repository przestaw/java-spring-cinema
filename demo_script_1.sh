#!/bin/bash
current_dir=$PWD

command[1]='curl -X GET localhost:8080/movie/all'
desc[1]='Listing all movies : '
command[2]='curl -X GET localhost:8080/screening/all'
desc[2]='listing all screenings : '
command[3]="curl -X GET localhost:8080/screening?begin=2019-12-12%2018:30&end=2019-12-14%2019:30"
desc[3]='list screenings in given interval'
command[4]="curl -H Content-Type:application/json -X POST -d {\"screeningId\":1,\"name\":\"Przemysław\",\"surname\":\"Stawczyk\",\"places\":[{\"row\":1,\"column\":3,\"type\":1},{\"row\":1,\"column\":4,\"type\":3}]} localhost:8080/screening/1/reservation"
desc[4]='Add reservation for two seats [name with polish character ł]'
command[5]="curl -X POST -H Content-Type:application/json -d {\"movie\":\"Madagaskar\",\"room\":4,\"date\":\"2019-05-15T10:30\"} localhost:8080/screening/new"
desc[5]="add new screening of the 'Madagaskar' movie on 15th May at 10:30"

echo -n "Example use case n.o. 1"
printf '\n\n'
#echo $current_dir

for i in {1..5}
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


