plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}
rootProject.name = "next-level-subscription-service"
include("domain")
include("application")
include("infrastructure")