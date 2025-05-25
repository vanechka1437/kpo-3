FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/file-storage-service-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]