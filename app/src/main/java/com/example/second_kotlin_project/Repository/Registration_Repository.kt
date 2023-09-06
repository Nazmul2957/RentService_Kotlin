package com.example.second_kotlin_project.Repository
import com.example.second_kotlin_project.Api.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response


class Registration_Repository() {

    suspend fun getOtp(mobile: String): Response<JsonObject> {
        return RetrofitHelper.api.getotp("88"+mobile)
    }
}
