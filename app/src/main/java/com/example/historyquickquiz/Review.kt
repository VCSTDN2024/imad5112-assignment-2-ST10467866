package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
// import android.widget.ScrollView // ScrollView itself is not directly manipulated for text
import android.widget.TextView // Import TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Review : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        val restartButton = findViewById<Button>(R.id.restartbtn)
        val exitButton = findViewById<Button>(R.id.Exitbtn3)
        // val scrollView = findViewById<ScrollView>(R.id.scrollView2) // You might not need a direct reference to the ScrollView itself
        val reviewContentTextView = findViewById<TextView>(R.id.reviewContentTextView) // TextView inside ScrollView

        // Questions and answers are passed
        val questions = intent.getStringArrayExtra("Questions")
        val answers = intent.getBooleanArrayExtra("Answers")

        val reviewTextBuilder = StringBuilder() // Renamed for clarity
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewTextBuilder.append("${i + 1}. ${questions[i]}\n")
                reviewTextBuilder.append("Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            reviewContentTextView.text = reviewTextBuilder.toString() // Set text on the TextView
        } else {
            reviewContentTextView.text = "Error: Could not load review data." // Set error text on the TextView
        }

        // Restart button
        restartButton.setOnClickListener {
            val quizIntent = Intent(this, Quiz::class.java)
            // Optional: Clear task or set flags if you want to ensure a fresh quiz start
            // quizIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(quizIntent)
            finish() // Finish Review activity so user can't go back to it after restarting
        }

        // Exit button - This listener should be separate, not nested.
        exitButton.setOnClickListener {
            finishAffinity() // Finishes this activity and all activities immediately below it
            exitProcess(0)   // Exits the application process
        }
    }
}