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

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.skerna.octopus.ApiConfig
import io.skerna.octopus.ClientFactory
import okhttp3.ConnectionSpec
import java.util.concurrent.TimeUnit

internal actual class DefaultClientFactory actual constructor(apiConfig: ApiConfig):ClientFactory {
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
                connectTimeout(15,TimeUnit.SECONDS)
                readTimeout(5,TimeUnit.SECONDS)
            }

            addNetworkInterceptor(LocalInterceptor())
        })
    }
}