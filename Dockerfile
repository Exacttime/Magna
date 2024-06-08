# Stage 1: Build the application
FROM ubuntu:latest AS build
# Install JDK 21 and other dependencies
RUN apt-get update && apt-get install -y openjdk-21-jdk
COPY . .
RUN apt-get install maven-y
RUN mvn clean package -DskipTests

# Copy and build the project

# Stage 2: Package the application
FROM openjdk-21-jdk
EXPOSE 8080
COPY --from=build /target/Magna-1.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]