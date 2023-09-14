package com.example.second_kotlin_project.Model.Model.Otp

data class SmsApi(
    val cost: Double,
    val error_code: Int,
    val ids: List<Id>,
    val message: String,
    val sms_count: Int,
    val status: Boolean
)