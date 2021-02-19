plugins {
    id("com.android.library")
    id("com.apollographql.apollo")
    kotlin(module = "android")
}

apollo {
    generateKotlinModels.set(true)
}

dependencies {
    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.Apollo.RUNTIME)
    implementation(Dependencies.Apollo.APOLLO_COROUTINES)
    implementation(Dependencies.Coroutines.CORE)

    addComposeDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}