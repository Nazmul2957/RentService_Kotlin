package com.example.second_kotlin_project.Api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("otp")
    suspend fun getotp(
        @Field("mobile") mobile: String
    ): Response<JsonObject>
}