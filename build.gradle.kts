plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}

buildscript {
    repositories {
        gradlePluginPortal()
    }

    val sqlDelightVersion = "1.5.5"

    dependencies {
        // ...
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("dev.icerock.moko:resources-generator:0.23.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    repositories {
        mavenCentral()
    }
}
