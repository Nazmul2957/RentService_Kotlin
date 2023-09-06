package com.example.second_kotlin_project.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val Base_Url = "http://test.codeminent.com/public/api/"


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
//
//    fun getInstance(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Base_Url)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}