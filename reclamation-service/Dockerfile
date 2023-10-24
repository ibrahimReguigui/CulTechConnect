FROM openjdk:17

COPY target/*.jar reclamation.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","/reclamation.jar"]
