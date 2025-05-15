package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.historyquickquiz.Quiz.Companion.questions

class Quiz :
    AppCompatActivity() {

    private lateinit var questiontxt: TextView
    private lateinit var truebtn: Button
    private lateinit var falsebtn: Button
    private lateinit var nextbtn: Button
    private lateinit var feedbacktxt: TextView

    // The quiz questions:
    companion object {
        val questions = arrayOf(
            "Ketchup was once sold as medicine",
            "The sinking of the Titanic was not predicted",
            "The leaning tower of Pisa never stood up straight",
            "The statue of Liberty used to be a lighthouse",
            "The shortest war in history lasted 38 min",
            "Cars were invented in the United States",
            "The great wall of China is not the longest man-made structure in the world",
            "President Abraham Lincon's top hat had a purpose",
            "The empire state building has its own zip code ",
            "The last letter to be added to the alphabet was actually X"
        )
        val answers = booleanArrayOf(true, false, true, true, true, false, false, true, true, false)
        // You might want to add logic here for randomizing or picking 5 questions
        // For now, it's just a static list.
    }

    private var currentQuestionIndex = 0
    private var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // IMPORTANT: Make sure R.layout.activity_score is the correct layout
        // for THIS activity. If this is the main quiz activity, it should likely be
        // R.layout.activity_main (or whatever your quiz layout is named).
        // If R.layout.activity_score is for displaying the final score,
        // then the findViewById calls might fail if those IDs don't exist in that layout.
        setContentView(R.layout.activity_score) // Or R.layout.activity_main

        // Initialize UI elements
        questiontxt = findViewById(R.id.questiontxt)
        truebtn = findViewById(R.id.truebtn)
        falsebtn = findViewById(R.id.falsebtn)
        nextbtn = findViewById(R.id.nextbtn)
        feedbacktxt = findViewById(R.id.feedbacktxt)

        // Display 1st question
        displayQuestion()

        // Answer buttons
        truebtn.setOnClickListener { checkAnswer(true) }
        falsebtn.setOnClickListener { checkAnswer(false) }

        // Next button
        nextbtn.setOnClickListener {
            currentQuestionIndex++
            // Access questions and answers via the companion object
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
                feedbacktxt.text = ""
                truebtn.isEnabled = true
                falsebtn.isEnabled = true
            } else {
                // proceed to score activity
                val intent = Intent(this, Score::class.java)
                // Add score to represent what score a user got
                intent.putExtra("score", score)
                startActivity(intent)
                finish() // Finish this activity so the user can't go back to it
            }
            nextbtn.isEnabled = false // Disable next button until an answer is chosen
        }
        nextbtn.isEnabled = false // Initially disable next button
    }

    private fun displayQuestion() {
        // Access questions via the companion object
        questiontxt.text = questions[currentQuestionIndex]
        // Reset button states for the new question
        truebtn.isEnabled = true
        falsebtn.isEnabled = true
        nextbtn.isEnabled = false // Disable next until an answer is given
        feedbacktxt.text = ""     // Clear previous feedback
    }

    private fun checkAnswer(userAnswer: Boolean) {
        // Access answers via the companion object
        val correctAnswer = answers[currentQuestionIndex]

        if (userAnswer == correctAnswer) {
            feedbacktxt.text = "Correct!"
            feedbacktxt.setTextColor(Color.GREEN)
            score++
        } else {
            feedbacktxt.text = "Incorrect!"
            feedbacktxt.setTextColor(Color.RED)
        }

        truebtn.isEnabled = false
        falsebtn.isEnabled = false
        nextbtn.isEnabled = true // Enable next button to proceed
    }
}
