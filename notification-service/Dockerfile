FROM openjdk:17

COPY target/notification-service-0.0.1-SNAPSHOT.jar notification.jar
EXPOSE 8099
ENTRYPOINT ["java","-jar","/notification.jar"]
