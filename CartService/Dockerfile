# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the jar file from target folder into the container
COPY target/CartService-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
