package com.example.second_kotlin_project.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import com.example.second_kotlin_project.R
import com.example.second_kotlin_project.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    var _loginfragment: FragmentLoginBinding? = null
    val loginbinding get() = _loginfragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _loginfragment = FragmentLoginBinding.inflate(inflater, container, false)
        // inflater.inflate(R.layout.fragment_login, container, false)

        loginbinding?.signUp?.setOnClickListener {
            val fragment = RegistrationFragment()

            fragmentManager?.beginTransaction()?.replace(R.id.activity_main, fragment)?.commit()
        }

        loginbinding?.signIn?.setOnClickListener {
            val fragment = SignInFragment()

            fragmentManager?.beginTransaction()?.replace(R.id.activity_main, fragment)?.commit()
        }
        //  setUpOnbackPressed()
        return loginbinding?.root
    }


}


