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
        useLiveLiterals = true
    }
}

dependencies {
    implementation(project(":common-android"))
    implementation(project(":core-model"))
    implementation(project(":lib-anime"))

    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.RUNTIME_KTX)
    implementation(Dependencies.Coil.COIL_COMPOSE)
    implementation(Dependencies.AndroidX.COMPOSE_VIEWMODEL)
    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Accompanist.ACCOMPANIST_INSETS)

    addDaggerDependencies()
    addComposeDependencies()
    implementation(Dependencies.AndroidX.COMPOSE_CONSTRAINT_LAYOUT)

    testImplementation(Dependencies.JUnit.JUNIT)
}
