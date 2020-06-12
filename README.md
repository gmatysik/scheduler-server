# scheduler-server
Scheduler server project. It is being used by client application:
 https://github.com/gmatysik/scheduler-client
and uses oAuth server to authorize access:
https://github.com/gmatysik/oauth-authorization-server


## Build

To build the project execute: mvn clean install

## Run

This starts the project with mock in memory reposotiry

java -jar -Dspring.profiles.active=development target/server-1.0-SNAPSHOT.jar

This starts the project with configured database (currently im memory h2, if configured it could be a file database):

java -jar -Dspring.profiles.active=production target/server-1.0-SNAPSHOT.jar
