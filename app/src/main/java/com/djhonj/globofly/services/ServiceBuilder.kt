package com.djhonj.globofly.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    //private const val URL = "http://10.0.2.2:9000"
    private const val URL = "https://jsonplaceholder.typicode.com/"

    //instancia para el interteptor de peticiones y respuestas
    val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // create OkHttp Client
    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)

    // create retrofi builder
    private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //create retrofit instance
    private val retrofit: Retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}