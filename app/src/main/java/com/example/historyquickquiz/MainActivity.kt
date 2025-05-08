package com.example.historyquickquiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

//references
//Background image:https://www.canva.com/design/DAGmHQ65hSA/RIzkBF0iKypzdDAVgWKwKg/edit


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val welcometxt = findViewById<TextView>(R.id.welcometxt)
        val descriptiontxt = findViewById<TextView>(R.id.descriptiontxt)
        val Startbtn = findViewById<Button>(R.id.Startbtn)
        val Exitbtn1 = findViewById<Button>(R.id.Exitbtn1)

        //Start button used to start the quiz
        Startbtn.setOnClickListener {


            //Exit button used to exit the quiz
            Exitbtn1.setOnClickListener {
                finishAffinity()
                exitProcess(0)
            }





            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

        }
    }
}