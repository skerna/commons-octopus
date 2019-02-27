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

class ApiConfig{
    var enableLoggin = false
    var serverPort:Int = 8080
    var serverUrl:String = "http://localhost"
    var contextAuth:String = "r2b"
    var serverUrlOauth:String = serverUrl
    var serverPortOauth:Int = serverPort
    /**
     * Set Enable Loggin
     * @param boolean
     * @return ApiConfig
     */
    @JsName("setEnableLoggin")
    fun setEnableLoggin(boolean: Boolean) = apply { this.enableLoggin = boolean }

    /**
     * Set Server port
     * @param port
     * @return ApiConfig
     */
    @JsName("setServerPort")
    fun setServerPort(port:Int) = apply { this.serverPort = port }

    /**
     * Set Url Server
     * @param url
     * @return ApiConfig
     */
    @JsName("setServerUrl")
    fun setServerUrl(url:String) = apply { this.serverUrl = url }

    /**
     * Setea el context de autenticación
     * @param contextAuth
     */
    @JsName("setContextId")
    fun setContextId(contextAuth:String) = apply { this.contextAuth = contextAuth }

    /**
     * setServerUrlOauth, set server URL OAUTH2
     */
    @JsName("setServerUrlOauth")
    fun setServerUrlOauth2(server:String) = apply { this.serverUrlOauth = server }


    @JsName("setServerPortOauth2")
    fun setServerPortOauth2(port:Int) = apply {
        this.serverPortOauth = port
    }


}