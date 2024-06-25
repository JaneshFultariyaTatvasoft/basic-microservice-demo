FROM openjdk:17
EXPOSE 9092
COPY target/userservice.jar /app/userservice.jar
ENTRYPOINT ["java", "-jar", "/app/userservice.jar"]