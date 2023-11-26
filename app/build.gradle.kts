plugins {
    id("com.android.application")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")


}

android {
    namespace = "com.android.magicconch"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.android.magicconch"
        minSdk = 33
        targetSdk = 33
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
}

dependencies {
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.0")
    implementation ("com.google.code.gson:gson:2.8.8")
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation("com.google.android.material:material:1.4.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-auth")
    implementation("org.chromium.net:cronet-embedded:113.5672.61")
    testImplementation("junit:junit:4.13.2")
    implementation("com.google.firebase:firebase-analytics")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.4.0"))
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation ("com.google.android.gms:play-services-location:18.0.0")
    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // Converter ( JSON 타입 결과를 객체로 매핑 )
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")


    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

}
