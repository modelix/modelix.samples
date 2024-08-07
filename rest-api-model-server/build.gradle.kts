plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.allopen)
    alias(libs.plugins.quarkus.jvm)
    alias(libs.plugins.openapi.generator)
}

dependencies {
    // The version of the Kotlin stdlib included
    // by libs.plugins.kotlin.jvm and libs.modelix.model.client
    // is overridden by the version from the Quarkus platform.
    implementation(enforcedPlatform(libs.quarkus.bom))
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-websockets")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    implementation("io.quarkus:quarkus-container-image-jib")

    implementation(libs.modelix.model.client)
    implementation(libs.ktor.client.core)
    constraints {
        // The Ktor version 2.3.5 from the modelix release 23.2,
        // has a bug that makes it break for Quarkus.
        //
        // The bug stems from an upgraded Kotlin version,
        // which broke backward compatibility.
        // See https://youtrack.jetbrains.com/issue/KTOR-6354
        //
        // The Ktor version 2.3.6 provides a fix,
        // but that Ktor version is not used in modelix release 23.2.
        //
        // Because of that, we are explicitly constraining the Ktor version
        // of Ktor libraries to 2.3.6 or later (specified in libs.version.toml)
        implementation(libs.ktor.client.cio)
        implementation(libs.ktor.client.auth)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.ktor.serialization.json)
    }

    implementation(project(":mps:metamodel-api-kts"))
}

val basePackage = "org.modelix.sample.restapimodelserver"

val openApiFile = layout.projectDirectory.file("../openapi/openapi.yaml")

// We let the Gradle OpenAPI generator plugin build data classes and API interfaces based on the provided
// OpenAPI specification. That way, the code in this example is force to stay in sync with the API specification.
openApiGenerate {
    generatorName.set("kotlin-server")
    inputSpec.set(openApiFile.toString())
    outputDir.set("$buildDir/openapi-generator")
    packageName.set(basePackage)
    apiPackage.set(basePackage)
    modelPackage.set(basePackage)
    configOptions.set(
        mapOf(
            "library" to "jaxrs-spec",
            "interfaceOnly" to "true",
        )
    )
}

// Provide the OpenAPI definition declared up-front to the Swagger UI served by Quarkus.
// Cf. https://quarkus.io/guides/openapi-swaggerui#loading-openapi-schema-from-static-files
tasks.register<Copy>("installOpenAPISchema") {
    from(openApiFile)
    into(layout.buildDirectory.dir("classes/kotlin/main/META-INF"))
}

// Ensure that the OpenAPI generator runs before starting to compile
tasks.named("processResources") {
    dependsOn("openApiGenerate")
    dependsOn("installOpenAPISchema")
}
tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

java.sourceSets.getByName("main").java.srcDir(file("$buildDir/openapi-generator/src/main/kotlin"))

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
