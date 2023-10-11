import com.github.gradle.node.npm.task.NpmTask

plugins {
    base
    alias(libs.plugins.node.gradle)
}

node {
    download.set(true)
    version.set(libs.versions.node)
}

val metamodelApiTsProject = project(":mps:metamodel-api-ts")
val metamodelApiTsPackagePath: String = metamodelApiTsProject.layout.buildDirectory
    .file("packages/metamodel-api-ts-1.0.0.tgz").get().asFile.path

val updateMetaModelApiTs = tasks.register<NpmTask>("updateMetaModelApiTs") {
    dependsOn(metamodelApiTsProject.tasks.assemble)
    args.set(listOf("install", metamodelApiTsPackagePath))
}

tasks.npmInstall {
    dependsOn(updateMetaModelApiTs)
}

tasks.assemble {
    dependsOn("npm_run_build")
}

tasks.check {
    dependsOn("npm_run_lint")
}