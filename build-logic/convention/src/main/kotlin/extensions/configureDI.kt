package extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import utils.IMPLEMENTATION
import utils.KAPT
import utils.getLibrary

internal inline fun <reified T : CommonExtension<*, *, *, *>> Project.configureDI() {
    extensions.getByType<T>().apply {
        dependencies {
            add(IMPLEMENTATION, project.getLibrary("io-insert-koin-core"))
            add(IMPLEMENTATION, project.getLibrary("io-insert-koin-android"))
        }
    }
}