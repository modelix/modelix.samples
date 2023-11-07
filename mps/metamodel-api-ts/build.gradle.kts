import com.github.gradle.node.npm.task.NpmTask

plugins {
    base
    alias(libs.plugins.node.gradle)
}

node {
    download.set(true)
    version.set(libs.versions.node)
}

tasks.named("npm_run_build") {
    dependsOn(":mps:generateMetaModelSources")
}

tasks.named<NpmTask>("npm_pack") {
    val packageDirectory = project.layout.buildDirectory.dir("packages").get().asFile
    dependsOn("npm_run_build")
    doFirst {
        packageDirectory.mkdirs()
    }
    args.addAll("--pack-destination", packageDirectory.path)
}

tasks.assemble {
    dependsOn("npm_pack")
}