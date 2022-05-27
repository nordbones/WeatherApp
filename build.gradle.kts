buildscript {
    val compose_version by extra("1.2.0-beta01")
    val kotlin_version by extra("1.6.10")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}