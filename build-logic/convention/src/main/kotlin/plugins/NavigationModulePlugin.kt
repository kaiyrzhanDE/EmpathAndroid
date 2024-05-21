package plugins

import extensions.configureLibraryAndroid
import extensions.configureNavigation
import org.gradle.api.Plugin
import org.gradle.api.Project

class NavigationModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }
        configureLibraryAndroid()
        configureNavigation()
    }
}