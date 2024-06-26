plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.openapi.generator)
    id("io.ktor.plugin") version "2.3.12"
}

val openApiFile = layout.projectDirectory.file("../openapi/openapi.yaml")

dependencies {
    // api-gen v2
    implementation(project(":mps:metamodel-api-kts"))

    // kotlin
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.default.headers)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.auto.head.response)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.server.locations)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.websockets)

    testImplementation(libs.junit.junit)

    api(libs.modelix.model.api)
    implementation(libs.modelix.light.model.client)
    implementation(libs.modelix.model.server.api)
    implementation(libs.modelix.model.client)
    implementation(libs.ktor.client.cio)
}

val basePackage = "org.modelix.sample.restapimodelql"

// Use the OpenAPI generator to generate data classes representing the REST response data types.
// Unfortunately, other generated artifacts cannot be used, because the generator still assumes
// ktor 1.x and doesn't have an interfaceOnly mode such as in the Quarkus example.
openApiGenerate {
    generatorName.set("kotlin-server")
    inputSpec.set(openApiFile.toString())
    outputDir.set("$buildDir/openapi-generator")
    packageName.set(basePackage)
    // The OpenAPI generator gradle plugin is strange. According to the documentation of the generator itself,
    // one has to specify `true` to generate all models. However, the plugin does something such that `true`
    // is assumed to be the name of a specific model to generate only then. Strangely, specifying an empty
    // string configures the plugin in such a way that all available models are generated and nothing else.
    globalProperties.set(
        mapOf(
            "models" to "",
        )
    )
    configOptions.set(
        mapOf(
            "library" to "ktor",
        )
    )
}

// Ensure that the OpenAPI generator runs before starting to compile
tasks.named("processResources") {
    dependsOn("openApiGenerate")
}
tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
}

java.sourceSets.getByName("main").java.srcDir(file("$buildDir/openapi-generator/src/main/kotlin"))

application {
    mainClass.set("org.modelix.sample.restapimodelql.ApplicationKt")
}

ktor {
    docker {
        localImageName.set("modelix/rest-api-model-ql")
        imageTag.set("latest")
    }
}
