plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.cookingguideapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cookingguideapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("androidx.navigation:navigation-fragment:2.5.3")
    implementation ("androidx.navigation:navigation-ui:2.5.3")
    implementation ("com.google.firebase:firebase-database:20.2.2")
    implementation (platform("com.google.firebase:firebase-bom:32.1.0"))
    implementation ("com.google.firebase:firebase-database-ktx:20.2.2")
    implementation ("com.google.firebase:firebase-auth:22.3.1")

    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21")
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
    implementation ("androidx.room:room-runtime:2.5.1")
    implementation ("com.google.firebase:firebase-crashlytics-buildtools:2.9.5")
    implementation ("androidx.annotation:annotation:1.6.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("com.facebook.android:facebook-android-sdk:[4,5)")
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")

    annotationProcessor ("androidx.room:room-compiler:2.5.1")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

    implementation ("de.hdodenhof:circleimageview:3.1.0")
}


