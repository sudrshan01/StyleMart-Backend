# Step 1: Use official OpenJDK image
FROM openjdk:21-jdk-slim

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy the JAR file built by Maven
COPY target/ReviewService-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your service runs on
EXPOSE 8082

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
