plugins {
    id("com.android.library")
    id("com.apollographql.apollo3")
    id("dagger.hilt.android.plugin")
    kotlin(module = "android")
    kotlin(module = "kapt")
}

apollo {
    packageName.set("com.yukarlo")
}

dependencies {
    implementation(project(":core-model"))

    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.Apollo.RUNTIME)
    implementation(Dependencies.Coroutines.CORE)

    addDaggerDependencies()

    testImplementation(Dependencies.JUnit.JUNIT)
}
