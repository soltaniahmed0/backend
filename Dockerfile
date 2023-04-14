FROM openjdk:17-slim
WORKDIR /app
COPY target/Backend-3.0.4.jar Backend-3.0.4.jar
EXPOSE 8081
CMD ["java", "-jar", "Backend-3.0.4.jar"]