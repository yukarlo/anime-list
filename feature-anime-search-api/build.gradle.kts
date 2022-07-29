plugins {
    id("yukarlo.android.library")
}

dependencies {
    implementation(project(":core-navigation"))

    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit4)
}
