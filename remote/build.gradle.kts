plugins {
    id("com.android.library")
    id("com.apollographql.apollo")
    kotlin(module = "android")
}

dependencies {
    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.Apollo.RUNTIME)

    testImplementation(Dependencies.JUnit.JUNIT)
}