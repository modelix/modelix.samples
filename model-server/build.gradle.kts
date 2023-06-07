plugins {
    application
}

dependencies {
    implementation(libs.modelix.model.server)
}

application {
    mainClass.set("org.modelix.model.server.Main")
}

tasks.run.configure {
    args("-inmemory", "-dumpin", "courses.modelserver.dump")
}
