import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.google.hilt)
}

val localPropertiesFile = rootProject.file("local.properties")
val localProperties = Properties().apply {
    load(FileInputStream(localPropertiesFile))
}

android {
    namespace = "com.sparkfusion.core.hilt_core"
    compileSdk = libs.versions.compileSDK.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSDK.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val urlProperty = localProperties.getProperty(
            "api_url", null
        ) ?: throw GradleException("API_URL is not defined")
        buildConfigField("String", "API_URL", "\"$urlProperty\"")
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

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":core:common"))

    implementation(project(":domain:admin:services"))
    implementation(project(":domain:admin:auth"))
    implementation(project(":domain:common:news"))
    implementation(project(":domain:admin:home"))
    implementation(project(":domain:admin:account"))
    implementation(project(":domain:admin:adminDetails"))
    implementation(project(":domain:admin:post"))
    implementation(project(":domain:admin:notification"))
    implementation(project(":domain:admin:confirmation"))

    implementation(project(":domainAdminServices:about"))
    implementation(project(":domainAdminServices:students"))
    implementation(project(":domainAdminServices:exam"))
    implementation(project(":domainAdminServices:schedule"))

    implementation(project(":data:admin"))
    implementation(project(":data:base"))
    implementation(project(":data:common"))

    implementation(libs.squareup.okhttp)

    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter.gson)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.google.hilt)
    ksp(libs.google.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}