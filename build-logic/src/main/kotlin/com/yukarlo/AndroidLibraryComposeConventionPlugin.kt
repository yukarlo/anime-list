@file:Suppress("PackageDirectoryMismatch")

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")

                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

                extensions.getByType<LibraryExtension>().apply {
                    buildFeatures.compose = true

                    composeOptions {
                        kotlinCompilerExtensionVersion =
                            libs.findVersion("androidxCompose").get().requiredVersion
                        useLiveLiterals = true
                    }
                }
            }
        }
    }
}
