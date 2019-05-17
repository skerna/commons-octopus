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

import io.ktor.client.request.HttpRequestBuilder
import io.skerna.commons.octopus.handlers.CallHandler
import io.skerna.commons.sreaction.Reaction
import io.skerna.commons.octopus.impl.DefaultApiBuilder
import kotlin.js.JsName


interface Api{
    /**
     * Ejecuta una petición a la Api usando un adaptador
     */
    @JsName("call")
    suspend fun call(requestBuilder: HttpRequestBuilder): String

    @JsName("callWithRequestAdapter")
    suspend fun<T> call(customCaller:CustomCall<T>): T

    fun getApiConfig():ApiConfig

    fun addCallHandler(handler:CallHandler)

    fun addCallHandlers(set: Set<CallHandler>)

    fun removeCallHandler(handler: CallHandler)

    fun getHandlers():Set<CallHandler>


    companion object {
        @JsName("create")
        fun create(): ApiBuilder {
            return DefaultApiBuilder()
        }
    }

}