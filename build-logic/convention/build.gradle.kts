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
        register("dataModule") {
            id = "convention.dataModule"
            implementationClass = "plugins.DataModulePlugin"
        }

        register("featureModule") {
            id = "convention.featureModule"
            implementationClass = "plugins.FeatureModulePlugin"
        }

        register("uiModule") {
            id = "convention.uiModule"
            implementationClass = "plugins.UiModulePlugin"
        }

        register("appModule") {
            id = "convention.appModule"
            implementationClass = "plugins.AppModulePlugin"
        }

        register("navigationModule"){
            id = "convention.navigationModule"
            implementationClass = "plugins.NavigationModulePlugin"
        }

        register("libraryModule"){
            id = "convention.libraryModule"
            implementationClass = "plugins.LibraryModulePlugin"
        }

    }
}