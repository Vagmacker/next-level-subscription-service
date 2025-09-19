plugins {
    id("kotlin-conventions")
    id("application")
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
    id("jacoco-report-aggregation")
    kotlin("plugin.jpa") version "2.2.0"
    kotlin("plugin.spring") version "2.2.0"
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.nextlevel.subscription.infrastructure"

val springdoc = "2.8.9"
val testcontainers = "1.21.3"
val postgresqlJdbcDriver = "42.7.7"

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdoc")

    implementation("org.postgresql:postgresql:$postgresqlJdbcDriver")

    testImplementation(kotlin("test"))
    testImplementation(project(":domain", configuration = "testClasses"))

    testImplementation("org.flywaydb:flyway-core")
    testImplementation("org.flywaydb:flyway-database-postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")

    testImplementation("org.testcontainers:testcontainers:$testcontainers")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainers")
    testImplementation("org.testcontainers:postgresql:$testcontainers")
}