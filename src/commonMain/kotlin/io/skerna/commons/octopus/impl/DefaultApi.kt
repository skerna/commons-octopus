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

package io.skerna.commons.octopus.impl

import io.ktor.client.call.call
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.port
import io.ktor.http.fullPath
import io.ktor.http.isSuccess
import io.skerna.commons.sreaction.Reaction
import io.skerna.commons.sreaction.asCoroutine
import io.skerna.commons.sreaction.asReaction
import io.skerna.commons.logger.LoggerFactory
import io.skerna.commons.octopus.*
import io.skerna.commons.octopus.handlers.CallHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.io.readRemaining


open class DefaultApi(private val apiConfig: ApiConfig,
                          private val listPreactions: List<Preaction>,
                          private val defaultClientFactory: ClientFactory) : Api {
    val logger = LoggerFactory.logger<DefaultApi>()
    val client by lazy { defaultClientFactory.create() }
    val handlers:MutableSet<CallHandler> by lazy { mutableSetOf<CallHandler>() }

    private fun getTargetHost(requestBuilder: HttpRequestBuilder) {
        var host = apiConfig.serverUrl.removeSuffix("/")
        requestBuilder.url.host = host
        logger.debug(requestBuilder.url.encodedPath)
        logger.debug(requestBuilder.url.buildString())
    }

    /**
     * Apply request builder
     * @param requestBuilder
     * @return HttpRequestBuilder
     */
    open suspend fun applyPreactions(requestBuilder: HttpRequestBuilder):HttpRequestBuilder{
        val contextRequest = PreactionContext(requestBuilder,apiConfig,client)
        println("Applicando total de preactions " + listPreactions.size)
        for (configuration in listPreactions) {
            try {
                configuration.apply(contextRequest)
            }
            catch(e:Exception) {
                logger.error("Error on apply preaction",e)
                throw ApiCallException("Preaction application end with error code",e)
            }
        }
        return requestBuilder
    }

    open fun buildBaseRequest():HttpRequestBuilder{
        val request  = HttpRequestBuilder()
        return request
    }



    /**
     * Ejecuta una petición a la Api usando un adaptador
     */
    @ExperimentalCoroutinesApi
    override suspend fun call(requestBuilder: HttpRequestBuilder): String {
        try {
            notifyCallStart()
            getTargetHost(requestBuilder)
            applyPreactions(requestBuilder)
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
            notifCallSucceeded()
            return content
        }catch (ex:Exception){
            val apiCallException = ApiCallException(ex)
            notifyCallFail(ex)
            throw ex
        }
    }


    override suspend fun <T> call(customCaller: CustomCall<T>): T{
        try {
            notifyCallStart()
            val baserequest = buildBaseRequest()
            applyPreactions(baserequest)
            val result = customCaller.call(baserequest)
            notifCallSucceeded()
            return result
        }catch (ex:Exception){
            val apiCallException = ApiCallException(ex)
            notifyCallFail(ex)
            throw apiCallException
        }

    }


    private fun notifyCallStart(){
        for (handler in handlers) {
            handler.onCallStarted()
        }
    }

    private fun notifCallSucceeded(){
        for (handler in handlers) {
            handler.onCallSucceeded()
        }
    }
    private fun notifyCallFail(throwable: Throwable){
        for (handler in handlers) {
            handler.onCallException(throwable)
        }
    }


    override fun getApiConfig(): ApiConfig {
        return apiConfig
    }

    override fun addCallHandler(handler: CallHandler) {
        handlers.add(handler)
    }

    override fun addCallHandlers(set: Set<CallHandler>) {
        this.handlers.addAll(set)
    }

    override fun removeCallHandler(handler: CallHandler) {
        handlers.remove(handler)
    }

    override fun getHandlers(): Set<CallHandler> {
        return handlers
    }

    override fun toString(): String {
        return "DefaultApi(apiConfig=$apiConfig)"
    }

}
