package com.example.login_signup.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.login_signup.R
import com.example.login_signup.databinding.SignUpFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : BaseFragment() {
    private val binding by lazy { SignUpFragmentBinding.inflate(layoutInflater) }
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
        binding.Alreadyuser.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.btnsignUp.setOnClickListener {
            val email = binding.EmailAddress.text.toString()
            val pass = binding.TextPassword.text.toString()
            val confirmpass = binding.reTextPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)

                        } else {
                            showToast(it.exception.toString())
                        }
                    }

                } else {
                    showToast("Password is not Matching")
                }
            } else {
                showToast("Empty fields are not allowed")
            }
        }
    }

}