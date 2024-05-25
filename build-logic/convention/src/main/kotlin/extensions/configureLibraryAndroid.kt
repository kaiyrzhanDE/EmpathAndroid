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
import utils.getVersion

internal fun Project.configureLibraryAndroid() {
    with(extensions.getByType<LibraryExtension>()) {
        defaultConfig.testInstrumentationRunner = AppConfig.android.testInstrumentationRunner
        
        compileSdk = AppConfig.android.compileSdk
        defaultConfig {
            minSdk = AppConfig.android.minSdk
        }

        compileOptions {
            sourceCompatibility = AppConfig.jvm.javaVersion
            targetCompatibility = AppConfig.jvm.javaVersion
        }

        packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

        dependencies {
            add(IMPLEMENTATION, project.getLibrary("androidx-core-ktx"))
            add(IMPLEMENTATION, project.getLibrary("androidx-lifecycle-runtime-ktx"))

            add(TEST_IMPLEMENTATION, project.getLibrary("junit"))
            add(TEST_IMPLEMENTATION, project.getLibrary("androidx-test-ext-junit"))
            add(TEST_IMPLEMENTATION, project.getLibrary("androidx-test-espresso-core"))
        }

    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = AppConfig.jvm.kotlinJvm
        }
    }
}