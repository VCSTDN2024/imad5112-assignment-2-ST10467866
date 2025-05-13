package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {

    private lateinit var questiontxt: TextView
    private lateinit var truebtn: Button
    private lateinit var falsebtn: Button
    private lateinit var nextbtn: Button
    private lateinit var feedbacktxt: TextView
    
//Questions:
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
    }

    private var currentQuestionIndex = 0
    private var score = 0


    private fun checkAnswer(b: Boolean) {

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        
        // UI elements
        questiontxt = findViewById(R.id.questiontxt)
        truebtn = findViewById(R.id.truebtn)
        falsebtn = findViewById(R.id.falsebtn)
        nextbtn = findViewById(R.id.nextbtn)
        feedbacktxt = findViewById(R.id.feedbacktxt)
        
        // Answer buttons
        truebtn.setOnClickListener { checkAnswer(true) }
        falsebtn.setOnClickListener { checkAnswer(false) }
        
        // Next button
        nextbtn.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
                feedbacktxt.text = ""
                truebtn.isEnabled = true
                falsebtn.isEnabled = true
            } else {
                // proceed to score activity
                val intent = Intent(this, Score::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        
        nextbtn.isEnabled = false 
            }

        private fun displayQuestion() {
            questiontxt.text = questions[currentQuestionIndex]
        }

        private fun checkAnswer(b: Boolean) {
            val correctAnswer = answers[currentQuestionIndex]

            val userAnswer = null
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
            nextbtn.isEnabled = true
            
            })
        }        


        
        
        
        
        
        
        
        
        
        
        
        
            
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}