plugins {
    id("empath.ui")
}

android {
    namespace = "kaiyrzhan.de.feature_auth"
}

dependencies {
    implementation(projects.base.ui)
    implementation(projects.features.authApi)
}
