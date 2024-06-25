FROM openjdk:17
EXPOSE 9091
COPY target/authservice.jar authservice.jar
ENTRYPOINT ["java", "-jar", "authservice.jar"]