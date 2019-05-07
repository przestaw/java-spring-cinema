Cinema Ticket Booking App
========================================================

Description
--------------------------------------------------------
Spring boot aplication with RESTful api for cinema ticket booking app.

This is my first app taking use of SPRING, JDBC database and RESTful api. Used database is HyperSQL. Project uses gradle to build and require at least java version 8 [tested with openjdk8]

Before launching test demo you must:
1. build and start the application using script *compile_and_run.sh* [*.jar file will be copied to root directory]
2. start desired script :
   -*demo_script_1.sh* contains 1 example use case -> checking movies screenings and making reservation
   -*demo_script_2.sh* contains 1 example use case -> #TODO
   -*demo_script_extra.sh* contains sdditional features -> adding new movie, new screenings, listing reservations
3. after running demo send shutdown signal to the spring boot app using CRTL + C   