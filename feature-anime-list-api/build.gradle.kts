plugins {
    id("yukarlo.android.library.compose")
}

dependencies {
    implementation(project(":core-navigation"))

    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit4)
}
