package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Score : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        // UI Elements
        val scoreTextView = findViewById<TextView>(R.id.score) // Renamed for clarity
        val feedbackMessageTextView = findViewById<TextView>(R.id.feedbacktxt) // Renamed for clarity
        val reviewButton = findViewById<Button>(R.id.Reviewbtn) // Renamed for clarity
        val exitButton = findViewById<Button>(R.id.Exitbtn2) // Renamed for clarity

        // Get the score from the Intent
        val userScore = intent.getIntExtra("score", 0)

        // Display the score
        // Assuming you want to show the score out of the total number of questions.
        // You'll need to get the total number of questions from your Quiz class.
        val totalQuestions = Quiz.questions.size
        scoreTextView.text = "Your score is: $userScore/$totalQuestions"

        // Determine and display feedback message
        val feedbackMessage = if (userScore >= 3) { // Assuming 3 is a good threshold
            "Well done!"
        } else {
            "Keep practicing!"
        }
        feedbackMessageTextView.text = feedbackMessage

        // Review button
        reviewButton.setOnClickListener {
            val reviewIntent = Intent(this, Review::class.java)
            // Pass the questions and answers to the Review activity
            // Note: For complex data, consider Parcelable or other serialization if needed,
            // but for arrays of strings and booleans, this is fine.
            reviewIntent.putExtra("Questions", Quiz.questions) // Array<String>
            reviewIntent.putExtra("Answers", Quiz.answers)     // BooleanArray
            startActivity(reviewIntent)
            // Optionally finish this Score activity if you don't want users to return to it
            // finish()
        }

        // Exit button
        exitButton.setOnClickListener {
            finishAffinity() // Finishes this activity and all activities immediately below it in the current task
            exitProcess(0)   // Exits the application process
        }
    }
}