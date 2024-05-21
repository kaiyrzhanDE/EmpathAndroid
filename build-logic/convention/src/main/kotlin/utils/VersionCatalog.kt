package utils

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.VersionConstraint
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

internal val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named(LIBS)

internal fun Project.getLibrary(dependency: String): Provider<MinimalExternalModuleDependency> {
    return versionCatalog.findLibrary(dependency).get()
}

internal fun Project.getVersion(dependency: String): VersionConstraint {
    return versionCatalog.findVersion(dependency).get()
}

internal fun Project.getBundle(dependency: String): Provider<ExternalModuleDependencyBundle> {
    return versionCatalog.findBundle(dependency).get()
}