package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizapp.databinding.ActivityFinishQuizBinding

class FinishQuiz : AppCompatActivity() {
    private lateinit var binding : ActivityFinishQuizBinding
    private lateinit var score : TextView
    private  lateinit var quitButton: Button
    private lateinit var startquizButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quizQuestions = intent.getParcelableArrayListExtra<QuizQuestions>("quizQuestions")
        startquizButton = binding.startquizButton
        quitButton = binding.quitButton
        score = binding.score

        val totalQuestions = quizQuestions?.size ?: 0
        val correctAnswers = quizQuestions?.count { it.selected == it.correctAnswer } ?: 0
        score.text = "$correctAnswers/$totalQuestions"

        binding.startquizButton.setOnClickListener{
            startActivity(
                Intent(this, QuizActivity::class.java)
            )
            Toast.makeText(this, "The Quiz has been started again", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.quitButton.setOnClickListener{
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }
    }
}