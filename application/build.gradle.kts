plugins {
    kotlin("jvm")
}

group = "com.nextlevel.subscription.application"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

ext {
    set("junitVersion", "5.12.2")
    set("mockitoVersion", "5.4.0")
}

dependencies {
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:${property("junitVersion")}")
    testImplementation("org.mockito.kotlin:mockito-kotlin:${property("mockitoVersion")}")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${property("junitVersion")}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}