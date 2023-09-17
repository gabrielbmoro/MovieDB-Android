@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.gabrielbmoro.moviedb.feature.movies"
}

dependencies {

    api(project(":core"))
    api(project(":repository"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.bom.ui)
    implementation(libs.compose.bom.preview)
    implementation(libs.compose.bom.activity)
    debugImplementation(libs.compose.bom.ui.tooling)
    debugImplementation(libs.compose.bom.ui.test.manifest)
    implementation(libs.bundles.compose.extras)

    implementation(libs.bundles.lifecycle)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)

    // Test
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.test)
    androidTestImplementation(libs.ui.compose.test)
}