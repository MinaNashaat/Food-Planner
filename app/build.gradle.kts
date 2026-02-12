plugins {
    alias(libs.plugins.android.application)
//    id("androidx.navigation.safeargs")
    alias(libs.plugins.navigation.safeargs)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.mina.foodplanner"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mina.foodplanner"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.lottie)
    implementation(platform("com.google.firebase:firebase-bom:34.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.github.bumptech.glide:glide:5.0.5")
    implementation("androidx.room:room-runtime:2.8.4")
    annotationProcessor("androidx.room:room-compiler:2.8.4")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
    implementation("com.google.firebase:firebase-firestore:26.0.0")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
    implementation("androidx.room:room-rxjava3:2.8.4")
}