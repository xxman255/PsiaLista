buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}

plugins {
    kotlin("android") version "1.9.0" apply false
    kotlin("kapt") version "1.9.0" apply false
}

