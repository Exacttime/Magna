# Stage 1: Build the application
FROM maven:3.8.1-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Package the application
FROM amazoncorretto:21
VOLUME /tmp
COPY --from=build /app/target/Magna-1.0.jar /Magna-1.0.jar
ENTRYPOINT ["java","-jar","/Magna-1.0.jar"]