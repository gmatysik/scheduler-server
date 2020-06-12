# scheduler-server
Scheduler server project

##Build

To build the project execute: mvn clean install

##Run

This starts the project with mock in memory reposotiry

java -jar -Dspring.profiles.active=development target/server-1.0-SNAPSHOT.jar

This starts the project with configured database (currently im memory h2, if configured it could be a file database):

java -jar -Dspring.profiles.active=production target/server-1.0-SNAPSHOT.jar
