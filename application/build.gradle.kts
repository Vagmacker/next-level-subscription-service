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
    set("mockkVersion", "1.14.2")
}

dependencies {
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:${property("junitVersion")}")
    testImplementation("io.mockk:mockk:${property("mockkVersion")}")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}