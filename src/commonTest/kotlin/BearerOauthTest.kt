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
import io.skerna.octopus.BearerOauth
import io.skerna.futures.asCoroutine
import kotlin.test.Test
import kotlin.test.assertTrue

open class BearerOauthTest:BaseTest() {

    @Test
    fun resolveToken() = runBlocking {
/**
        val preaction = BearerOauth("it3AC9dpU1btYrFa6kR6E2tch8224UwL","73K59up7996HKL4JFXrxY97xJL7paktx")
        val httpRequestBuilder = HttpRequestBuilder()
        val react = preaction.apply(httpRequestBuilder).asCoroutine()
        println("LLegue aqui")
        assertTrue(react,"Se esperaba una respuesta ok")**/
    }
}