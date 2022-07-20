plugins {
    kotlin("jvm") version "1.7.10"
}

group = "pw.avi"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.arangodb:arangodb-java-driver:6.18.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}