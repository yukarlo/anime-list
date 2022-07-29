plugins {
    id("yukarlo.android.library")
}

dependencies {
    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    testImplementation(libs.junit4)
}
