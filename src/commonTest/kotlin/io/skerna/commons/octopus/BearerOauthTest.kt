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

import io.ktor.client.request.HttpRequestBuilder
import io.skerna.commons.octopus.*
import io.skerna.commons.octopus.impl.DefaultApi
import io.skerna.commons.octopus.impl.DefaultClientFactory
import io.skerna.commons.sreaction.asCoroutine
import kotlin.test.Test
import kotlin.test.assertTrue

open class BearerOauthTest:BaseTest() {

    @Test
    fun resolveToken() = runBlocking {
        val apiConfig = ApiConfig()
                .setServerUrlOauth2("192.168.1.33")
                .setServerPortOauth2(80)

        val auth = BearerOauth("069f0Vf1tMwU498Au6WC44RMs83axXC6","P718v53swJa8VjwvvQhaYe8WpJF6H8c1")
        val client = DefaultApi(apiConfig, listOf(auth),DefaultClientFactory(apiConfig))

        client.call(CustomCall {
            println(it)
        }).asCoroutine()


    }
}