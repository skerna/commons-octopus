package io.skerna.commons.octopus.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.skerna.commons.octopus.ApiConfig
import io.skerna.commons.octopus.ClientFactory

internal actual class DefaultClientFactory actual constructor(apiConfig: ApiConfig) : ClientFactory {
    /**
     * Create new HttpClient in specific platform
     * @return HttpClient
     */
    actual override fun create(): HttpClient {
        return HttpClient(Android) {
            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
            }
        }
    }
}