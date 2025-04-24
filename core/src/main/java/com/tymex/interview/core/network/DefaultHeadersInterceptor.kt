package com.tymex.interview.core.network

import com.tymex.interview.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * An OkHttp Interceptor that adds default headers required for API requests.
 */
class DefaultHeadersInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the original request from the chain.
        val originalRequest = chain.request()
        val token = BuildConfig.GITHUB_API_TOKEN

        // Build a new request based on the original one, adding headers.
        val requestBuilder = originalRequest.newBuilder()
            .header("Content-Type", "application/json; charset=utf-8")
            .header("Accept", "application/json")

        if (token.isNotEmpty()) {
            requestBuilder.header("Authorization", "Bearer $token")
        }

        // Build the modified request.
        val newRequest = requestBuilder.build()

        // Proceed with the chain, sending the modified request and returning the response.
        return chain.proceed(newRequest)
    }
}
