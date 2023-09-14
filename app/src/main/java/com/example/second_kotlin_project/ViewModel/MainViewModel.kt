package com.example.second_kotlin_project.ViewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.second_kotlin_project.Model.Model.Otp.Otp_Model
import com.example.second_kotlin_project.Model.Model.Register.registration_model
import com.example.second_kotlin_project.Repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(val repository: Repository) : ViewModel() {


//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getotps("8801629668325")
//        }
//
//    }
//
//    val otp:LiveData<Otp_Model>
//    get() = repository.livedata

    val otpresponse: MutableLiveData<Response<Otp_Model>> = MutableLiveData()

    val registrationrespons: MutableLiveData<Response<registration_model>> = MutableLiveData()


    fun GetOtp(mobile: String) {
        viewModelScope.launch {
            val response = repository.getOtp(mobile)
            otpresponse.value = response
            Log.d("otp_send_to_mobile", response.toString())
        }
    }

    @SuppressLint("LongLogTag")
    fun getregistration(
        name: String,
        mobiles: String,
        password: String,
        address: String,
        otpnum: String
    ) {
        viewModelScope.launch {
            val response = repository.getregistration(name, mobiles, password, address, otpnum)
            registrationrespons.value = response
            Log.d("registration_successfully", response.toString())
        }
    }

}