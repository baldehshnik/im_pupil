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
    namespace = "com.sparkfusion.domain.admin.confirmation"
    compileSdk = libs.versions.compileSDK.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSDK.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val teacherIconUrlProperty = localProperties.getProperty(
            "teacher_icon_url", null
        ) ?: throw GradleException("TEACHER_ICON_URL is not defined")
        val pupilIconUrlProperty = localProperties.getProperty(
            "pupil_icon_url", null
        ) ?: throw GradleException("PUPIL_ICON_URL is not defined")

        buildConfigField("String", "TEACHER_ICON_URL", "\"$teacherIconUrlProperty\"")
        buildConfigField("String", "PUPIL_ICON_URL", "\"$pupilIconUrlProperty\"")
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
        buildConfig = true
    }
}

dependencies {

    implementation(project(":core:common"))

    implementation(project(":dataPort:admin:portConfirmation"))
    implementation(project(":dataPort:admin:portInstitution"))

    implementation(project(":domain:admin:port:portConfirmation"))

    implementation(libs.google.hilt)
    ksp(libs.google.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}