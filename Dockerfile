FROM eclipse-temurin:21-jre-alpine
LABEL authors="OSH"
COPY target/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "/backend.jar"]