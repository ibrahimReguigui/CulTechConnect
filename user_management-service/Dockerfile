FROM openjdk:17

COPY target/user_management-service-0.0.1-SNAPSHOT.jar user.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/user.jar"]
