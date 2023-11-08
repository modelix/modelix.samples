package org.acme.getting.started.testing

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.TestProfile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.modelix.model.client.ReplicatedRepository
import javax.inject.Inject

class UnreachableUrlProfile : QuarkusTestProfile {
    override fun getConfigOverrides(): Map<String, String> {
        // Override the server uri to be an unreachable URL.
        return mapOf("modelix.client.server-uri" to "http://non_existing_host:93939")
    }
}

@QuarkusTest
@TestProfile(value = UnreachableUrlProfile::class)
class ReplicatedRepositoryInstantiationTest {
    @Inject
    lateinit var replicatedRepository: ReplicatedRepository

    @Test
    fun testInjection() {
        // This tests, whether `replicatedRepository` can be correctly injected and instantiated by Quarkus.
        // The actual instantiation is happening when we call something on `replicatedRepository`.
        // Therefore, we call `.branch`.
        // The asserted exception message indicates that the `replicatedRepository` was injected correctly.
        // The exception message "java.lang.NoClassDefFoundError: kotlin/enums/EnumEntriesKt", for example,
        // would indicate that something is set up wrong and the instantiation is failing.
        // In the past, we had such issues with the wrong version of Kotlin and Ktor.
        val exception = assertThrows(RuntimeException::class.java) { replicatedRepository.branch }
        assertEquals(
            "Unable to connect to 'http://non_existing_host:93939/get/branch_courses_master' to get key branch_courses_master",
            exception.message
        )
    }
}