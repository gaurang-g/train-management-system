# Stage 1: Build using Maven
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run using OpenJDK
FROM openjdk:17-jdk-slim
# This matches the 'target' folder shown in your screenshot
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]