plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
}

group = "pw.avi"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("com.arangodb:arangodb-java-driver:6.18.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}