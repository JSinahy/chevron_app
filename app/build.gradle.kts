plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = General.nameSpace
    compileSdk = General.compileSDK.toInt()

    defaultConfig {
        applicationId = General.applicationId
        minSdk = General.minSDK.toInt()
        targetSdk = General.compileSDK.toInt()
        versionCode = General.versionCode.toInt()
        versionName = General.versionName

        testInstrumentationRunner = General.testInstrumentationRunner
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }

}

dependencies {

    implementation(Dependencies.androidCore)
    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.androidConstraitLayout)
    testImplementation(Dependencies.androidJUnit)
    androidTestImplementation(Dependencies.androidExtUnit)
    androidTestImplementation(Dependencies.androidEspresso)

    implementation(project(Modules.moduleCommon))
    implementation(project(Modules.moduleAccounts))
    implementation(project(Modules.moduleAuthentication))
    implementation(project(Modules.moduleContent))
    implementation(project(Modules.moduleTests))
    implementation(project(Modules.moduleTrends))
}