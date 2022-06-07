package com.example.notbored.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    val baseUrl= "http://www.boredapi.com/api/"

    val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> getAPI(API: Class<T>): T {
        return retrofit.create(API)
    }

}



