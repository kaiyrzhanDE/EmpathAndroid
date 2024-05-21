plugins {
    id("convention.dataModule")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "kaiyrzhan.de.core_network"
}

dependencies {
    api(project(":base:utils"))
}

