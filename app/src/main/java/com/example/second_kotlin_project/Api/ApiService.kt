package com.example.second_kotlin_project.Api

import com.example.second_kotlin_project.Model.Model.Otp.Otp_Model
import com.example.second_kotlin_project.Model.Model.Register.registration_model
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
    ): Response<Otp_Model>

    @FormUrlEncoded
    @POST("register")
    suspend fun getregistration(
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("otp") otp: String,
    ): Response<registration_model>

}