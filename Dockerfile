FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn clean install

FROM alpine/java:22.0.2-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]