plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.tymex.interview.homeassesmenthainlt"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tymex.interview.homeassesmenthainlt"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":user-data"))
    implementation(project(":user-ui"))
    implementation (project(":core"))

    // Core Android & Kotlin
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Koin (Dependency Injection) - Implement BOM first
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin) // Includes koin-android, koin-androidx-compose

    // Testing (Unit Tests) - Use bundle
    testImplementation(libs.bundles.unit.test)
    // Koin Test - Use bundle
    testImplementation(libs.bundles.koin.test)


    // Testing (Android Instrumented Tests) - Use bundle
    androidTestImplementation(libs.bundles.android.test)
    // Use Compose BOM forandroidTestImplementation as well
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.compose.test) // Includes compose-ui-test-junit4

    // Debug dependencies - Use bundle
    debugImplementation(libs.bundles.compose.debug) // Includes compose-ui-tooling, compose-ui-test-manifest
}