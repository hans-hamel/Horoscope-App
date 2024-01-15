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
    // Safe Args
    id("androidx.navigation.safeargs.kotlin")
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
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "appname", "Horoscope App")
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }
        getByName("debug") {
            isDebuggable = true
            resValue("string", "appname", "Debug Horoscope App")
            buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
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
        buildConfig = true
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
    implementation(Dependencies.COROUTINES)
    // Hilt
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_ANDROID_COMPILER)
    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON)
    implementation(Dependencies.RETROFIT_LOGGING)
    // Camera X
    implementation (Dependencies.CAMERA_CORE)
    implementation (Dependencies.CAMERA2)
    implementation (Dependencies.CAMERA_LIFECYCLE)
    implementation (Dependencies.CAMERA_VIEW)
    implementation (Dependencies.CAMERA_EXTENSIONS)
    // Tests
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.JUNIT_EXT)
    androidTestImplementation(Dependencies.ESPRESSO)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}