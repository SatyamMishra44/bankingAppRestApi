# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
# Automatically picks the correct JAR file
COPY target/*.jar app.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]