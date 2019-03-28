package io.skerna.commons.octopus.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.skerna.commons.octopus.ApiConfig
import io.skerna.commons.octopus.ClientFactory

internal actual class DefaultClientFactory actual constructor(apiConfig: ApiConfig) : ClientFactory {
    /**
     * Create new HttpClient in specific platform
     * @return HttpClient
     */
    actual override fun create(): HttpClient {
        return HttpClient(engine = Js.create {
        })
    }
}