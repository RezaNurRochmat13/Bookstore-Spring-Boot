FROM openjdk:8-jdk-alpine

COPY ./target/demo-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch demo-0.0.1-SNAPSHOT.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]