plugins {
    java
    id("org.modelix.model-api-gen")
}

val mps: Configuration by configurations.creating
val mps_version: String by project
val mpsDependencies: Configuration by configurations.creating
val mpsExtensions_version: String by project
dependencies {
    mps("com.jetbrains:mps:$mps_version")
    mpsDependencies("de.itemis.mps:extensions:$mpsExtensions_version")
}

val mpsDir = file("$buildDir/mps")
val extractMps by tasks.registering(Copy::class) {
    from({ mps.resolve().map { zipTree(it) } })
    into(mpsDir)
}

val dependenciesDir = file("$buildDir/dependencies")
val extractMpsDependencies by tasks.registering(Copy::class) {
    from({ mpsDependencies.resolve().map { zipTree(it) } })
    into(dependenciesDir)
}

// Generate the API classes
metamodel {
    dependsOn(extractMps)
    dependsOn(extractMpsDependencies)

    mpsHome = mpsDir

    // restrictions on languages and solutions
    modulesFrom(projectDir.resolve("languages"))
    modulesFrom(projectDir.resolve("solutions"))

    // further restrictions on language/solution names
    includeNamespace("University.Schedule.sandbox")
    includeLanguage("University.Schedule")

    // include dependencies from the shared dependencies folder
    modulesFrom(projectDir.resolve("build/dependencies"))
    // and specifically adds repository related concepts
    includeLanguage("org.modelix.model.repositoryconcepts")

    // the target project into which the kotlin API will be build
    // (requires the project to exist in the gradle setup)
    kotlinDir = project(":mps:metamodel").projectDir.resolve("src/main/kotlin")

    // name of the registration helper class
    registrationHelperName = "University.Schedule.GeneratedLanguages"
}