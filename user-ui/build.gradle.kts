plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.tymex.interview.user_ui"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
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
    implementation(project(":core"))
    implementation(project(":user-domain"))
    testImplementation(project(":shared-test"))

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animation)

    // Compose ViewModel/Lifecycle integration
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.activity.compose)

    // Datastore
    implementation(libs.androidx.datastore.preferences)

    // Coil (Image Loading)
    implementation(libs.coil.compose) // For Jetpack Compose

    // Paging Runtime (if repositories handle pagination)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    // Timber logging
    implementation(libs.timber)

    // Koin (Dependency Injection) - Implement BOM first
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin) // Includes koin-android, koin-androidx-compose
    implementation(libs.kotlinx.coroutines.core)

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

    // Turbine
    testImplementation(libs.turbine)

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