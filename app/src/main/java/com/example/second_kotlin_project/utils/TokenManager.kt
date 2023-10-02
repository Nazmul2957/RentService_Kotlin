package com.example.second_kotlin_project.utils

import android.content.Context
import com.example.second_kotlin_project.utils.Constant.PREFS_TOKEN_FILE
import com.example.second_kotlin_project.utils.Constant.USER_TOKEN

class TokenManager(context: Context) {

    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun savetoken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun gettoken(): String? {
        return prefs.getString(USER_TOKEN, null)

    }
}