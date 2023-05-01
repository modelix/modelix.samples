plugins {
    kotlin("jvm") version "1.8.20" apply false
    kotlin("kapt") version "1.8.21" apply false
    id("com.specificlanguages.mps") version "1.5.0" apply false
    id ("com.github.node-gradle.node") version "3.2.1" apply false
    // we use the java plugin so that we can use the implementation in the
    // subprojects block to declare modelix platform dependencies for all
    // subprojects
    java
}

subprojects {
    // as this sample is a giant monorepo, we can enforce the modelix platform
    // to be used everywhere here. as a result we will not have to pick
    // versions for individual modelix artifacts anywhere
    apply(plugin = "java")
    val modelix_platform_version: String by project
    dependencies {
        implementation(enforcedPlatform("org.modelix:platform-mps-2021-2:$modelix_platform_version"))
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    repositories {
        mavenCentral()

        maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }

        maven {
             url = uri("https://maven.pkg.github.com/modelix/mps-json-bulk-model-access")
             credentials {
                 username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
                 password = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")
             }
        }

        maven {
            url = uri("https://maven.pkg.github.com/modelix/api-gen")
            credentials {
                username = project.findProperty("gpr.user")?.toString() ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key")?.toString() ?: System.getenv("TOKEN")
            }
        }
    }
}
