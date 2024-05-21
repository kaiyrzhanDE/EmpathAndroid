package plugins

import com.android.build.api.dsl.LibraryExtension
import extensions.configureCompose
import extensions.configureDI
import extensions.configureLibraryAndroid
import extensions.configureNavigation
import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }
        configureLibraryAndroid()
        configureDI<LibraryExtension>()
        configureCompose<LibraryExtension>()
        configureNavigation()
    }
}