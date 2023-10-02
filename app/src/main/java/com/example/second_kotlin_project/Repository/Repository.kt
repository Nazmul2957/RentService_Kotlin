package com.example.second_kotlin_project.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.second_kotlin_project.Api.RetrofitHelper
import com.example.second_kotlin_project.Model.Model.Login.LoginRequest
import com.example.second_kotlin_project.Model.Model.Login.Login_Response
import com.example.second_kotlin_project.Model.Model.Otp.Otp_Model
import com.example.second_kotlin_project.Model.Model.Register.registration_model
import com.example.second_kotlin_project.utils.NetworkResult
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

    suspend fun getLogin(
        mobile: String,
        password: String
    ): Response<Login_Response> {
        return RetrofitHelper.api.getlogin("88" + mobile, password)
    }


}
