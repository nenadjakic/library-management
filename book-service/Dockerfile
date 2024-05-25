FROM eclipse-temurin:21 AS build

WORKDIR /app

COPY src/ ./src/
COPY build.gradle.kts .
COPY gradle.properties .
COPY gradlew .
COPY settings.gradle.kts .
COPY gradle/ ./gradle

RUN ./gradlew clean build

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build /app/build/libs/book-service.jar .
CMD ["java", "-jar", "./book-service.jar"]