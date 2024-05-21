package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.IMPLEMENTATION
import utils.getLibrary

internal fun Project.configureNavigation() {
    dependencies {
        add(IMPLEMENTATION, project.getLibrary("voyager-navigator"))
        add(IMPLEMENTATION, project.getLibrary("voyager-screenmodel"))
        add(IMPLEMENTATION, project.getLibrary("voyager-navigator-bottom-sheet"))
        add(IMPLEMENTATION, project.getLibrary("voyager-transitions"))
        add(IMPLEMENTATION, project.getLibrary("voyager-hilt"))
    }
}