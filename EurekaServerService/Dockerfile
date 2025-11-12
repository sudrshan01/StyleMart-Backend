# Use Eclipse Temurin JDK 21
FROM eclipse-temurin:21-jdk-alpine

# JAR file argument
ARG JAR_FILE=target/*.jar

# Copy the JAR
COPY ${JAR_FILE} app.jar

# Run the JAR
ENTRYPOINT ["java","-jar","/app.jar"]
