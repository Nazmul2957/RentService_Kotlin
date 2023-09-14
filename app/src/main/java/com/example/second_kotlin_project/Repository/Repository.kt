package com.example.second_kotlin_project.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.second_kotlin_project.Api.ApiService
import com.example.second_kotlin_project.Api.RetrofitHelper
import com.example.second_kotlin_project.Model.Model.Otp.Otp_Model
import com.example.second_kotlin_project.Model.Model.Register.registration_model
import com.google.gson.JsonObject
import retrofit2.Response


class Repository() {

    suspend fun getOtp(mobile: String): Response<Otp_Model> {
        return RetrofitHelper.api.getotp("88" + mobile)
    }

    suspend fun getregistration(
        name: String,
        mobiles: String,
        password: String,
        address: String,
        otpnum: String
    ): Response<registration_model> {
        return RetrofitHelper.api.getregistration(name, "88" + mobiles, password, address, otpnum)
    }

//    val otplivedata = MutableLiveData<Otp_Model>()
//
//    val livedata: LiveData<Otp_Model>
//        get() = otplivedata

//    suspend fun getotps(mobile: String) {
//        val result = apiservie.getotp(mobile)
//        if (result?.body() != null) {
//            otplivedata.postValue(result.body())
//        }
//    }
}
