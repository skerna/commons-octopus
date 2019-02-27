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

package io.skerna.octopus.representations

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsName

@JsName("OAuth2Token")
@Serializable
data class OAuth2Token(
        val access_token: String="", // eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJVcFg4NEt5M2pwbFNfN1REckhkZmlCbVhUdHZkNmVRQUdhMlcwRTc3Uk00In0.eyJqdGkiOiI3MmZmYmM0My0wMTk5LTQwY2UtOWIyOC1iMzE5MmI2NzM4MWUiLCJleHAiOjE1NDk2NjIxOTIsIm5iZiI6MCwiaWF0IjoxNTQ3MDcwMTkyLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0L2F1dGgvcmVhbG1zL3IyYiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI4OGE2MjU0Yy1jNGVkLTQ4ZmItODZhYy0wNzY5YzY4YmJhNzgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJBZjVwUU0ycHZVUkwyNlgyWFdFS0U4TjdXcDZiVlE0NCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6ImQxZDYzOTM1LTQ5OTYtNDQxMC04OTVkLWU5ZjFlYjA0ZjEyNiIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiY2xpZW50SG9zdCI6IjE3Mi4xOS4wLjQiLCJjbGllbnRJZCI6IkFmNXBRTTJwdlVSTDI2WDJYV0VLRThON1dwNmJWUTQ0IiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LWFmNXBxbTJwdnVybDI2eDJ4d2VrZThuN3dwNmJ2cTQ0IiwiY2xpZW50QWRkcmVzcyI6IjE3Mi4xOS4wLjQiLCJlbWFpbCI6InNlcnZpY2UtYWNjb3VudC1hZjVwcW0ycHZ1cmwyNngyeHdla2U4bjd3cDZidnE0NEBwbGFjZWhvbGRlci5vcmcifQ.K0Y8JYMSwN1ai8YZ9ORGFOlYJf9B-f23bFLChbyKqk-dBiqwEvXSpy1lS6caTfPzhvkpq37wHJQVaot-zVF75gi3W2narcPFBH7AXMda-p5wt1es-XwA0sZtiSqA5CbEy3FWgSy0ing-D4Zn62Jc-TAUFF6bm2txwIlRo_kZ6UTxxeIAuRS-WIr5UewGCKdVfK2VOyyntyAyRjbSPvWKd5M34Cun12rW8lXLwgyNOLXSUczOjHc_Q9k2Y2tc5z1xFqPM9cGLWCYEiAO04nt011rD8e_395zXtNtT8ChDt5j9B3P9fkb-Ic5sWTRXJTlRg8B8qpeivjYs10PficsL1w
        val expires_in: Long=0, // 2592000
        @Optional
        @SerialName("not-before-policy")
        val not_before_policy: Int=0, // 0
        val refresh_expires_in: Int=0, // 86400
        val refresh_token: String="", // eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJjZTYwMjU4NS00ZmE0LTQyZTctYTE2Ni05NDU4YWRhNDIyZDAifQ.eyJqdGkiOiJkYzViZDNiZC0xZDEyLTRkY2YtOGFjNy1jOThhMjFjNjQ4NTAiLCJleHAiOjE1NDcxNTY1OTIsIm5iZiI6MCwiaWF0IjoxNTQ3MDcwMTkyLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0L2F1dGgvcmVhbG1zL3IyYiIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3QvYXV0aC9yZWFsbXMvcjJiIiwic3ViIjoiODhhNjI1NGMtYzRlZC00OGZiLTg2YWMtMDc2OWM2OGJiYTc4IiwidHlwIjoiUmVmcmVzaCIsImF6cCI6IkFmNXBRTTJwdlVSTDI2WDJYV0VLRThON1dwNmJWUTQ0IiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZDFkNjM5MzUtNDk5Ni00NDEwLTg5NWQtZTlmMWViMDRmMTI2IiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUifQ.CLyGoGp203kmai4hVKc_VzYSgc9rcREHwBnKSZki7y0
        val scope: String="", // email profile
        val session_state: String="", // d1d63935-4996-4410-895d-e9f1eb04f126
        val token_type: String="" // bearer
)