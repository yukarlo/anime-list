plugins {
    id("com.android.library")
    kotlin(module = "android")
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

    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.AndroidX.RUNTIME_KTX)
    implementation(Dependencies.Accompanist.ACCOMPANIST_COIL)

    addComposeDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}
