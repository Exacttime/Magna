# Stage 1: Build the application
FROM maven:3.8.6 AS build
WORKDIR /app

# Install JDK 21 and other dependencies
RUN apt-get update && apt-get install -y openjdk-21-jdk

ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Copy and build the project
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Package the application
FROM amazoncorretto:21
VOLUME /tmp
COPY --from=build /app/target/Magna-1.0.jar /Magna-1.0.jar
ENTRYPOINT ["java","-jar","/Magna-1.0.jar"]