package com.example.second_kotlin_project.Fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.second_kotlin_project.Mainviewmodelfactory.MainViewModelFactory
import com.example.second_kotlin_project.R
import com.example.second_kotlin_project.Repository.Repository
import com.example.second_kotlin_project.ViewModel.MainViewModel
import com.example.second_kotlin_project.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(), View.OnFocusChangeListener {

    var _binding: FragmentRegistrationBinding? = null
    val binding get() = _binding!!

    lateinit var viewmodel: MainViewModel
     var result: String = null.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)


        val repository = Repository()
        viewmodel =
            ViewModelProvider(
                requireActivity(),
                MainViewModelFactory(repository)
            ).get(MainViewModel::class.java)

        binding.tookName.onFocusChangeListener = this
        binding.regAddress.onFocusChangeListener = this
        binding.phnNo.onFocusChangeListener = this
        binding.tookPassword.onFocusChangeListener = this
        binding.tookConfirmPassword.onFocusChangeListener = this

        binding.signUp.setOnClickListener {
            viewmodel.GetOtp(binding.phnNo.text.toString())
            val name = binding.tookName.text.toString()
            val address = binding.regAddress.text.toString()
            val password = binding.tookPassword.text.toString()
            val phone = binding.phnNo.text.toString()
            val bundle = Bundle()
            bundle.putString("Name", name)
            bundle.putString("Address", address)
            bundle.putString("Password", password)
            bundle.putString("Phone", phone)

            val fragment = OtpFragment()
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.activity_main, fragment)?.commit()


            viewmodel.otpresponse.observe(requireActivity(), Observer { response ->
                result = response.toString()
            })

        }
       // setUpOnbackPressed()

        return binding.root
    }

    private fun valiDateFulLName(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.tookName.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Full Name is required"
        }
        if (errorMessage != null) {
            binding.tookNameTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun valiPhoneNumber(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.phnNo.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Input Valid Phone Number"
        } else if (value.length < 11) {
            errorMessage = "Input 11 digit Phone Number"
        }
        if (errorMessage != null) {
            binding.phoneTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validAddress(): Boolean {
        var errorMessage: String? = null
        val value = binding.regAddress.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Address is required"
        }
        if (errorMessage != null) {
            binding.addressTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validPassword(): Boolean {
        var errorMessage: String? = null
        val value = binding.tookPassword.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password is required"
        } else if (value.length < 6) {
            errorMessage = "Passwrod must be 6 characters long"
        }
        if (errorMessage != null) {
            binding.tookPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val value = binding.tookConfirmPassword.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Confirm Password is required"
        } else if (value.length < 6) {
            errorMessage = "Confirm Passwrod must be 6 characters long"
        }

        if (errorMessage != null) {
            binding.tookConfirmPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun ValidedpasswordandConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val password = binding.tookPassword.text.toString()
        val confrimpassword = binding.tookConfirmPassword.text.toString()
        if (password != confrimpassword) {
            errorMessage = "Confirm Password doesn't match with password"
        }
        if (errorMessage != null) {
            binding.tookConfirmPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null

    }

    private fun setUpOnbackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled) {
                    Toast.makeText(requireContext(), "Go Back", Toast.LENGTH_SHORT).show()
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })


    }


    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                R.id.took_name -> {
                    if (hasFocus) {
                        if (binding.tookNameTil.isErrorEnabled) {
                            binding.tookNameTil.isErrorEnabled = false
                        }
                    } else {
                        valiDateFulLName()
                    }
                }
                R.id.phn_no -> {
                    if (hasFocus) {
                        if (binding.phoneTil.isErrorEnabled) {
                            binding.phoneTil.isErrorEnabled = false
                        }
                    } else {
                        valiPhoneNumber()
                    }

                }
                R.id.reg_address -> {
                    if (hasFocus) {
                        if (binding.addressTil.isErrorEnabled) {
                            binding.addressTil.isErrorEnabled = false
                        }
                    } else {
                        validAddress()
                    }
                }
                R.id.took_password -> {
                    if (hasFocus) {
                        if (binding.tookPasswordTil.isErrorEnabled) {
                            binding.tookPasswordTil.isErrorEnabled = false
                        } else {
                            if (validPassword() && binding.tookConfirmPassword.text!!.isEmpty() && validConfirmPassword() &&
                                ValidedpasswordandConfirmPassword()
                            ) {
                                if (binding.tookConfirmPasswordTil.isErrorEnabled) {
                                    binding.tookConfirmPasswordTil.isErrorEnabled = false
                                }
                                binding.tookConfirmPasswordTil.apply {
                                    setStartIconDrawable(R.drawable.check_circle_outline)
                                    setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }

                            }
                        }
                    } else {
                        validPassword()
                    }
                }
                R.id.took_confirm_password -> {
                    if (hasFocus) {
                        if (binding.tookConfirmPasswordTil.isCounterEnabled) {
                            binding.tookConfirmPasswordTil.isErrorEnabled = false
                        }
                    } else {
                        if (validConfirmPassword() && validPassword() && ValidedpasswordandConfirmPassword()) {
                            if (binding.tookPasswordTil.isErrorEnabled) {
                                binding.tookPasswordTil.isErrorEnabled = false
                            }
                            binding.tookConfirmPasswordTil.apply {
                                setStartIconDrawable(R.drawable.check_circle_outline)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }

                    }
                }

            }
        }
    }


}


