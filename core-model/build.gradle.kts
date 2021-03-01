plugins {
    id("com.android.library")
    kotlin(module = "android")
    kotlin(module = "android.extensions")
}

dependencies {
    implementation(Dependencies.Kotlin.STDLIB)
}