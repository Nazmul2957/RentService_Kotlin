package com.example.second_kotlin_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.second_kotlin_project.Fragment.RegistrationFragment
import com.example.second_kotlin_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainbinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)


        //setContentView(R.layout.activity_main)


        val loginFragment = RegistrationFragment()
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.activity_main, loginFragment).commit()
    }


}