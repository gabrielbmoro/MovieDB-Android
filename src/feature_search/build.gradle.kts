@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.kover)
}

android {
    namespace = "com.gabrielbmoro.moviedb.feature.search"
}

dependencies {
    implementation(projects.data)
    implementation(projects.core)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose.impl)
    debugImplementation(libs.bundles.compose.debug.impl)
    implementation(libs.bundles.compose.extras)

    // Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)

    implementation(libs.bundles.lifecycle)

    testImplementation(libs.bundles.test)
}
