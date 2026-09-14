# Build the Spring Boot application
FROM eclipse-temurin:11-jdk AS build

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests


# Run the Spring Boot application
FROM eclipse-temurin:11-jre

WORKDIR /app

COPY --from=build /app/target/jolt-server-1.0.0.jar app.jar

EXPOSE 9000

CMD ["java", "-jar", "app.jar"]
