plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    // Networking Libraries (using bundle or individual aliases)
    implementation(libs.bundles.retrofit)
    implementation(libs.squareup.moshi.kotlin)
    
    // Core testing libraries
    implementation(libs.test.junit)

    // Coroutines testing
    implementation(libs.kotlinx.coroutines.test)

    // Mockwebserver
    implementation(libs.test.okhttp.mockwebserver)

    // Turbine for Flow testing
    implementation(libs.turbine)

    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.test.junit)
    implementation(libs.test.mockito.kotlin)
    implementation(libs.test.mockito.inline)
    implementation(libs.test.junit)
    implementation(kotlin("test"))
}
