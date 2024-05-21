package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.IMPLEMENTATION
import utils.getLibrary

internal fun Project.configureNetwork() {
    dependencies {
        add(IMPLEMENTATION, project.getLibrary("com-squareup-retrofit"))
        add(IMPLEMENTATION, project.getLibrary("com-squareup-okhttp3-logging-interceptor"))
        add(IMPLEMENTATION, project.getLibrary("org-jetbrains-kotlinx-serialization-json"))
        add(IMPLEMENTATION, project.getLibrary("com-jakewharton-retrofit-kotlinx-serialization-converter"))
        add(IMPLEMENTATION, project.getLibrary("retrofit-adapters-result"))
    }
}