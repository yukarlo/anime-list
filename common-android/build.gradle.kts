plugins {
    id("yukarlo.android.library.compose")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":core-model"))

    implementation(libs.androidx.palette)
    implementation(libs.coil.kt.compose)

    implementation(libs.hilt.android.core)
    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling)
}
