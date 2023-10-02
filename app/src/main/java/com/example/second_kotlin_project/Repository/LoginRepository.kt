package com.example.second_kotlin_project.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.second_kotlin_project.Api.ApiService
import com.example.second_kotlin_project.Model.Model.Login.LoginRequest
import com.example.second_kotlin_project.Model.Model.Login.Login_Response
import com.example.second_kotlin_project.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(val apiservice: ApiService) {

    val _loginrepsonslivedata = MutableLiveData<NetworkResult<Login_Response>>()
    val loginresponselivedata: LiveData<NetworkResult<Login_Response>>
        get() = _loginrepsonslivedata

    suspend fun loginUser(loginrequest: LoginRequest) {
        _loginrepsonslivedata.postValue(NetworkResult.Loading())
        val response = apiservice.signin(loginrequest)
        handleResponse(response)

    }

    private fun handleResponse(response: Response<Login_Response>) {
        if (response.isSuccessful && response.body() != null) {
            _loginrepsonslivedata.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _loginrepsonslivedata.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _loginrepsonslivedata.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

}