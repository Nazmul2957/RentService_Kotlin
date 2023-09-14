package com.example.second_kotlin_project.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.second_kotlin_project.Mainviewmodelfactory.MainViewModelFactory
import com.example.second_kotlin_project.R
import com.example.second_kotlin_project.Repository.Repository
import com.example.second_kotlin_project.ViewModel.MainViewModel
import com.example.second_kotlin_project.databinding.FragmentOtpBinding


class OtpFragment : Fragment(), View.OnFocusChangeListener {

    var _otpbinding: FragmentOtpBinding? = null
    val otpbinding get() = _otpbinding
    var isErrorEnabled = false
    var result: String = null.toString()

    lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _otpbinding = FragmentOtpBinding.inflate(inflater, container, false);
        val repository = Repository()
        viewmodel =
            ViewModelProvider(
                requireActivity(),
                MainViewModelFactory(repository)
            ).get(MainViewModel::class.java)
        // val v = inflater.inflate(R.layout.fragment_otp, container, false)
        val args = this.arguments
        val get_name = args?.get("Name")
        val get_address = args?.get("Address")
        val get_phonenum = args?.get("Phone")
        val get_password = args?.get("Password")
        Log.d("show_name", get_name.toString())

        otpbinding?.verifyOtp?.setOnClickListener {
            viewmodel.getregistration(
                get_name.toString(), get_phonenum.toString(), get_password.toString(),
                get_address.toString(), otpbinding?.pinView?.text.toString()
            )
            viewmodel.registrationrespons.observe(requireActivity(), Observer { response ->
                result = response.toString()
            })

        }


        return otpbinding?.root
    }

    private fun valiOtp(): Boolean {
        var errorMessage: String? = null
        val value: String = otpbinding?.pinView?.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Input Valid OTP Number"
        }
        if (errorMessage != null) {
            otpbinding?.pinviewtil.apply {
                isErrorEnabled = true
                error(errorMessage)
            }
        }

        return errorMessage == null
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        TODO("Not yet implemented")
        if (view != null) {
            when (view.id) {
                R.id.pin_view -> {
                    if (hasFocus) {
                        otpbinding?.verifyOtp?.isClickable = false

                    } else {
                        valiOtp()
                    }
                }


            }
        }
    }


}