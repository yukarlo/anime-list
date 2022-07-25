plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

android {
    defaultConfig {
        applicationId = "com.yukarlo.anime"
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }

    buildFeatures.compose = true

    composeOptions {
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        kotlinCompilerExtensionVersion = libs.findVersion("androidxCompose").get().requiredVersion
        useLiveLiterals = true
    }
}

dependencies {
    implementation(project(":feature-anime-home"))
    implementation(project(":feature-anime-list"))
    implementation(project(":feature-anime-details"))
    implementation(project(":feature-anime-search"))
    implementation(project(":feature-about"))
    implementation(project(":lib-anime"))
    implementation(project(":common-android"))
    implementation(project(":core-remote"))

    implementation(libs.accompanist.insets)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.graphql.apollo3.runtime)

    implementation(libs.hilt.android.core)
    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.junit4)
}
