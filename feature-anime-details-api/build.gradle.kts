plugins {
    id("yukarlo.android.library")
}

dependencies {
    implementation(project(":core-navigation"))

    testImplementation(libs.junit4)
}
