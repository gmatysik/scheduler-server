FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
COPY application/springboot/target/springboot-0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]