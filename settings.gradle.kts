rootProject.name = "modelix-samples"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://artifacts.itemis.cloud/repository/maven-mps/") }
        mavenLocal()
    }
}

//
// MPS related sub-projects
//
// extracts the meta-model from the mps languages into the metamodel sub-project
include("mps")
// provides the meta-model a sub-project for the APIs as an implementation dependency
include("mps:metamodel-api-kts")
include("mps:metamodel-api-ts")
// MPS project which uses the model-server as a storage backend
include("mps:project-modelserver-backend")
// MPS project which uses classic MPS XMLs a storage backend
include("mps:project-mps-backend")

//
// modelix
//
// sub-project which provides an easy way to start a localized model-server
include("model-server")

//
// APIs
//
//  API using ktor and a light model-server client (light)
include("rest-api-model-ql")
// API using quarkus and a direct model-server connection (advanced)
include("rest-api-model-server")

//
// Web client
//
// a single page application which can use any of the APIs
include("spa-dashboard-angular")

// a single page application which can write on the model through a model client
include("spa-management-vue")