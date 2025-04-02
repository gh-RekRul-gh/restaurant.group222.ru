# Сборка
FROM maven:3.9.7-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

RUN mvn clean package -DskipTests

# Выполнение
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/website.jar .

CMD ["java", "-jar", "website.jar"]