FROM openjdk:17

COPY target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]
