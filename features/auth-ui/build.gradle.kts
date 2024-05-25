plugins {
    id("convention.featureModule")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "kaiyrzhan.de.feature_auth"
}

dependencies {
    implementation(project(":base:ui"))
    implementation(project(":features:auth-api"))
}
