plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.AndroidX.ANDROID_COMPOSE_VERSION
        kotlinCompilerVersion = Dependencies.Kotlin.VERSION
    }
}

dependencies {
    implementation(project(":common-android"))
    implementation(project(":core-model"))
    implementation(project(":lib-anime"))

    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.AndroidX.RUNTIME_KTX)
    implementation(Dependencies.Accompanist.ACCOMPANIST_COIL)
    implementation(Dependencies.AndroidX.COMPOSE_VIEWMODEL)
    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Accompanist.ACCOMPANIST_INSETS)

    addDaggerDependencies()
    addComposeDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}
