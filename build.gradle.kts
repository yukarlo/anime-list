import com.android.build.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
    dependencies {
        classpath(dependencyNotation = "com.android.tools.build:gradle:7.0.0-alpha09")
        classpath(dependencyNotation = Dependencies.Dagger.DAGGER_HILT_ANDROID_GRADLE_PLUGIN)
        classpath(dependencyNotation = Dependencies.Apollo.GRADLE)
        classpath(kotlin(module = "gradle-plugin", version = Dependencies.Kotlin.VERSION))
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
                "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi"
            )
            useIR = true
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
    compileSdkVersion(Android.Sdk.COMPILE)

    defaultConfig.apply {
        minSdkVersion(Android.Sdk.MIN)
        targetSdkVersion(Android.Sdk.TARGET)
    }

    buildFeatures.viewBinding = true

    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
