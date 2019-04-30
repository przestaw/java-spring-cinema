#!/bin/bash
current_dir=$PWD

# note : SCRIPT IS TEMPORARY

command[1]='curl -X GET localhost:8080/movie/all'
desc[1]='Listing all movies : '
command[2]='curl -X GET localhost:8080/screening/all'
desc[2]='listing all screenings : '
command[3]='curl -X GET localhost:8080/prices'
desc[3]='Check prices of the tickets : '
command[4]="curl -X GET localhost:8080/screening?begin=2019-05-13%2018:30&end=2019-05-14%2010:30"
desc[4]='list screenings in given interval from 2019-05-13 18:30 to 2019-05-14 9:30'
command[5]="curl -X GET localhost:8080/screening/9/emptyplaces"
desc[5]='list screening nr 9 seats'
#command[6]="curl -H Content-Type:application/json -X POST -d {\"screeningId\":9,\"name\":\"Przemysław\",\"surname\":\"Stawczyk\",\"places\":[{\"row\":1,\"column\":3,\"type\":1},{\"row\":1,\"column\":4,\"type\":3}]} localhost:8080/screening/1/reservation"
#desc[6]='Add reservation for two seats [name with polish character ł]'
command[6]='curl -X GET localhost:8080/reservation/2'
desc[6]='Test : res_id = 2'

#command[7]="curl -X POST -H Content-Type:application/json -d {\"movie\":\"Madagaskar\",\"room\":4,\"date\":\"2019-05-15T10:30\"} localhost:8080/screening/new"
#desc[7]="add new screening of the 'Madagaskar' movie on 15th May at 10:30"

echo -n "Example use case n.o. 1"
printf '\n\n'
#echo $current_dir

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
#curl -i -X POST -H 'Content-Type: application/json' -d '{"screeningId":1,"name":"Przemysław","surname":"Stawczyk","places":[{"row":1, "column":3, "type":1}, {"row":1,"column":4,"type":3}]}' localhost:8080/screening/1/reservation
#curl -i -X POST -H 'Content-Type: application/json' -d '{"movie": "The Shining", "room": 4, "date" : "2019-05-14T10:30"}' localhost:8080/screening/new