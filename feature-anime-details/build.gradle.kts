plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

android {
    buildFeatures.compose = true

    composeOptions {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        kotlinCompilerExtensionVersion = libs.findVersion("androidxCompose").get().requiredVersion
        useLiveLiterals = true
    }
}

dependencies {
    implementation(project(":common-android"))
    implementation(project(":core-model"))
    implementation(project(":lib-anime"))

    implementation(libs.accompanist.insets)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
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
