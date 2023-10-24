FROM openjdk:17

COPY target/discovery-service-0.0.1-SNAPSHOT.jar discovery.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/discovery.jar"]
