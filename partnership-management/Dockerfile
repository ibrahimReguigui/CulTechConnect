FROM openjdk:17

COPY target/*.jar chat.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","/chat.jar"]
