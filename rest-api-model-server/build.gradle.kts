plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.allopen)
    alias(libs.plugins.quarkus.jvm)
    alias(libs.plugins.openapi.generator)
}

dependencies {
    implementation(enforcedPlatform(libs.quarkus.bom))
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-websockets")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")

    implementation(libs.ktor.client.core)
    implementation(libs.modelix.model.client)
    implementation(libs.kotlin.stdlib)

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
