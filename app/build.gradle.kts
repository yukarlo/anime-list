plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

android {
    defaultConfig {
        applicationId = "com.yukarlo.anime"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(enabled = false)
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":feature-anime-home"))
    implementation(project(":remote"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.Google.MATERIAL)
    implementation(Dependencies.AndroidX.RUNTIME_KTX)
    implementation(Dependencies.Apollo.RUNTIME)

    addComposeDependencies()
    implementation(Dependencies.AndroidX.COMPOSE_ACTIVITY)

    addDaggerDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}