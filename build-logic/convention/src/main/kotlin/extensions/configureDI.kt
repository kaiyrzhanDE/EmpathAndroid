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
        with(plugins) {
            apply("dagger.hilt.android.plugin")
            apply("kotlin-kapt")
        }

        dependencies {
            add(IMPLEMENTATION, project.getLibrary("com-google-dagger-hilt-android"))
            add(IMPLEMENTATION, project.getLibrary("androidx-hilt-navigation-compose"))
            add(KAPT, project.getLibrary("androidx-hilt-compiler"))
            add(KAPT, project.getLibrary("com-google-dagger-hilt-android-compiler"))
        }
    }
}