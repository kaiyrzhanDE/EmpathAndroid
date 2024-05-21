plugins {
    id("convention.featureModule")
}

android {
    namespace = "kaiyrzhan.de.feature_auth"
}

dependencies {
    api(project(":base:ui"))
    implementation(project(":base:navigation"))
    implementation(project(":features:auth-api"))
}
