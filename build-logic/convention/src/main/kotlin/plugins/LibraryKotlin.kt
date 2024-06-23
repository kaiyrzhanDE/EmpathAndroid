package plugins

import com.android.build.api.dsl.LibraryExtension
import extensions.configureDI
import extensions.configureLibraryAndroid
import extensions.configureNetwork
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

class LibraryKotlin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("java-library")
            apply("org.jetbrains.kotlin.jvm")
        }
        configureLibraryAndroid()
        configureDI<KotlinJvmProjectExtension>()
        configureNetwork()
    }
}