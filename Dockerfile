# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run the application with a minimal runtime (distroless)
FROM gcr.io/distroless/java17-debian11

WORKDIR /app

COPY --from=builder /app/target/voting-app-1.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

