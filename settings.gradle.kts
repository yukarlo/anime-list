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
    ":feature-anime-home-api",
    ":core-navigation",
    ":core-remote",
    ":core-model",
    ":lib-anime",
    ":feature-anime-domain",
    ":feature-anime-list",
    ":feature-anime-list-api",
    ":feature-anime-details",
    ":feature-anime-details-api",
    ":feature-anime-search",
    ":feature-anime-search-api",
    ":feature-about",
    ":feature-about-api",
    ":feature-main"
)
