FROM eclipse-temurin:21 AS build

WORKDIR /app

COPY src/ ./src/
COPY build.gradle.kts .
COPY gradlew .
COPY settings.gradle.kts .
COPY gradle/ ./gradle

RUN ./gradlew clean build

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build /app/build/libs/country-service.jar .
CMD ["java", "-jar", "./country-service.jar"]