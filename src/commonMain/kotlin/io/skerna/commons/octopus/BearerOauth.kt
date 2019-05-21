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

package io.skerna.commons.octopus

import io.ktor.client.call.call
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.isSuccess
import io.skerna.commons.logger.LoggerFactory
import io.skerna.commons.octopus.representations.OAuth2Token
import io.skerna.commons.octopus.utils.Base64
import io.skerna.commons.octopus.utils.Dates
import kotlinx.coroutines.io.readRemaining
import kotlinx.serialization.json.JSON

class BearerOauth(val tokenEncoded: String) : Preaction {
    private val log = LoggerFactory.logger<BearerOauth>()
    private var currentToken: OAuth2Token? = null
    private var currentTokenExpireTime: Long = 0

    constructor(clientId: String, clientSecret: String) : this(Base64.encode("$clientId:$clientSecret"))


    /**
     * Aplica una configuracion al builder de la solicitud antes de realizar una petición
     * a los servidores
     * @param httpRequestBuilder
     */
    override suspend fun apply(context: PreactionContext) {
        log.debug("Aplicando request interceptor ${this::class.simpleName}")
        log.debug("Current Token is $currentToken")
        val token = resolveToken(context)
        bindTokenToRequest(context.request, token)
    }

    /**
     * Añade el token a las cabeceras de la petción
     * @param requestBuilder
     */
    private fun bindTokenToRequest(requestBuilder: HttpRequestBuilder, token: OAuth2Token) {
        log.debug("Bind token to request $token")
        if (this.currentToken == null) {
            throw IllegalStateException("Token no resuelto")
        }
        val accessToken = token.access_token
        requestBuilder.headers
                .append("Authorization", "Bearer $accessToken")
    }

    /**
     * Metodo resuelve un token, si no lo encuentra localmente o el token actual ha expirado
     * trata de obtener un nuevo token desde los servidores de autorización
     * @param
     */
    private suspend fun resolveToken(context: PreactionContext): OAuth2Token {
        return if (currentTokenIsInValid()) {
            log.debug("Current token es invalido")
            val tokenOperation = resolveRemoteToken(context)
            this.currentToken = tokenOperation
            this.currentTokenExpireTime = Dates.getExpirationTimeEpoch(this.currentToken!!)
            return this.currentToken!!
        } else {
            log.debug("Return current token from local memory")
            // check if it is expired
            this.currentToken!!
        }
    }

    private suspend fun resolveRemoteToken(context: PreactionContext): OAuth2Token {
        log.debug("Resolve remote token")
        val apiConfig = context.apiConfig
        val httpClient = context.httpClient

        val request = HttpRequestBuilder()
        val oauthPaht = "auth/realms/${apiConfig.contextAuth}/protocol/openid-connect/token"
        request.url {
            host = apiConfig.serverUrlOauth
            port = apiConfig.serverPortOauth
            path(oauthPaht)
        }

        log.debug("Request token to ${apiConfig.contextAuth} using server ${apiConfig.serverUrlOauth}:${apiConfig.serverPortOauth} path => $oauthPaht")
        request.method = HttpMethod.Post
        request.body = FormDataContent(Parameters.build {
            append("grant_type", "client_credentials")
        })
        request.headers.append("Authorization", "Basic $tokenEncoded")

        val resultCall = httpClient.call(request)
        val response = resultCall.response
        val status = response.status
        val packet = response.content.readRemaining(Long.MAX_VALUE)
        val content = packet.readText()
        log.debug("Content response request token $content")
        log.debug("Status response request token $status")

        if (!status.isSuccess()) {
            val message = "Server response with not success code $status, content [$content]"
            log.error(message)
            throw ApiCallException(message, request, response)
        }
        return JSON.parse(OAuth2Token.serializer(), content)
    }

    private fun currentTokenIsInValid(): Boolean {
        return this.currentToken == null || currentTokenIsExpired()
    }

    private fun currentTokenIsExpired(): Boolean {
        if (this.currentToken == null) {
            return true
        }
        val actualTime = Dates.getCurrentTimeEpoch()
        return actualTime >= currentTokenExpireTime
    }

    /**
     * Retorna el token actual
     * @return OAuth2Token
     */
    fun getCurrentToken(): OAuth2Token? {
        return this.currentToken
    }


}