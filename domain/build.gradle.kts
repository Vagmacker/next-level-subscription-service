plugins {
    id("kotlin-conventions")
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "2.2.0"
}

group = "com.nextlevel.subscription.domain"

val datafakerVersion = "2.4.4"

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("net.datafaker:datafaker:$datafakerVersion")
}

val testClasses by configurations.creating

configurations {
    named("testClasses") {
        extendsFrom(configurations.testImplementation.get())
    }
}

task<Jar>("testJar") {
    archiveClassifier.set("tests")
    from(sourceSets["test"].output)
}

artifacts {
    add("testClasses", tasks.named("testJar"))
}