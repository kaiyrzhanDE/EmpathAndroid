package extensions

import com.android.build.api.dsl.LibraryExtension
import config.AppConfig
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.IMPLEMENTATION
import utils.TEST_IMPLEMENTATION
import utils.getLibrary

internal fun Project.configureLibraryKotlin() {
    with(extensions.getByType<LibraryExtension>()) {
        compileOptions {
            sourceCompatibility = AppConfig.jvm.javaVersion
            targetCompatibility = AppConfig.jvm.javaVersion
        }
    }
}