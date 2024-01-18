package com.example.login_signup.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.login_signup.R
import com.example.login_signup.databinding.SignInFragmentBinding
import com.google.firebase.auth.FirebaseAuth


class SignInFragment:BaseFragment() {
private val binding by lazy { SignInFragmentBinding.inflate(layoutInflater) }
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       firebaseAuth = FirebaseAuth.getInstance()
        binding.Notuser.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.EmailAddress.text.toString()
            val pass = binding.TextPassword.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        findNavController().navigate(R.id.action_signInFragment_to_finalFragment)
                    }else{
                        showToast(it.exception.toString())
                    }
                }
            }else{
                showToast("Empty fields are not allowed")
            }
        }

        }
    }
