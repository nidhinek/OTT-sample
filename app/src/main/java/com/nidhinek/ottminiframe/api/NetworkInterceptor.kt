package com.nidhinek.ottminiframe.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder: Request.Builder = chain.request().newBuilder()
        requestBuilder.header("x-rapidapi-host", "imdb8.p.rapidapi.com")
        requestBuilder.header(
            "x-rapidapi-key",
            "10493364bfmshd997ee5e5d1f5b2p1644c7jsne9865c69075d"
        )
        return chain.proceed(requestBuilder.build())
    }
}
