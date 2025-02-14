import org.jetbrains.kotlin.fir.types.builder.buildTypeProjectionWithVariance

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka") version "1.9.10"
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
    task<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(android.sourceSets["main"].java.srcDirs)
    }

    task<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        from(tasks["dokkaHtml"]) // Use Dokka for Kotlin documentation
    }

    publishing {
        // Enable publishing for the release variant
//        singleVariant("release") {
//            buildTypeProjectionWithVariance { configurations.getByName("release") }
//        }
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

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

val libVersion = "1.0.0"
val libGroup = "dev.hoangnam9194.library"
val libArtifactId = "linkattributionn"

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = libGroup // e.g., com.example
            artifactId = libArtifactId // e.g., mylibrary
            version = libVersion // Your library version

            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("My Library Name")
                description.set("A brief description of your library")
                url.set("https://github.com/hoangnam9194/test-library")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("hoangnam9194")
                        name.set("Nam Nguyen")
                        email.set("hoangnam9194@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:github.com/hoangnam9194/test-library.git")
                    developerConnection.set("scm:git:ssh://github.com/hoangnam9194/test-library.git")
                    url.set("https://github.com/hoangnam9194/test-library/tree/main")
                }
            }
        }
    }

    repositories {
        maven {
            name = "MavenCentral"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername") as String? ?: ""
                password = project.findProperty("ossrhPassword") as String? ?: ""
            }
        }
    }
}

signing {
    val signingKey = System.getenv("GPG_SIGNING_KEY")
    val signingPassword = System.getenv("GPG_SIGNING_PASSWORD")
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["release"])
}