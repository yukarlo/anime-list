plugins {
    id("yukarlo.android.library")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core-model"))
    implementation(project(":remote"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutines.core)

    testImplementation(libs.junit4)
    testImplementation(libs.mockito)

    implementation(libs.hilt.android.core)
    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
}
