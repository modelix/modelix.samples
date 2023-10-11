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
    dependsOn("npm_run_build")
    args.addAll("--pack-destination", "./build/packages")
}

tasks.assemble {
    dependsOn("npm_pack")
}