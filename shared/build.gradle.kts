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
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
    }

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

                val voyagerVersion = "1.0.0-rc05"

                // Multiplatform

                // Navigator
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

                // BottomSheetNavigator
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")

                // TabNavigator
                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

                // Transitions
                implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

                // Android ViewModel integration
                implementation("cafe.adriel.voyager:voyager-androidx:$voyagerVersion")

                // Koin integration
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
                implementation("dev.icerock.moko:resources-test:0.23.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.sqlDelight.android)
                implementation(libs.ktor.client.okHttp)
                implementation("io.ktor:ktor-client-okhttp")
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
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Internal // optional, default Public
    iosBaseLocalizationRegion = "en" // optional, default "en"
    multiplatformResourcesSourceSet = "commonMain"  // optional, default "commonMain"
}