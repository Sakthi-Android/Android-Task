package com.example.androidtask.APIService

import com.example.androidtask.model.Profile
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @POST("users")
    suspend fun postData(@Body dataModel: Profile?): Profile

    @GET("users")
    suspend fun getAllData() : List<Profile>
}

