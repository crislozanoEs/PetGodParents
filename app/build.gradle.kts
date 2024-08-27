plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")

}

android {
    namespace = "com.petgodparents.petgodparents"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.petgodparents.petgodparents"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.databinding.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)
    kapt(libs.androidx.lifecycle.kapt)
    // Coroutines
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)
    // Firebase
    implementation(platform(libs.firebase.core))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.android.play.services)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Room
    implementation(libs.room.ktx)
    implementation(libs.room.android)
    kapt(libs.room.compiler)
}
