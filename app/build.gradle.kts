
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    // KSP
    id("com.google.devtools.ksp")
    // Parcelize
    id("kotlin-parcelize")
}

android {
    namespace = ConfigData.APPLICATION_ID
    compileSdk = ConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = ConfigData.APPLICATION_ID
        minSdk = ConfigData.MIN_SDK_VERSION
        targetSdk = ConfigData.TARGET_SDK_VERSION
        versionCode = ConfigData.VERSION_CODE
        versionName = ConfigData.VERSION_NAME

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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    // Navigation Component
    implementation(Dependencies.NAVIGATION)
    implementation(Dependencies.NAVIGATION_KTX)
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // Hilt
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_ANDROID_COMPILER)
    // Tests
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.JUNIT_EXT)
    androidTestImplementation(Dependencies.ESPRESSO)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}