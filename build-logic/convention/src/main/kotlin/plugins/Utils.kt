package plugins

import com.android.build.gradle.LibraryExtension
import extensions.configureDI
import extensions.configureLibraryAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class Utils : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }
        configureLibraryAndroid()
        configureDI<LibraryExtension>()
    }
}