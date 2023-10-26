package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.quizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding : ActivityQuizBinding
    private lateinit var questiontv : TextView
    private lateinit var option1tv : TextView
    private lateinit var option2tv : TextView
    private lateinit var option3tv : TextView
    private lateinit var option4tv : TextView
    private  lateinit var nextbtn : Button

    private val quizQuestions: List<QuizQuestions> = listOf(
        QuizQuestions(
            question = "Which of the following option leads to the portability and security of Java?",
            options = listOf("A. Bytecode is executed by JVM", "B. Use of exception handling", "C. Dynamic binding between objects", "D. The applet makes the Java code secure and portable"),
            correctAnswer = "A. Bytecode is executed by JVM",
            marks = 1,
            ),
        QuizQuestions(
            question = "Which of the following is not a Java features?",
            options = listOf("A. Dynamic", "B. Architecture Neutral", "C. Use of pointers", "D. Object-oriented"),
            correctAnswer = "C. Use of pointers",
            marks = 1,

            ),
        QuizQuestions(
            question = " _____ is used to find and fix bugs in the Java programs.",
            options = listOf("A. JVM","B. JRE","C. JDK","D. JDB"),
            correctAnswer = "D. JDB",
            marks = 1
        )
        // Add more questions as needed
    )

    private var currentQuestionIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questiontv = binding.questiontv
        option1tv = binding.option1tv
        option2tv = binding.option2tv
        option3tv = binding.option3tv
        option4tv = binding.option4tv

        nextbtn = binding.nextbtn

        setQuestion(currentQuestionIndex)


        nextbtn.setOnClickListener {
            // Move to the next question
            currentQuestionIndex++
            // Check if all questions are answered
            if (currentQuestionIndex < quizQuestions.size) {
                // Set the next question
                setQuestion(currentQuestionIndex)
                // Reset background tint for options
                resetOptionBackgrounds()
//                 Set click listeners for options
                setOptionClickListener(option1tv, quizQuestions[currentQuestionIndex].correctAnswer)
                setOptionClickListener(option2tv, quizQuestions[currentQuestionIndex].correctAnswer)
                setOptionClickListener(option3tv, quizQuestions[currentQuestionIndex].correctAnswer)
                setOptionClickListener(option4tv, quizQuestions[currentQuestionIndex].correctAnswer)
            } else {
                val intent = Intent(this, FinishQuiz::class.java)
                intent.putParcelableArrayListExtra("quizQuestions", ArrayList(quizQuestions))
                startActivity(intent)
                finish()
            }
        }



        setOptionClickListener(option1tv, quizQuestions[currentQuestionIndex].correctAnswer)
        setOptionClickListener(option2tv, quizQuestions[currentQuestionIndex].correctAnswer)
        setOptionClickListener(option3tv, quizQuestions[currentQuestionIndex].correctAnswer)
        setOptionClickListener(option4tv, quizQuestions[currentQuestionIndex].correctAnswer)
    }

    private fun resetOptionBackgrounds() {
        val optionTextViews = listOf(option1tv, option2tv, option3tv, option4tv)
        for (optionTextView in optionTextViews) {
            optionTextView.setBackgroundColor(Color.WHITE)
        }
    }

    private fun setQuestion(questionIndex: Int) {
        val currentQuestion = quizQuestions[questionIndex]
        questiontv.text = currentQuestion.question
        option1tv.text = currentQuestion.options[0]
        option2tv.text = currentQuestion.options[1]
        option3tv.text = currentQuestion.options[2]
        option4tv.text = currentQuestion.options[3]
    }

    private fun setOptionClickListener(optionTextView: TextView, correctAnswer: String) {
        optionTextView.setOnClickListener {
            // Check if the selected option is correct
            val selectedOption = optionTextView.text.toString()
            println(selectedOption)
            println(correctAnswer)
            quizQuestions[currentQuestionIndex].selected = selectedOption
            if (selectedOption == correctAnswer) {
                // Correct answer, set background tint to green
                optionTextView.setBackgroundColor(Color.GREEN)
            } else {
                // Incorrect answer, set background tint to red
                optionTextView.setBackgroundColor(Color.RED)
                // Highlight the correct answer by finding the correct option TextView
                highlightCorrectOption(correctAnswer)
            }
            // Disable click listeners for options after selection
            disableOptionClickListeners()
        }
    }

    private fun disableOptionClickListeners() {
        option1tv.isClickable = false
        option2tv.isClickable = false
        option3tv.isClickable = false
        option4tv.isClickable = false
    }

    private fun highlightCorrectOption(correctAnswer: String) {
        val optionTextViews = listOf(option1tv, option2tv, option3tv, option4tv)
        for (optionTextView in optionTextViews) {
            if (optionTextView.text.toString() == correctAnswer) {
                optionTextView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}