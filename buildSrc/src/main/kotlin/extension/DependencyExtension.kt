@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addDaggerDependencies() {
    implement(Dependencies.Dagger.DAGGER_HILT_ANDROID)
    implement(Dependencies.Dagger.DAGGER_HILT)
    implement(Dependencies.Dagger.DAGGER_HILT_LIFECYCLE_VIEWMODEL)
    implement(Dependencies.Dagger.DAGGER_HILT_NAVIGATION)
    kapt(Dependencies.Dagger.DAGGER_HILT_ANDROID_COMPILER)
    kapt(Dependencies.Dagger.DAGGER_HILT_COMPILER)
}

fun DependencyHandler.addComposeDependencies() {
    implement(Dependencies.AndroidX.COMPOSE_UI)
    implement(Dependencies.AndroidX.COMPOSE_MATERIAL)
    implement(Dependencies.AndroidX.COMPOSE_UI_TOOLING)
    implement(Dependencies.AndroidX.COMPOSE_NAVIGATION)
}

private fun DependencyHandler.implement(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)
