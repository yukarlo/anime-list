pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Anime"
include(
    ":app",
    ":common-android",
    ":feature-anime-home",
    ":remote",
    ":core-model",
    ":lib-anime",
    ":feature-anime-list",
    ":feature-anime-details",
    ":feature-anime-search",
    ":feature-about"
)
