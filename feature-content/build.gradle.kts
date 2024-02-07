plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
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
    implementation(Dependencies.androidLifeCycleLivedata)
    implementation(Dependencies.androidLifeCycleViewModel)
    implementation(Dependencies.androidNavigationFragment)
    implementation(Dependencies.androidNavigationUI)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation(Dependencies.androidJUnit)
    androidTestImplementation(Dependencies.androidExtUnit)
    androidTestImplementation(Dependencies.androidEspresso)

    // Hilt
    implementation(Dependencies.androidHiltCore)
    kapt(Dependencies.androidHiltCompiler)

    // Lifecycle
    implementation(Dependencies.androidLifeCycleLivedata)
    implementation(Dependencies.androidLifeCycleViewModel)
    implementation(Dependencies.androidLifeCycle_Activity)

    // Retrofit
    implementation(Dependencies.androidRetrofit)
    implementation(Dependencies.androidGson)
    implementation(Dependencies.androidInterceptor)
    implementation(Dependencies.androidOkhttp)
    implementation(Dependencies.androidCoroutinesKotlinx)
    implementation(Dependencies.androidCoroutinesKotlinxCore)

    // Glide
    implementation(Dependencies.androidGlide)

    implementation(project(Modules.moduleCommon))
    implementation(project(Modules.moduleTests))
}