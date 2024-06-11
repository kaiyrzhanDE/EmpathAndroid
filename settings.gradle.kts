/**
 * For projects implementation like version catalog libs
 **/
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    /**
     * For convention plugin work
     **/
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "EmpathAndroid"

/**
 * App
 **/
include(":app")

/**
 * Features
 **/
include(":features:root")
include(":features:auth-api")
include(":features:auth-ui")

/**
 * Base
 **/
include(":base:ui")
include(":base:data")
include(":base:utils")
