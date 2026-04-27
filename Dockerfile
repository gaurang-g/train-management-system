 FROM maven:3.8.4-openjdk-17 AS build
 COPY . .
 RUN mvn clean package -DskipTests

 FROM eclipse-temurin:17-jre-alpine
 # This tells the container to look for these variables at runtime
 ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
 ENV SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
 ENV SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD

 COPY --from=build /target/*.jar app.jar
 EXPOSE 8080
 ENTRYPOINT ["java","-jar","/app.jar"]