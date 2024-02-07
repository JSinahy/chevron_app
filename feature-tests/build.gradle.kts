plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = General.nameSpace
    compileSdk = General.compileSDK.toInt()

    defaultConfig {
        minSdk = General.minSDK.toInt()

        testInstrumentationRunner = General.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(Dependencies.androidCore)
    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.androidConstraitLayout)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation(Dependencies.androidJUnit)
    androidTestImplementation(Dependencies.androidExtUnit)
    androidTestImplementation(Dependencies.androidEspresso)

    implementation(project(Modules.moduleCommon))
}