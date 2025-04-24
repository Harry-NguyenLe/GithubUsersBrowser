import java.io.FileInputStream
import java.util.Properties

fun getLocalProperty(key: String, project: Project): String {
    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(FileInputStream(localPropertiesFile))
        return properties.getProperty(key, "")
    }
    return ""
}

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-parcelize")
}

android {
    namespace = "com.tymex.interview.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "GITHUB_API_TOKEN",
                "\"${getLocalProperty("GITHUB_API_TOKEN", project)}\""
            )
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
        compose = true
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
    testImplementation(project(":shared-test"))

    // AndroidX Core (useful for Android Library modules, provides compatibility APIs)
    implementation(libs.androidx.core.ktx)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animation)

    // Jetpack Compose
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

    // Coil (Image Loading)
    implementation(libs.coil.compose) // For Jetpack Compose

    // Koin (Dependency Injection) - Implement BOM first
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    implementation(libs.kotlinx.coroutines.core)

    // Networking Libraries (using bundle or individual aliases)
    implementation(libs.bundles.retrofit)
    implementation(libs.squareup.moshi.kotlin)

    // Datastore
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.androidx.datastore.preferences)

    // Unit Testing
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Core testing libraries
    testImplementation(libs.test.junit)

    // Coroutines testing
    testImplementation(libs.kotlinx.coroutines.test)

    // MockK for mocking
    testImplementation(libs.mockk.core)
    testImplementation(libs.mockk.android)

    // Mockwebserver
    testImplementation(libs.test.okhttp.mockwebserver)

    // Turbine for Flow testing
    testImplementation(libs.turbine)

    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.test.junit)
    testImplementation(libs.test.mockito.kotlin)
    testImplementation(libs.test.mockito.inline)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.androidx.junit)
    androidTestImplementation(libs.test.androidx.espresso.core)
    testImplementation(kotlin("test"))
}