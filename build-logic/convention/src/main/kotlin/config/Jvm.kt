package config

import org.gradle.api.JavaVersion

internal data class Jvm(
    val javaVersion : JavaVersion = JavaVersion.VERSION_1_8,
    val kotlinJvm : String = javaVersion.toString(),
    val freeCompilerArgs : List<String> = listOf("-Xopt-in=kotlin.RequiresOptIn")
)
