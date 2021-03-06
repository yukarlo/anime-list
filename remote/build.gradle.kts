plugins {
    id("com.android.library")
    id("com.apollographql.apollo")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

apollo {
    generateKotlinModels.set(true)
}

dependencies {
    implementation(project(":core-model"))

    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.Apollo.RUNTIME)
    implementation(Dependencies.Apollo.APOLLO_COROUTINES)
    implementation(Dependencies.Coroutines.CORE)

    addDaggerDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}