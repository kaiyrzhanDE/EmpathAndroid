package config

internal data class Android(
    val minSdk: Int = 24,
    val targetSdk: Int = 34,
    val compileSdk: Int = 34,
    val applicationId: String = "kaiyrzhan.de.diploma",
    val versionCode: Int = 1,
    val versionName: String = "1.0",
    val namespace: String = "kaiyrzhan.de.diploma",
    val testInstrumentationRunner: String = "androidx.test.runner.AndroidJunitRunner",
)
