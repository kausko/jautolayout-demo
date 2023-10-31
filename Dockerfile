FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
EXPOSE 8080
RUN ./mvnw clean package -Pproduction
ENTRYPOINT ["java", "-jar", "/jautolayout-1.0-SNAPSHOT.jar"]