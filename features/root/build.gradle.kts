plugins {
    id("empath.ui")
}

android {
    namespace = "kaiyrzhan.de.root"
}

dependencies{
    api(projects.features.authUi)
}