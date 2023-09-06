package com.example.second_kotlin_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.second_kotlin_project.Fragment.RegistrationFragment
import com.example.second_kotlin_project.Fragment.TestkFragment
import com.example.second_kotlin_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)

        //setContentView(R.layout.activity_main)

//        mainbinding.signUp.setOnClickListener {
//            Toast.makeText(this, "button click main activity", Toast.LENGTH_LONG).show()
//        }

//        val loginFragment = TestkFragment()
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val fragmentTransition: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransition.replace(R.id.fragmentcon, loginFragment).commit()

        val loginFragment = RegistrationFragment()
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.activity_main, loginFragment).commit()
    }


}