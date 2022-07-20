plugins {
    id("com.android.library")
    kotlin(module = "android")
    kotlin(module = "kapt")
    id("kotlin-parcelize")
}

android {
    buildFeatures.compose = true

    composeOptions {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        kotlinCompilerExtensionVersion = libs.findVersion("androidxCompose").get().requiredVersion
        useLiveLiterals = true
    }
}

dependencies {
    implementation(project(":core-model"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.coil.kt.compose)

    implementation(libs.hilt.android.core)
    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.navigation.compose)
}
