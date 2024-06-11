package plugins

import com.android.build.api.dsl.LibraryExtension
import extensions.configureCompose
import extensions.configureDI
import extensions.configureLibraryAndroid
import extensions.configureNavigation
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class Ui: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }
        configureLibraryAndroid()
        configureDI<LibraryExtension>()
        configureCompose<LibraryExtension>()
        configureNavigation()

    }
}