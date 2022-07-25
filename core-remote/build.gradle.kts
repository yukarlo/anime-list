plugins {
    id("yukarlo.android.library")
    id("com.apollographql.apollo3")
    id("dagger.hilt.android.plugin")
}

apollo {
    packageName.set("com.yukarlo")
}

dependencies {
    implementation(project(":core-model"))

    implementation(libs.kotlin.stdlib)
    implementation(libs.graphql.apollo3.runtime)
    implementation(libs.coroutines.core)

    implementation(libs.hilt.android.core)
    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit4)
}
