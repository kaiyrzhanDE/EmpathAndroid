plugins {
    id("convention.uiModule")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "kaiyrzhan.de.root"
}

dependencies{
    api(project(":features:auth-ui"))
}