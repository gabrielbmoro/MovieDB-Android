@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

include(
    ":androidApp",
    ":data",
    ":core",
    ":feature_movies",
    ":feature_details",
    ":feature_wishlist",
    ":feature_search",
    ":domain",
    ":navigation"
)

rootProject.name = "MovieDB-Android"
