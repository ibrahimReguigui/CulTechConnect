FROM openjdk:17

COPY target/ms-email-0.0.1-SNAPSHOT.jar email.jar
EXPOSE 8099
ENTRYPOINT ["java","-jar","/email.jar"]
