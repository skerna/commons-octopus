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

import io.skerna.commons.octopus.*
import io.skerna.commons.octopus.impl.DefaultApi
import io.skerna.commons.octopus.impl.DefaultClientFactory

class DefaultApiBuilder(): ApiBuilder {

    private var listPreactions = mutableListOf<Preaction>()
    private var apiConfig: ApiConfig = ApiConfig()
    private var clientFactory: ClientFactory?=null

    override fun withLogginEnabled(boolean: Boolean): ApiBuilder {
        apiConfig.enableLoggin = boolean
        return this
    }

    override fun withHost(server: String): ApiBuilder {
        apiConfig.serverUrl = server
        return this
    }

    override fun withPort(port: Int): ApiBuilder {
        apiConfig.serverPort =  port
        return this
    }

    override fun withConfig(config: ApiConfig): ApiBuilder {
        this.apiConfig = apiConfig
        return this
    }

    override fun withToken(token: String): ApiBuilder {
        listPreactions.add(BearerAuth(token))
        return this
    }

    override fun withPreaction(preaction: Preaction): ApiBuilder {
        listPreactions.add(preaction)
        return this
    }

    override fun withClientFactory(clientFactory: ClientFactory): ApiBuilder {
        this.clientFactory = clientFactory
        return this
    }

    override fun withContextOauth(contextOauth: String): ApiBuilder {
        this.apiConfig.contextAuth = contextOauth
        return this
    }

    override fun withServerUrlOauth2(server: String): ApiBuilder {
        this.apiConfig.setServerUrlOauth2(server)
        return this
    }

    override fun withServerPortOauth2(port: Int): ApiBuilder {
        this.apiConfig.setServerPortOauth2(port)
        return this
    }

    override fun build(): Api {
        if(clientFactory == null){
            clientFactory = DefaultClientFactory(apiConfig)
        }

        return DefaultApi(apiConfig,listPreactions, clientFactory!!)
    }

}