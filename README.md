Cinema Ticket Booking App
========================================================

Description
--------------------------------------------------------
Spring boot aplication with RESTful api for cinema ticket booking app.

This is my first app taking use of SPRING, JDBC database and RESTful api. Used database is HyperSQL. Project uses gradle to build and require at least java version 8 [*tested with openjdk8*]

Instructions
--------------------------------------------------------
Before launching test demo you must:
1. build and start the application using script *compile_and_run.sh* [* *.jar file will be copied to root directory*]
2. start desired script :
   - *demo_script_1.sh* contains 1 example use case -> Checking movies, screenings, prices and making reservation
   - *demo_script_2.sh* contains 2 example use case -> Checking prices, listing screening and making reservation
   - *demo_script_extra.sh* contains sdditional features -> adding new movie, new screenings, listing reservations
- **note**:
   - I suggest running examples in 1 then 2 order. 
   - All script will provide information about what is happenning, used command, and response. 
   - To continue to the next brakpoint press any key.
3. after running demo send shutdown signal to the spring boot app using CRTL + C

Assumptions
--------------------------------------------------------
- API has no session support and for that reason choosing places, screeninig and ticket types is one request
- ticket types, rooms are stored in database [including room shape]
- to simplify seat checking i assumed every screening room has 6 rows and 6 columns
- if there is no object/failed to create object String field of the object will contain message and all integers will remain -1
- if there is no objects [multiple ones requested] empty Collection will be returned