plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.pieski"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pieski"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.compose.compiler:compiler:1.5.4")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.material:material:1.6.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.1")

    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.compose.material:material:1.6.1")

    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.moshi:moshi:1.15.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")

    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))

}
