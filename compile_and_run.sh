#!/bin/bash

gradle build
cp ./build/libs/SpringCinema-0.5.1.jar ./SpringCinema.jar
java -jar ./SpringCinema.jar