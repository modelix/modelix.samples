import de.itemis.mps.gradle.BuildLanguages

plugins {
    java
}

buildscript {
    repositories {
        maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }
        mavenCentral()
    }

    dependencies {
        classpath(libs.itemis.mps.gradle.plugin)
    }
}

repositories {
    mavenCentral()
}

val mps: Configuration by configurations.creating
val buildDependencies: Configuration by configurations.creating
val mpsDependencies: Configuration by configurations.creating

dependencies {
    buildDependencies(libs.ant.junit)
    mps(libs.mps)
    mpsDependencies(libs.mps.extensions)
    mpsDependencies(libs.modelix.syncPlugin)
}

val mpsDir = file("$buildDir/mps")
val artifactsDir = file("$buildDir/artifacts")
val dependenciesDir = file("$buildDir/dependencies")

val extractMps by tasks.registering(Copy::class) {
    from({ mps.resolve().map { zipTree(it) } })
    into(mpsDir)
}

val extractMpsDependencies by tasks.registering(Copy::class) {
    from({ mpsDependencies.resolve().map { zipTree(it) } })
    into(dependenciesDir)
}

fun antVar(name: String, value: String)  = "-D$name=$value"

ext["itemis.mps.gradle.ant.defaultScriptArgs"] =
    listOf(
        antVar("mps_home", mpsDir.absolutePath),
        antVar("artifacts_home", artifactsDir.absolutePath),
        antVar("mps.generator.skipUnmodifiedModels", "true"),
        antVar("sample.home", projectDir.absolutePath),
        antVar("dependencies.home", dependenciesDir.absolutePath)
    )
ext["itemis.mps.gradle.ant.defaultScriptClasspath"] = buildDependencies.fileCollection { true }

val setup by tasks.registering {
    group = "setup"
    description = "Download and extract MPS and the projects MPS dependencies."
    dependsOn(extractMps)
    dependsOn(extractMpsDependencies)
}

val buildLanguages by tasks.registering(BuildLanguages::class) {
    group = "build"
    description = "Build all languages in the MPS project"
    script = "$projectDir/build.xml"
    inputs.file(file("$projectDir/build.xml"))
    inputs.files(fileTree("$projectDir/solutions").include("**/*.mps", "**/*.msd")).withPropertyName("mps-solution")
    inputs.files(fileTree("$projectDir/languages").include("**/*.mps", "**/*.msd")).withPropertyName("mps-languages")
    outputs.dir("$projectDir/languages/University.Schedule/source_gen")
    dependsOn(setup)
}

tasks.getByName("build").dependsOn(buildLanguages)
