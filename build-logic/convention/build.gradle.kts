import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins{
    `kotlin-dsl`
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("data") {
            id = "empath.data"
            implementationClass = "plugins.Data"
        }

        register("ui") {
            id = "empath.ui"
            implementationClass = "plugins.Ui"
        }

        register("app") {
            id = "empath.app"
            implementationClass = "plugins.App"
        }

        register("utils"){
            id = "empath.utils"
            implementationClass = "plugins.Utils"
        }

        register("lib"){
            id = "empath.lib"
            implementationClass = "plugins.LibraryKotlin"
        }
    }
}