plugins {
    java
    jacoco
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion = "5.12.2"
val mockkVersion = "1.14.2"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("unitTests") {
    group = "verification"
    useJUnitPlatform {
        includeTags("unitTest")
    }
}

tasks.register<Test>("integrationTests") {
    group = "verification"
    useJUnitPlatform {
        includeTags("integrationTest")
    }
}

tasks.register<Test>("e2eTests") {
    group = "verification"
    useJUnitPlatform {
        includeTags("e2eTest")
    }
}
