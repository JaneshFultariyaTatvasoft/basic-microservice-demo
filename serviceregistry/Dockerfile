FROM openjdk:17
EXPOSE 8761
COPY /target/serviceregistry.jar serviceregistry.jar
ENTRYPOINT ["java", "-jar", "serviceregistry.jar"]