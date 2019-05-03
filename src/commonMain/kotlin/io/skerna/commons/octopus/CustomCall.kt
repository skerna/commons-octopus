package io.skerna.commons.octopus

import io.ktor.client.request.HttpRequestBuilder

/**
 * @author Ronald Cárdenas
 **/
interface CustomCall<T>{
    fun call(requestBuilder: HttpRequestBuilder):T
}

/**
 * Creates [Runnable] task instance.
 */
@Suppress("FunctionName")
public fun<T> CustomCall( block: (requestBuilder: HttpRequestBuilder) -> T): CustomCall<T>{
    return object:CustomCall<T>{
        override fun call(requestBuilder: HttpRequestBuilder): T {
            return block(requestBuilder)
        }
    }
}