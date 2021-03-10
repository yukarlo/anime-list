object Dependencies {
    object Kotlin {
        const val VERSION = "1.4.30"
        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$VERSION"
        const val EXTENSIONS = "org.jetbrains.kotlin:kotlin-android-extensions:$VERSION"
    }

    object Coroutines {
        private const val VERSION = "1.4.2"
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
        const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$VERSION"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:1.3.0"
    }

    object AndroidX {
        const val ANDROID_COMPOSE_VERSION = "1.0.0-beta01"
        private const val ANDROID_LIFECYCLE_VERSION = "2.3.0"

        const val APP_COMPAT = "androidx.appcompat:appcompat:1.3.0-beta01"
        const val CORE_KTX = "androidx.core:core-ktx:1.5.0-beta01"
        const val RUNTIME_KTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:$ANDROID_LIFECYCLE_VERSION"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:1.3.0-alpha03"
        const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:1.0.0-alpha08"
        const val COMPOSE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha02"
        const val COMPOSE_UI = "androidx.compose.ui:ui:$ANDROID_COMPOSE_VERSION"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:$ANDROID_COMPOSE_VERSION"
        const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:$ANDROID_COMPOSE_VERSION"
        const val COMPOSE_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha03"
    }

    object Dagger {
        private const val DAGGER_HILT_ANDROID_VERSION = "2.31.2-alpha"
        private const val DAGGER_HILT_VERSION = "1.0.0-alpha03"

        const val DAGGER_HILT_ANDROID =
            "com.google.dagger:hilt-android:$DAGGER_HILT_ANDROID_VERSION"
        const val DAGGER_HILT_ANDROID_COMPILER =
            "com.google.dagger:hilt-android-compiler:$DAGGER_HILT_ANDROID_VERSION"
        const val DAGGER_HILT = "androidx.hilt:hilt-common:$DAGGER_HILT_VERSION"
        const val DAGGER_HILT_LIFECYCLE_VIEWMODEL =
            "androidx.hilt:hilt-lifecycle-viewmodel:$DAGGER_HILT_VERSION"
        const val DAGGER_HILT_COMPILER = "androidx.hilt:hilt-compiler:$DAGGER_HILT_VERSION"
        const val DAGGER_HILT_NAVIGATION = "androidx.hilt:hilt-navigation:$DAGGER_HILT_VERSION"
        const val DAGGER_HILT_ANDROID_GRADLE_PLUGIN =
            "com.google.dagger:hilt-android-gradle-plugin:$DAGGER_HILT_ANDROID_VERSION"
    }

    object JUnit {
        const val JUNIT = "junit:junit:4.13.2"
    }

    object Mockito {
        const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    }

    object Apollo {
        private const val VERSION = "2.5.3"

        const val GRADLE = "com.apollographql.apollo:apollo-gradle-plugin:$VERSION"
        const val RUNTIME = "com.apollographql.apollo:apollo-runtime:$VERSION"
        const val APOLLO_COROUTINES = "com.apollographql.apollo:apollo-coroutines-support:$VERSION"
    }

    object Accompanist {
        private const val VERSION = "0.6.0"

        const val ACCOMPANIST_COIL = "dev.chrisbanes.accompanist:accompanist-coil:$VERSION"
        const val ACCOMPANIST_INSETS = "dev.chrisbanes.accompanist:accompanist-insets:$VERSION"
    }
}