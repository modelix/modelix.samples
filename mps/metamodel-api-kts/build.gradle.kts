plugins {
    kotlin("jvm")
}

dependencies {
    api(libs.modelix.model.api.gen.runtime)
}

kotlin {
    compilerOptions {
        // Only use features from Kotlin 1.7.
        // This is the version used by the used Quarkus release.
        // It ensures, that the generated code can be used by Quarkus.
        // see. https://quarkus.io/version/main/guides/kotlin#kotlin-version
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_7)
    }
}
