// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    //구글 서비스 추가
    id("com.google.gms.google-services") version "4.4.0" apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // 다른 의존성 추가

        classpath ("com.google.gms:google-services:4.3.10")  // 버전은 업데이트 될 수 있습니다.
    }
}