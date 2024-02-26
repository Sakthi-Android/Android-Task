package com.example.androidtask.APIService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private
    const val BASE_URL = "https://crudcrud.com/api/dccd6d07ed0a43fab687593a8d3ffe45/"
    val api: RetrofitApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RetrofitApi::class.java)
    }
}