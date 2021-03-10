plugins {
    id("com.android.library")
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
    implementation(project(":core-model"))

    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.Accompanist.ACCOMPANIST_COIL)

    addComposeDependencies()
    addDaggerDependencies()
}