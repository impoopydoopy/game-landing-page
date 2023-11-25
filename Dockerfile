# Use the official OpenJDK base image
FROM openjdk:11

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build output to the container
COPY target/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
