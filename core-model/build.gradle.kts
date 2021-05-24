plugins {
    id("com.android.library")
    kotlin(module = "android")
    id("kotlin-parcelize")
}

dependencies {
    implementation(Dependencies.Kotlin.STDLIB)
}
