package com.nidhinek.ottminiframe.api

import com.nidhinek.ottminiframe.data.ImdbSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdbService {
    @GET("/auto-complete")
    suspend fun searchMovies(
        @Query("q") query: String
    ): ImdbSearchResponse
    companion object {
        private const val BASE_URL = "https://imdb8.p.rapidapi.com"

        fun create(): ImdbService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(NetworkInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImdbService::class.java)
        }
    }
}
