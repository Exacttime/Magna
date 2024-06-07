# Dockerfile
FROM amazoncorretto:21
VOLUME /tmp
COPY target/*.jar Magna-1.0.jar
ENTRYPOINT ["java","-jar","/Magna-1.0.jar"]