plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

dependencies {
    implementation(project(":core-model"))
    implementation(project(":remote"))

    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.Coroutines.CORE)

    testImplementation(Dependencies.JUnit.JUNIT)
    testImplementation(Dependencies.Mockito.MOCKITO_KOTLIN)

    addDaggerDependencies()
}
