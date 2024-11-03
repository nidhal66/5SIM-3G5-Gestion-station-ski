FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/gestionski-1.0.jar gestionski-1.0.jar
ENTRYPOINT ["java","-jar","/gestionski-1.0.jar"]
