plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.node.gradle)
}

dependencies {

}

node {
    download.set(true)
    version.set(libs.versions.node)
}

val npmRun by tasks.creating(com.github.gradle.node.npm.task.NpmTask::class) {
    dependsOn(tasks.getByName("build"))
    args.addAll("run-script", "ng", "serve")
}

tasks.getByName("build").dependsOn("npmInstall")
