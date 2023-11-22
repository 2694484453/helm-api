FROM openjdk:8-jdk-alpine
LABEL authors="Administrator"
COPY target/demo-0.0.1-SNAPSHOT.jar /
ENV jarName demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["/bin/sh", "cd / && chmod +x ./demo-0.0.1-SNAPSHOT.jar && java -jar ./demo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080


#docker build -t helm-api:0.0.1 .
