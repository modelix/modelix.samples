plugins {
    java
    alias(libs.plugins.modelix.model.api.gen)
}

val mps: Configuration by configurations.creating
val mpsDependencies: Configuration by configurations.creating

dependencies {
    mps(libs.mps)
    mpsDependencies(libs.mps.extensions)
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
    includeLanguage("University.Schedule.Time")
    includeLanguage("University.Schedule")
    includeNamespace("University.Schedule.sandbox")

    // include dependencies from the shared dependencies folder
    modulesFrom(projectDir.resolve("build/dependencies"))
    // and specifically adds repository related concepts
    includeLanguage("org.modelix.model.repositoryconcepts")

    // the target project into which the kotlin API will be build
    // (requires the project to exist in the gradle setup)
    kotlinDir = project(":mps:metamodel-api-kts").projectDir.resolve("src/main/kotlin")
    typescriptDir = project(":mps:metamodel-api-ts").projectDir.resolve("src/")

    // name of the registration helper class
    registrationHelperName = "University.Schedule.GeneratedLanguages"
}
