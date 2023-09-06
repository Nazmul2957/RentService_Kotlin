package com.example.second_kotlin_project.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.second_kotlin_project.Repository.Registration_Repository
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel( val repository: Registration_Repository) : ViewModel() {

    val otpresponse: MutableLiveData<Response<JsonObject>> = MutableLiveData()


    fun GetOtp(mobile: String) {
        viewModelScope.launch {
            val response = repository.getOtp("88" + mobile)
            otpresponse.value = response
        }
    }


}