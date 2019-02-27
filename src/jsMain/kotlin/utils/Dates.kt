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

package io.skerna.octopus.utils

import io.skerna.octopus.representations.OAuth2Token
import kotlin.js.Date

const val MILLIS = 10000

actual object Dates {

    actual fun getExpirationTimeEpoch(token: OAuth2Token): Long {
        val date = Date()
        val millisDate = date.getTime().toLong()
        val millisToken = token.expires_in.toMillis()

        return millisToken + millisDate

    }

    /**
     * get Current Time Epoch from system
     * @return Long
     */
    actual fun getCurrentTimeEpoch(): Long {
        return Date().getTime().toLong()
    }

    private fun Long.toMillis(): Long {
        return this* MILLIS
    }
}