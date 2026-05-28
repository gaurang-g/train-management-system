  FROM maven:3.8.4-openjdk-17 AS build
  COPY . .
  RUN mvn clean package -DskipTests

  FROM eclipse-temurin:17-jre-alpine
  # This tells the container to look for these variables at runtime


  COPY --from=build /target/*.jar app.jar

  EXPOSE 8080

  ENTRYPOINT ["java","-jar","app.jar"]