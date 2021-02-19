plugins {
    id("com.android.library")
    kotlin(module = "android")
}

dependencies {
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.AndroidX.RUNTIME_KTX)

    addComposeDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}