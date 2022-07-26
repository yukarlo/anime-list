plugins {
    id("yukarlo.android.library")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":core-model"))

    implementation("javax.inject:javax.inject:1")
    implementation(libs.coroutines.core)

    testImplementation(libs.junit4)
}
