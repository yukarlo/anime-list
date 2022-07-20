plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidLibraryCompose") {
            id = "yukarlo.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("AndroidLibrary") {
            id = "yukarlo.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
