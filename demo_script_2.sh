#!/bin/bash

command[1]='curl -X GET localhost:8080/prices'
desc[1]='Check prices of the tickets : '
command[2]="curl -X GET localhost:8080/screening?begin=2019-05-13%2018:30&end=2019-05-14%2010:30"
desc[2]='list screenings in given interval from 2019-05-13 18:30 to 2019-05-14 9:30'
command[3]="curl -X GET localhost:8080/screening/9/emptyplaces"
desc[3]='list screening nr 9 seats'
command[4]="curl -H Content-Type:application/json -X POST -d {\"screeningId\":2,\"name\":\"Przemysław\",\"surname\":\"Stawczyk\",\"places\":[{\"row\":1,\"column\":3,\"type\":1},{\"row\":1,\"column\":4,\"type\":3}]} localhost:8080/screening/1/reservation"
desc[4]='Add reservation for two seats [name with polish character ł]'
command[5]='curl -X GET localhost:8080/reservation/3'
desc[5]='Test : res_id = 3'
command[6]="curl -X GET localhost:8080/screening/9/emptyplaces"
desc[6]='list screening nr 9 seats again'

echo -n "Example use case n.o. 1"
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
#curl -i -X POST -H 'Content-Type: application/json' -d '{"screeningId":1,"name":"Przemysław","surname":"Stawczyk","places":[{"row":1, "column":3, "type":1}, {"row":1,"column":4,"type":3}]}' localhost:8080/screening/1/reservation