plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.google.hilt)
}

android {
    namespace = "com.sparkfusion.data.admin"
    compileSdk = libs.versions.compileSDK.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSDK.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(project(":core:common"))

    implementation(project(":data:commonEntity"))

    implementation(project(":dataPort:admin:portServices"))
    implementation(project(":dataPort:admin:portAuth"))
    implementation(project(":dataPort:admin:portAccount"))
    implementation(project(":dataPort:admin:portInstitution"))
    implementation(project(":dataPort:admin:portAdminDetails"))
    implementation(project(":dataPort:admin:portInstitutionEvent"))
    implementation(project(":dataPort:admin:portNotification"))
    implementation(project(":dataPort:admin:portConfirmation"))

    implementation(project(":dataPort:admin:portAbout"))
    implementation(project(":dataPort:admin:portStudents"))
    implementation(project(":dataPort:admin:portExam"))
    implementation(project(":dataPort:admin:portSchedule"))
    implementation(project(":dataPort:admin:portMagazine"))
    implementation(project(":dataPort:admin:portStatistics"))
    implementation(project(":dataPort:admin:portSections"))

    implementation(project(":data:base"))
    implementation(project(":data:common"))

    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.converter.gson)

    implementation(libs.google.hilt)
    ksp(libs.google.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}