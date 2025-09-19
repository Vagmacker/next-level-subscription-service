plugins {
    id("kotlin-conventions")
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
}

group = "com.nextlevel.subscription.application"

dependencies {
    implementation(project(":domain"))

    testImplementation(kotlin("test"))
    testImplementation(project(":domain", configuration = "testClasses"))
}