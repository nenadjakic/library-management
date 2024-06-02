FROM eclipse-temurin:21 AS build

WORKDIR /app/shared
COPY shared/ .

WORKDIR /app/book-service

COPY book-service/src/ ./src/
COPY book-service/build.gradle.kts .
COPY book-service/gradlew .
COPY book-service/settings.gradle.kts .
COPY book-service/gradle/ ./gradle

RUN ./gradlew clean build

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build /app/book-service/build/libs/book-service.jar .
CMD ["java", "-jar", "./book-service.jar"]