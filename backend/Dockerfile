# Use a base Java image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . /app

# Change the permissions of the mvnw file to make it executable
RUN chmod +x ./mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port your application is running on (change it if your app runs on a different port)
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "target/CRUDBackend-0.0.1-SNAPSHOT.jar"]