plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.zenbyte.studio.wavesphere"
   /* compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }*/

    compileSdk = 37

    defaultConfig {
        applicationId = "com.zenbyte.studio.wavesphere"
        minSdk = 24
        targetSdk = 37
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation3.ui)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(project(":presentation"))

    // retrofit android
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // hilt android
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.kotlin.metadata.jvm)

    // hilt navigation compose
    implementation(libs.androidx.hilt.navigation.compose)

    // coil compose for image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // paging3 compose
    implementation(libs.androidx.paging.compose)

    // exo player android
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.exoplayer.hls)
    implementation(libs.androidx.media3.session)

    // navigation
    implementation(libs.navigation3.ui)
    implementation(libs.jetbrains.lifecycle.viewmodelNavigation3)

    //kotlin x serilization json
    implementation(libs.kotlin.serialization.json)

    // icon ectended
    implementation(libs.material.icons.extended)

    implementation(libs.seeker)

    // lottie android
    implementation(libs.lottie.compose)
    implementation(libs.androidx.media)
    implementation(libs.androidx.palette.ktx)

    // room android
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.swipeable.kmp)

    implementation(libs.kotlinx.coroutines.guava)

// Manages the Locale used by the app
    implementation("dev.b3nedikt.applocale:applocale:3.1.0")
// Needed to intercept view inflation
    implementation("dev.b3nedikt.viewpump:viewpump:4.0.15")
// Allows to update the text of views at runtime without recreating the activity
    implementation("dev.b3nedikt.reword:reword:4.0.4")

}