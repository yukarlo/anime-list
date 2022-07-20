plugins {
    id("com.android.library")
    kotlin(module = "android")
    id("kotlin-parcelize")
}

dependencies {
    implementation(libs.kotlin.stdlib)
}
