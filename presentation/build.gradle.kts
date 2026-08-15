plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.zenbyte.studio.presentation"
    compileSdk = 37

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.androidx.constraintlayout.compose)
    api(project(":domain"))
    implementation(project(":data"))
    implementation(libs.androidx.lifecycle.viewmodel.ktx)



    // paging3 dependency
    implementation(libs.androidx.paging.common)


    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)





    implementation(project(":domain"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.kotlin.metadata.jvm)

    // coil for image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)


    // android lottie
    implementation(libs.lottie.compose)

    // android shimmer
    implementation(libs.compose.shimmer)

    //kotlin x serilization json
    implementation(libs.kotlin.serialization.json)

    // navigation
    implementation(libs.navigation3.ui)
    implementation(libs.jetbrains.lifecycle.viewmodelNavigation3)

    // exo player android
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.session)

    // hilt navigation compose
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.swipeable.kmp)

    // icon ectended
    implementation(libs.material.icons.extended)

    implementation(libs.seeker)


    // Manages the Locale used by the app
    implementation("dev.b3nedikt.applocale:applocale:3.1.0")
// Needed to intercept view inflation
    implementation("dev.b3nedikt.viewpump:viewpump:4.0.15")
// Allows to update the text of views at runtime without recreating the activity
    implementation("dev.b3nedikt.reword:reword:4.0.4")
}