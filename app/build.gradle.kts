plugins {
    id("empath.app")
}

dependencies {
    implementation(projects.base.data)
    implementation(projects.base.ui)

    implementation(projects.features.root)
    implementation(projects.features.authUi)
    implementation(projects.features.authApi)
}

