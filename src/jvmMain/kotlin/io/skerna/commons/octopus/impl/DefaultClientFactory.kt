package io.skerna.commons.octopus.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.skerna.commons.octopus.ApiConfig
import io.skerna.commons.octopus.ClientFactory
import okhttp3.ConnectionSpec
import java.util.concurrent.TimeUnit

internal actual class DefaultClientFactory actual constructor(apiConfig: ApiConfig): ClientFactory {
    /**
     * Create new HttpClient in specific platform
     * @return HttpClient
     */
    actual override fun create(): HttpClient {
        val connectionSpec = ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT)
                .build()

        return HttpClient(engine = OkHttp.create {
            config {
                followRedirects(true)
                connectionSpecs(listOf(connectionSpec))
                cache(null)
                connectTimeout(15, TimeUnit.SECONDS)
                readTimeout(5, TimeUnit.SECONDS)
            }

            addNetworkInterceptor(LocalInterceptor())
        })
    }
}