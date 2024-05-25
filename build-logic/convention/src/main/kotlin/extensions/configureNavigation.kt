package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.IMPLEMENTATION
import utils.getLibrary

internal fun Project.configureNavigation() {
    dependencies {
        add(IMPLEMENTATION, project.getLibrary("org-jetbrains-kotlinx-serialization-json"))
        add(IMPLEMENTATION, project.getLibrary("decompose"))
        add(IMPLEMENTATION, project.getLibrary("decompose-extensions-jetpack"))
        add(IMPLEMENTATION, project.getLibrary("essenty-lifecycle-coroutines"))
    }
}