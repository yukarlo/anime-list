plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

android {
    defaultConfig {
        applicationId = "com.yukarlo.anime"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(enabled = false)
            proguardFiles("proguard-rules.pro")
        }
    }

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.ANDROID_COMPOSE_VERSION
        kotlinCompilerVersion = Dependencies.Kotlin.VERSION
        useLiveLiterals = true
    }
}

dependencies {
    implementation(project(":feature-anime-home"))
    implementation(project(":feature-anime-list"))
    implementation(project(":feature-anime-details"))
    implementation(project(":feature-anime-search"))
    implementation(project(":feature-about"))
    implementation(project(":lib-anime"))
    implementation(project(":common-android"))
    implementation(project(":remote"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.AndroidX.RUNTIME_KTX)
    implementation(Dependencies.Apollo.RUNTIME)

    addComposeDependencies()
    implementation(Dependencies.AndroidX.COMPOSE_ACTIVITY)
    implementation(Dependencies.AndroidX.COMPOSE_NAVIGATION)
    implementation(Dependencies.Accompanist.ACCOMPANIST_INSETS)

    addDaggerDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}