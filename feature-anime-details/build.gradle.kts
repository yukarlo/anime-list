plugins {
    id("yukarlo.android.library.compose")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":common-android"))
    implementation(project(":core-model"))
    implementation(project(":core-navigation"))
    implementation(project(":feature-anime-domain"))
    implementation(project(":feature-anime-details-api"))

    implementation(libs.accompanist.flowlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.coil.kt.compose)
    implementation(libs.coroutines.core)

    implementation(libs.hilt.android.core)
    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.constraintlayout)

    testImplementation(libs.junit4)
}
