import org.jetbrains.kotlin.fir.types.builder.buildTypeProjectionWithVariance

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
    id("maven-publish")
//    id("signing")
//    id("org.jetbrains.dokka") version "1.9.10"
//    alias(libs.plugins.kotlin.compose)
}


android {
    namespace = "com.library.link_attribution"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        buildConfigField("String", "LIBRARY_VERSION", "\"1.0.0\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
//        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    // Sources JAR
//    task<Jar>("sourcesJar") {
//        archiveClassifier.set("sources")
//        from(android.sourceSets["main"].java.srcDirs)
//    }
//
//    task<Jar>("javadocJar") {
//        archiveClassifier.set("javadoc")
//        from(tasks["dokkaHtml"]) // Use Dokka for Kotlin documentation
//    }

//    publishing {
//        // Enable publishing for the release variant
////        singleVariant("release") {
////            buildTypeProjectionWithVariance { configurations.getByName("release") }
////        }
//        singleVariant("release") {
//            withSourcesJar()
//            withJavadocJar()
//        }
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.1"
//    }
}

dependencies {
    implementation(project(":data:shared"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.window)

    // Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.auth)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)

    // Required for Ktor
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.github.hoangnam9194" // Replace with your GitHub username
                artifactId = "test-library" // Replace with your library's name (e.g., my-awesome-library)
                version = "1.0.0" // Initial version number (important!)

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
}
