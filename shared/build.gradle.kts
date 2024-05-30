import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"

    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.github.nenadjakic.library"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework:spring-web:6.1.8")
    implementation("org.springframework:spring-context:6.1.8")
    implementation("org.springframework.data:spring-data-commons:3.3.0")
    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

kotlin {
    jvmToolchain(21)
}

