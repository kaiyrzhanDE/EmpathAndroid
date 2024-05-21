pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "EmpathAndroid"

include(":app")

include(":features:auth-api")
include(":features:auth-ui")

include(":base:ui")
include(":base:data")
include(":base:navigation")
include(":base:utils")
