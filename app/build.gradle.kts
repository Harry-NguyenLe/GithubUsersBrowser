plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("jacoco")
    id("com.google.gms.google-services")
}

fun Project.ciSecret(name: String): String? =
    providers.environmentVariable(name).orNull ?: (findProperty(name) as String?)

val coverageVariant = "debug"
val coverageVariantTaskName = "Debug"

jacoco {
    toolVersion = "0.8.9"
}

tasks.withType<Test>().configureEach {
    extensions.configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }

}

tasks.register<JacocoReport>("jacocoTestReport") {
    description = "Generates the HTML documentation for this project."
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    dependsOn("test${coverageVariantTaskName}UnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val variantTree = fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/$coverageVariant")) {
        exclude("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*")
    }

    classDirectories.setFrom(variantTree)
    sourceDirectories.setFrom(files("src/main/java", "src/main/kotlin"))
    executionData.setFrom(layout.buildDirectory.file("jacoco/test${coverageVariantTaskName}UnitTest.exec"))
}

android {
    namespace = "com.githubusersbrowser"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.githubusersbrowser"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val signingStoreFile = project.ciSecret("SIGNING_STORE_FILE")
            val signingStorePassword = project.ciSecret("SIGNING_STORE_PASSWORD")
            val signingKeyAlias = project.ciSecret("SIGNING_KEY_ALIAS")
            val signingKeyPassword = project.ciSecret("SIGNING_KEY_PASSWORD")

            if (
                !signingStoreFile.isNullOrBlank() &&
                !signingStorePassword.isNullOrBlank() &&
                !signingKeyAlias.isNullOrBlank() &&
                !signingKeyPassword.isNullOrBlank()
            ) {
                storeFile = file(signingStoreFile)
                storePassword = signingStorePassword
                keyAlias = signingKeyAlias
                keyPassword = signingKeyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (
                !project.ciSecret("SIGNING_STORE_FILE").isNullOrBlank() &&
                !project.ciSecret("SIGNING_STORE_PASSWORD").isNullOrBlank() &&
                !project.ciSecret("SIGNING_KEY_ALIAS").isNullOrBlank() &&
                !project.ciSecret("SIGNING_KEY_PASSWORD").isNullOrBlank()
            ) {
                signingConfig = signingConfigs.getByName("release")
            }
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
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(project(":user-domain"))
    implementation(project(":user-data"))
    implementation(project(":user-ui"))
    implementation(project(":core"))
    testImplementation(project(":shared-test"))

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // Core Android & Kotlin
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animation)

    // Composable
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Koin (Dependency Injection) - Implement BOM first
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    implementation(libs.androidx.material3.android) // Includes koin-android, koin-androidx-compose

    // Testing (Unit Tests) - Use bundle
    testImplementation(libs.bundles.unit.test)
    // Koin Test - Use bundle
    testImplementation(libs.bundles.koin.test)


    // Testing (Android Instrumented Tests) - Use bundle
    androidTestImplementation(libs.bundles.android.test)
    // Turbine
    testImplementation(libs.turbine)
    // MockK for mocking
    testImplementation(libs.mockk.core)
    testImplementation(libs.mockk.android)
    // Use Compose BOM forandroidTestImplementation as well
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.compose.test) // Includes compose-ui-test-junit4

    // Debug dependencies - Use bundle
    debugImplementation(libs.bundles.compose.debug) // Includes compose-ui-tooling, compose-ui-test-manifest
}
