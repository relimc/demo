package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton = findViewById(R.id.false_button)
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        questionTextView = findViewById(R.id.question_text_view)
        questionTextView.setText(questionBank[currentIndex].textResId)
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            questionTextView.setText(questionBank[currentIndex].textResId)
        }

        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            questionTextView.setText(questionBank[currentIndex].textResId)
        }

        prevButton = findViewById(R.id.prev_button)
        prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            if (currentIndex < 0) {
                Toast.makeText(this, "This is already the first question!", Toast.LENGTH_SHORT).show()
                currentIndex = 0
            } else {
                questionTextView.setText(questionBank[currentIndex].textResId)
            }
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == questionBank[currentIndex].answer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }
    }
}