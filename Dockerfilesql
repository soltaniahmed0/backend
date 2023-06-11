FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file
COPY target/Backend-3.0.4.jar /app
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/pfe
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=ahmedsoltani

EXPOSE 8081

# Set the entry point
CMD ["java", "-jar", "Backend-3.0.4.jar"]
