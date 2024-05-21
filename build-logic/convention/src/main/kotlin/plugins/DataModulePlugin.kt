package plugins

import com.android.build.api.dsl.LibraryExtension
import extensions.configureDI
import extensions.configureLibraryAndroid
import extensions.configureNetwork
import org.gradle.api.Plugin
import org.gradle.api.Project

class DataModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }
        configureLibraryAndroid()
        configureDI<LibraryExtension>()
        configureNetwork()
    }
}