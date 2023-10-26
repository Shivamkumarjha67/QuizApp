package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp_Activity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.gotoLogin.setOnClickListener {
            val intent = Intent(this, LogIn_Activity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPass.text.toString()
            val cPassword = binding.edtConfirmpass.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && cPassword.isNotEmpty()) {
                if(password == cPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if(it.isSuccessful) {
                                startActivity(
                                    Intent(this, LogIn_Activity::class.java)
                                )
                                finish()
                            } else {
                                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                                println(it.toString())
                            }
                        }
                } else {
                    Toast.makeText(this, "Password can not be different", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fields can not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}