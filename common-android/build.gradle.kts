plugins {
    id("com.android.library")
    id("androidx.navigation.safeargs")
    kotlin(module = "android")
    kotlin(module = "kapt")
    kotlin(module = "android.extensions")
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.ANDROID_COMPOSE_VERSION
        kotlinCompilerVersion = Dependencies.Kotlin.VERSION
    }
}

dependencies {
    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.NAVIGATION_UI_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_COMMON_JAVA8)
    implementation(Dependencies.Google.MATERIAL)

    addComposeDependencies()
    addDaggerDependencies()
}