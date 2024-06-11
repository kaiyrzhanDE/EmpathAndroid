plugins {
    id("empath.data")
}

android {
    namespace = "kaiyrzhan.de.data_auth"
}

dependencies {
   api(projects.base.data)
}
