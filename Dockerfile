FROM openjdk:8-jdk-alpine
COPY target/profile-ms-*.jar profile-ms.jar
ENTRYPOINT ["java","-jar","/profile-ms.jar"]