# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/*.jar /app/flights.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/flights.jar"]
