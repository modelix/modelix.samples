package org.modelix.sample.restapimodelserver

import University.Schedule.GeneratedLanguages
import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import org.modelix.model.api.INodeReference
import org.modelix.model.api.INodeReferenceSerializer
import org.modelix.model.api.PNodeReference
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

/**
 * This class configures access to models using the api-gen generated classes. The required configuration is part
 * of the Quarkus application initialization using Quarkus event listeners.
 */
@ApplicationScoped
class LanguageConfig {

    fun configure(@Observes event: StartupEvent) {
        GeneratedLanguages.registerAll()
    }
}
