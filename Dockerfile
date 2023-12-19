# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-alpine

# Expose port 8888
EXPOSE 8888

# Copy the JAR file into the container at /app
COPY target/timemanagement-app-0.0.1-SNAPSHOT.jar /app/timemanagement-app.jar

# Set the working directory to /app
WORKDIR /app

# Run the JAR file
CMD ["java", "-jar", "timemanagement-app.jar"]
