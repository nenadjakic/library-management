FROM eclipse-temurin:21 AS build

WORKDIR /app/shared
COPY shared/ .

WORKDIR /app/country-service

COPY country-service/src/ ./src/
COPY country-service/build.gradle.kts .
COPY country-service/gradlew .
COPY country-service/settings.gradle.kts .
COPY country-service/gradle/ ./gradle

RUN ./gradlew clean build

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build /app/country-service/build/libs/country-service.jar .
CMD ["java", "-jar", "./country-service.jar"]