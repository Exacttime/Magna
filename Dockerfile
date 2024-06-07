# Stage 1: Build the application
FROM maven:3.8.6 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apt-get update && apt-get install -y openjdk-21-jdk && update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-21-openjdk-amd64/bin/java 100
RUN mvn clean package -DskipTests

# Stage 2: Package the application
FROM amazoncorretto:21
VOLUME /tmp
COPY --from=build /app/target/Magna-1.0.jar /Magna-1.0.jar
ENTRYPOINT ["java","-jar","/Magna-1.0.jar"]