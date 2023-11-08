plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.node.gradle) apply false
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

//    dependencies {
//        // cannot use the catalog, see https://github.com/gradle/gradle/issues/23255#issuecomment-1364039826
//        implementation(platform("org.modelix:platform-mps-2021-2"))
//   }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }
    }
}
