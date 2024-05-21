package plugins

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import config.AppConfig
import extensions.configureAndroid
import extensions.configureBuildTypes
import extensions.configureCompose
import extensions.configureDI
import extensions.configureNavigation
import extensions.configureNetwork
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AppModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<BaseAppModuleExtension> {
                defaultConfig.apply {
                    namespace = AppConfig.android.namespace
                    targetSdk = AppConfig.android.targetSdk
                    applicationId = AppConfig.android.applicationId
                    versionCode = AppConfig.android.versionCode
                    versionName = AppConfig.android.versionName
                }
                buildFeatures.buildConfig = true

                configureAndroid<BaseAppModuleExtension>()
                configureBuildTypes<BaseAppModuleExtension>()
                configureNavigation()
                configureCompose<BaseAppModuleExtension>()
                configureNetwork()
                configureDI<BaseAppModuleExtension>()
            }
        }
    }
}