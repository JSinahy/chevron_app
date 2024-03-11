plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("com.google.firebase.appdistribution")
    kotlin("kapt")
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

    signingConfigs {
        create("release") {
            keyAlias = "chevron"
            keyPassword = "Chevron*2022"
            storeFile = file("trend.jks")
            storePassword = "Chevron*2022"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false

            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotesFile = "./notes.txt"
                testers = "jslaraestudillo@gmail.com"
            }
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

    implementation(Dependencies.androidHiltCore)
    kapt(Dependencies.androidHiltCompiler)

    implementation(project(Modules.moduleCommon))
    //implementation(project(Modules.moduleAuthentication))
    implementation(project(Modules.moduleContent))
    //implementation(project(Modules.moduleTrends))
    //implementation(project(Modules.moduleTests))
}