package com.example.button_test

import com.example.button_test.service.Cost_Service
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object costInfo{
    val okHttpClient= OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    var gson= GsonBuilder().setLenient().create()

    val retrofit= Retrofit.Builder()
        .baseUrl("https://c0c3-221-149-4-114.ngrok-free.app/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    val cost_Service: Cost_Service by lazy{retrofit.create(Cost_Service::class.java)}
}