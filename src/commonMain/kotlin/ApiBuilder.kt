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

package io.skerna.octopus

import kotlin.js.JsName


interface ApiBuilder{

    @JsName("withLogginEnabled")
    fun withLogginEnabled(boolean: Boolean):ApiBuilder

    @JsName("withHost")
    fun withHost(server:String):ApiBuilder

    @JsName("withPort")
    fun withPort(port:Int):ApiBuilder

    @JsName("withConfig")
    fun withConfig(config: ApiConfig):ApiBuilder

    @JsName("withToken")
    fun withToken(token:String):ApiBuilder

    @JsName("withPreaction")
    fun withPreaction(preaction: Preaction):ApiBuilder

    @JsName("withClientFactory")
    fun withClientFactory(clientFactory: ClientFactory):ApiBuilder

    @JsName("withContextOauth")
    fun withContextOauth(contextOauth: String):ApiBuilder

    @JsName("withServerUrlOauth2")
    fun withServerUrlOauth2(server:String):ApiBuilder

    @JsName("withServerPortOauth2")
    fun withServerPortOauth2(port:Int):ApiBuilder

    @JsName("build")
    fun build():Api
}