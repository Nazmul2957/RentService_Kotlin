package com.example.second_kotlin_project.Fragment

import android.app.ProgressDialog
import android.content.res.Resources
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.second_kotlin_project.Mainviewmodelfactory.MainViewModelFactory
import com.example.second_kotlin_project.Model.Model.Login.Login_Response
import com.example.second_kotlin_project.R
import com.example.second_kotlin_project.Repository.Repository
import com.example.second_kotlin_project.ViewModel.MainViewModel
import com.example.second_kotlin_project.databinding.FragmentSignInBinding
import com.example.second_kotlin_project.utils.NetworkResult
import com.example.second_kotlin_project.utils.TokenManager
import okhttp3.ResponseBody
import retrofit2.Response


class SignInFragment : Fragment() {

    var _signinbinding: FragmentSignInBinding? = null
    val signInBinding get() = _signinbinding

    lateinit var tokenManager: TokenManager
    lateinit var viewmodel: MainViewModel
    var result: String = null.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _signinbinding = FragmentSignInBinding.inflate(inflater, container, false)
        // inflater.inflate(R.layout.fragment_sign_in, container, false)
        val repository = Repository()
        val progressDialog = ProgressDialog(requireActivity())
        progressDialog.setMessage("Application is loading, please wait")
        viewmodel =
            ViewModelProvider(
                requireActivity(),
                MainViewModelFactory(repository)
            ).get(MainViewModel::class.java)

        signInBinding?.signIp?.setOnClickListener {
            if (!TextUtils.isEmpty(signInBinding?.mobileNumber?.text.toString())) {
                if (!TextUtils.isEmpty(signInBinding?.passwordMatch?.text.toString())) {
                    progressDialog.dismiss()
                    viewmodel.getlogin(
                        signInBinding?.mobileNumber?.text.toString(),
                        signInBinding?.passwordMatch?.text.toString()
                    )

                    var respons = viewmodel.loginrepsons.value?.body()?.key.toString()
                    Log.d("show_resposns",respons)
//                    Toast.makeText(requireActivity(), respons.toString(), Toast.LENGTH_SHORT)
//                        .show()

                } else {
                    signInBinding?.passwordMatch?.setError("Input Valid Password")
                    signInBinding?.passwordMatch?.requestFocus()
                }
            } else {
                signInBinding?.mobileNumber?.setError("Input Valid Phone Number")
                signInBinding?.mobileNumber?.requestFocus()
            }
        }

        viewmodel.loginrepsons.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkResult.Loading<*> ->{
                    tokenManager.savetoken(
                        it.body()
                            ?.key.toString())
                    Log.d("showsavekeytoken",it.body()?.key.toString())
                }
//                    is NetworkResult.Success<*> -> {
//                        tokenManager.savetoken(it.login)
//                        Log.d("showsavekeytoken",it.body()?.key.toString())
//                    }
            }
        })

        return signInBinding?.root
    }


}