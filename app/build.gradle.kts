plugins {
    id("convention.appModule")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

dependencies {
    implementation(project(":base:data"))
    implementation(project(":features:root"))
    implementation(project(":features:auth-api"))
    implementation(project(":base:ui"))
}

