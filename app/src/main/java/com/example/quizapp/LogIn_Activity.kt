package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.system.exitProcess

class LogIn_Activity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gotosignup.setOnClickListener {
            val intent = Intent(this, SignUp_Activity::class.java)
            startActivity(intent)
            finish()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPass.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        startActivity(
                            Intent(this, MainActivity::class.java)
                        )
                        finish()
                    } else {
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                        println(it.toString())
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = firebaseAuth.currentUser

        if(currentUser != null) {
            Toast.makeText(this, "Signed in as ${currentUser.email}", Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        } else {
            Toast.makeText(this, "Not signed in..", Toast.LENGTH_SHORT).show()
        }
    }
}