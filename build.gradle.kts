// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

// Performance optimizations for all subprojects
allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-Xjvm-default=all"
            )
        }
    }
    
    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf(
            "-Xlint:unchecked",
            "-Xlint:deprecation"
        ))
    }
}