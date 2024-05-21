package extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
internal inline fun <reified T : CommonExtension<*, *, *, *>> Project.configureBuildTypes() {
    extensions.getByType<T>().apply {
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}