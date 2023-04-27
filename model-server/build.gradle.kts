plugins {
    application
}

val modelix_version: String by project

dependencies {
    implementation("org.modelix:model-server:$modelix_version")
}

application {
    mainClass.set("org.modelix.model.server.Main")
    applicationDefaultJvmArgs = listOf("-XX:MaxRAMPercentage=85")
}

tasks.run.configure {
    args("-inmemory", "-dumpin", "courses.modelserver.dump")
}
