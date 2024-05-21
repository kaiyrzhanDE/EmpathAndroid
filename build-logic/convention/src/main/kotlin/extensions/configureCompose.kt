package extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import utils.DEBUG_IMPLEMENTATION
import utils.IMPLEMENTATION
import utils.TEST_IMPLEMENTATION
import utils.getLibrary
import utils.getVersion

@Suppress("UnstableApiUsage")
internal inline fun <reified T : CommonExtension<*, *, *, *>> Project.configureCompose() {
    extensions.getByType<T>().apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                project.getVersion("kotlin-compiler-extension").toString()
        }

        dependencies {
            add(IMPLEMENTATION, project.getLibrary("androidx-activity-compose"))
            add(IMPLEMENTATION, project.getLibrary("androidx-compose-bom"))
            add(IMPLEMENTATION, project.getLibrary("androidx-compose-ui"))
            add(IMPLEMENTATION, project.getLibrary("androidx-compose-ui-graphics"))
            add(IMPLEMENTATION, project.getLibrary("androidx-compose-ui-tooling-preview"))
            add(IMPLEMENTATION, project.getLibrary("androidx-compose-material3"))
            add(IMPLEMENTATION, project.getLibrary("io-coil-compose"))
            add(IMPLEMENTATION, project.getLibrary("com-google-accompanist-permissions"))

            add(TEST_IMPLEMENTATION, project.getLibrary("androidx-test-compose-bom"))
            add(TEST_IMPLEMENTATION, project.getLibrary("androidx-compose-ui-test-junit4"))

            add(DEBUG_IMPLEMENTATION, project.getLibrary("androidx-compose-ui-tooling"))
            add(DEBUG_IMPLEMENTATION, project.getLibrary("androidx-compose-ui-test-manifest"))
        }
    }
}