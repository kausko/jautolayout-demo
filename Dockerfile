FROM openjdk:17-jdk-slim
RUN mvn clean package -Pproduction
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]