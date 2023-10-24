FROM openjdk:17

COPY target/blog-0.0.1-SNAPSHOT.jar BlogService.jar
EXPOSE 8093
ENTRYPOINT ["java","-jar","BlogService.jar"]
