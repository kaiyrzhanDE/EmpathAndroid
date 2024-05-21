plugins {
    id("convention.dataModule")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "kaiyrzhan.de.data_auth"
}

dependencies {
    api(project(":base:data"))
}
