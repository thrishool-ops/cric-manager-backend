# Use official Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Use smaller JDK image to run app
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render provides the port via the PORT environment variable
ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "export JDBC_URL=$(echo ${DB_URL:-${SPRING_DATASOURCE_URL:-${DATABASE_URL}}} | sed 's/^postgres:\\/\\//jdbc:postgresql:\\/\\//' | sed 's/^postgresql:\\/\\//jdbc:postgresql:\\/\\//'); java -jar app.jar --server.port=${PORT:-8080} --spring.datasource.url=${JDBC_URL}"]