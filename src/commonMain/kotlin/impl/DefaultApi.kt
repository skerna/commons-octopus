/*
 * Copyright (c)  2019  SKERNA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.skerna.octopus.impl

import io.ktor.client.call.call
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.port
import io.ktor.http.fullPath
import io.ktor.http.isSuccess
import io.skerna.octopus.*
import io.skerna.futures.Reaction
import io.skerna.futures.asCoroutine
import io.skerna.futures.asReaction
import io.skerna.slog.LoggerFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.io.readRemaining


internal class DefaultApi(private val apiConfig: ApiConfig,
                          private val listPreactions: List<Preaction>,
                          private val defaultClientFactory: ClientFactory) : Api {
    val logger = LoggerFactory.logger<DefaultApi>()

    val client by lazy { defaultClientFactory.create() }

    private fun getTargetHost(requestBuilder: HttpRequestBuilder) {
        var host = apiConfig.serverUrl.removeSuffix("/")
        requestBuilder.url.host = host
        logger.debug(requestBuilder.url.encodedPath)
        logger.debug(requestBuilder.url.buildString())
    }

    /**
     * Ejecuta una petición a la Api usando un adaptador
     */
    @ExperimentalCoroutinesApi
    override fun call(requestBuilder: HttpRequestBuilder): Reaction<String> = GlobalScope.async {
            getTargetHost(requestBuilder)
            val contextRequest = PreactionContext(requestBuilder,apiConfig,client)
            for (configuration in listPreactions) {
                configuration.apply(contextRequest).asCoroutine()
            }
            // Setup global config to request
            requestBuilder.port = apiConfig.serverPort

            val resultCall = client.call(requestBuilder)
            val response = resultCall.response
            val status = response.status
            val packet = response.content.readRemaining(Long.MAX_VALUE)

            val content = packet.readText()
            if (!status.isSuccess()) {
                println("================================")
                println(response.call.request.url.fullPath)
                throw ApiCallException("Call api ended with code ${status.value}, content: ${status.description} ${content}")
            }
        content

        }.asReaction()


    /**
     * Ejecuta una petición usando un adaptador para transformar la respuesta del servidor
     * @param adapter
     * @param requestBuilder
     * @return Reaction<T>
     */
    override fun <T> callWithAdapter(requestBuilder: HttpRequestBuilder, adapter: (String) -> T): Reaction<T> {
        val result = call(requestBuilder)
        return result.map(adapter)
    }

    override fun toString(): String {
        return "DefaultApi(apiConfig=$apiConfig)"
    }

}
