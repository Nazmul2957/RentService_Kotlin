package com.example.second_kotlin_project.Mainviewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.second_kotlin_project.Repository.Repository
import com.example.second_kotlin_project.ViewModel.MainViewModel

class MainViewModelFactory(private val respository:Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(respository) as T
    }
}