package com.example.second_kotlin_project.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.second_kotlin_project.R
import com.example.second_kotlin_project.databinding.FragmentRegistrationBinding
import com.example.second_kotlin_project.databinding.FragmentTestkBinding


class TestkFragment : Fragment() {


    // TODO: Rename and change types of parameters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_testk, container, false)
        val bt=v.findViewById<Button>(R.id.sign_up)
        
        bt.setOnClickListener {
            Toast.makeText(activity, "button click susccesfull", Toast.LENGTH_LONG).show()
        }
        return v
    }


}