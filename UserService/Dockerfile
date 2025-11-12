# ✅ Step 1: Use a lightweight JDK image
FROM openjdk:21-jdk-slim

# ✅ Step 2: Set working directory
WORKDIR /app

# ✅ Step 3: Copy Maven-built JAR into container
COPY target/UserService-0.0.1-SNAPSHOT.jar app.jar

# ✅ Step 4: Expose service port
EXPOSE 8083

# ✅ Step 5: Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
