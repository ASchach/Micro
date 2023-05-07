FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/classes/com/example/ClientApplication.class app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

