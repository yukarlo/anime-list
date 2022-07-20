import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(dependencyNotation = libs.android.gradlePlugin)
        classpath(dependencyNotation = libs.hilt.gradlePlugin)
        classpath(dependencyNotation = libs.graphql.apollo3.gradle)
        classpath(dependencyNotation = libs.kotlin.gradlePlugin)
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}

subprojects {
    tasks.withType<KotlinCompile> {
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-Xopt-in=coil.annotation.ExperimentalCoilApi",
            )
        }
    }

    project.plugins.configureAppAndModules(subProject = project)
}

fun PluginContainer.configureAppAndModules(subProject: Project) = apply {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                subProject.extensions
                    .getByType<AppExtension>()
                    .applyAppCommons()
            }
            is LibraryPlugin -> {
                subProject.extensions
                    .getByType<LibraryExtension>()
                    .applyLibraryCommons()
            }
        }
    }
}

fun AppExtension.applyAppCommons() = apply { applyBaseCommons() }
fun LibraryExtension.applyLibraryCommons() = apply { applyBaseCommons() }

fun BaseExtension.applyBaseCommons() = apply {
    compileSdkVersion(32)

    defaultConfig.apply {
        minSdk = 23
        targetSdk = 32
    }

    buildFeatures.viewBinding = true

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
