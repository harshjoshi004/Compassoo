package com.molog.compassoo_project

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://vkvp4qqj-4000.inc1.devtunnels.ms/"
    //"https://r80q8w1t-4000.inc1.devtunnels.ms/"
    //"https://vkvp4qqj-4000.inc1.devtunnels.ms/"

    //200: successful
    //401: user already exist
    //500: internal server error
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}