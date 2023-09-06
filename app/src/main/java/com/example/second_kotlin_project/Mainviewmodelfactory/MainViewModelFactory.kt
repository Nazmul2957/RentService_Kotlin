package com.example.second_kotlin_project.Mainviewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.second_kotlin_project.Repository.Registration_Repository
import com.example.second_kotlin_project.ViewModel.MainViewModel

class MainViewModelFactory(private val respository:Registration_Repository) :
    ViewModelProvider.AndroidViewModelFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(respository) as T
    }
}