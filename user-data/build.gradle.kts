plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.tymex.interview.user_data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_BASE_URL", "\"https://api.github.com/\"")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":user-domain"))
    implementation(project(":core"))
    testImplementation(project(":shared-test"))

    // AndroidX Core
    implementation(libs.androidx.core.ktx)

    // Koin (Dependency Injection) - Implement BOM first
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin) // Includes koin-android, koin-androidx-compose

    // Coroutines (both core and android might be useful here for dispatchers etc.)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Networking Libraries (using bundle or individual aliases)
    implementation(libs.bundles.retrofit)
    implementation(libs.squareup.moshi.kotlin) // Explicit Moshi Kotlin support

    // Paging Runtime (if repositories handle pagination)
    implementation(libs.androidx.paging.runtime)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Unit Testing
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.jfixture)

    // Core testing libraries
    testImplementation(libs.test.junit)

    // Coroutines testing
    testImplementation(libs.kotlinx.coroutines.test)

    // MockK for mocking
    testImplementation(libs.mockk.core)
    testImplementation(libs.mockk.android)

    // Mockwebserver
    testImplementation(libs.test.okhttp.mockwebserver)

    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.test.junit)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.mockito.inline)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.androidx.junit)
    androidTestImplementation(libs.test.androidx.espresso.core)
    testImplementation(kotlin("test"))
}