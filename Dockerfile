# Use the official OpenJDK 21 image (slim version)
FROM openjdk:21-jdk-slim

# Copy the JAR file from the target directory into the container
COPY ./target/quizapp-0.0.1-SNAPSHOT.jar /app.jar

# Expose the application port (default is 8080 for Spring Boot)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

