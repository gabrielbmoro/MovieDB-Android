@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    alias(libs.plugins.kover)
}

android {
    namespace = "com.gabrielbmoro.moviedb.data"
}

dependencies {
    implementation(libs.preferences.ktx)

    implementation(libs.timber)

    implementation(libs.bundles.retrofit)

    ksp(libs.room.compiler)
    implementation(libs.bundles.room)

    implementation(libs.paging.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Test
    testImplementation(libs.bundles.test)
}
