plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.21"
    id("com.squareup.sqldelight")
    id("dev.icerock.mobile.multiplatform-resources")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
//            export("dev.icerock.moko:resources:0.23.0")
//            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
    }

    val napierVersion = "2.6.1"
    val ktorVersion = "2.2.4"
    val compose_image = "1.2.10"
    val koin_core_version = "3.4.0"
    val koin_android_version = "3.3.3"
    val koin_android_compose_version = "3.4.2"
    val voyagerVersion = "1.0.0-rc04"
    val mokoResourcesVersion = "0.21.2"

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.koin.core)
                implementation(libs.coroutines.core)
                implementation(libs.sqlDelight.coroutinesExt)
                implementation(libs.bundles.ktor.common)
                implementation(libs.touchlab.stately)
                implementation(libs.multiplatformSettings.common)
                implementation(libs.kotlinx.dateTime)
                api(libs.touchlab.kermit)
                implementation(libs.kotlinx.serialization.json)

                implementation(platform("com.aallam.openai:openai-client-bom:3.3.0"))

                // Multiplatform

                api("io.insert-koin:koin-core:$koin_core_version")

                api("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
                api("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
                implementation("cafe.adriel.voyager:voyager-koin:$voyagerVersion")

                // define dependencies without versions
                implementation("com.aallam.openai:openai-client")
                runtimeOnly ("io.ktor:ktor-client-okhttp")

                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:0.23.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
//                implementation("dev.icerock.moko:resources-test:0.23.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.sqlDelight.android)
                implementation(libs.ktor.client.okHttp)
                implementation("io.ktor:ktor-client-okhttp")
                api("io.insert-koin:koin-android:$koin_android_version")
                // Jetpack WorkManager
                api("io.insert-koin:koin-androidx-workmanager:$koin_android_version")
                // Navigation Graph
                api("io.insert-koin:koin-androidx-navigation:$koin_android_version")
                // Compose
                api("io.insert-koin:koin-androidx-compose:$koin_android_compose_version")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.sqlDelight.native)
                implementation(libs.ktor.client.ios)
            }
        }
    }
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

sqldelight {
    database("KaMPKitDb") {
        packageName = "co.touchlab.kampkit.db"
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.example.myapplication" // required
    multiplatformResourcesClassName = "SharedRes" // optional, default MR
    multiplatformResourcesSourceSet = "commonMain"  // optional, default "commonMain"
}