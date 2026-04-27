 # Stage 1: Build using Maven and Amazon Corretto (reliable OpenJDK)
 FROM maven:3.8.4-openjdk-17 AS build
 COPY . .
 RUN mvn clean package -DskipTests

 # Stage 2: Run using Eclipse Temurin Java 17
 FROM eclipse-temurin:17-jre-alpine
 COPY --from=build /target/*.jar app.jar
 EXPOSE 8080
 ENTRYPOINT ["java","-jar","/app.jar"]