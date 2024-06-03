# Use a multi-stage build to reduce the final image size
# First stage: build the application
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Second stage: create the final image
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/SpringJPADemoPostgreSQL.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
