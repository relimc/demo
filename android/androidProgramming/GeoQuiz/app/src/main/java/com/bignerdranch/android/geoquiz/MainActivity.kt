package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var scoreTextView: TextView
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }
    private var count = 0

    /*
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate called")

        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0

        val provider = ViewModelProvider(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        quizViewModel.setCurrentIndex(currentIndex)
        Log.d(TAG, "Got a QuizViewModel:$quizViewModel")

        scoreTextView = findViewById(R.id.score)

        trueButton = findViewById(R.id.true_button)
        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton = findViewById(R.id.false_button)
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        questionTextView = findViewById(R.id.question_text_view)
        upateQuestion()
        questionTextView.setOnClickListener {
            // currentIndex = (currentIndex + 1) % questionBank.size
            // questionTextView.setText(questionBank[currentIndex].textResId)
            quizViewModel.moveToNext()
            upateQuestion()
        }

        nextButton = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
             quizViewModel.moveToNext()
            if (quizViewModel.getCurrentIndex() == quizViewModel.getQuestionSize()) {
                Toast.makeText(this, "This is already the last question!", Toast.LENGTH_SHORT).show()
                scoreTextView.visibility = View.VISIBLE
                scoreTextView.text = "Your score is $count"
                quizViewModel.setCurrentIndex(quizViewModel.getQuestionSize() - 1)
            } else {
                // questionTextView.setText(questionBank[currentIndex].textResId)
                upateQuestion()
                trueButton.isEnabled = true
                falseButton.isEnabled = true
            }
        }

        cheatButton = findViewById(R.id.cheat_button)
        cheatButton.setOnClickListener {
            val intent = CheatActivity.newIntent(this, quizViewModel.currentQuestionAnswer)
            // startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }

        prevButton = findViewById(R.id.prev_button)
        prevButton.setOnClickListener {
            quizViewModel.moveToLast()
            if (quizViewModel.getCurrentIndex() < 0) {
                Toast.makeText(this, "This is already the first question!", Toast.LENGTH_SHORT).show()
                quizViewModel.setCurrentIndex(0)
            } else {
                // questionTextView.setText(questionBank[currentIndex].textResId)
                upateQuestion()
                trueButton.isEnabled = true
                falseButton.isEnabled = true
            }
        }
    }

    private fun upateQuestion() {
        // Log.d(TAG, "Updating question text", Exception())
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
         val correctAnswer: Boolean = quizViewModel.currentQuestionAnswer

        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        /*
        if (userAnswer == quizViewModel.currentQuestionAnswer) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
            trueButton.isEnabled = false
            falseButton.isEnabled = false
            count += 1
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }
         */
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.getCurrentIndex())
    }

    // requestCode 就是 startActivityForResult(intent, REQUEST_CODE_CHEAT) 中的 REQUEST_CODE_CHEAT
    // resultCode 就是 setResult(Activity.RESULT_OK, data) 中的 Activity.RESULT_OK
    // data 就是 setResult(Activity.RESULT_OK, data) 中的 data
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult")
        Log.d(TAG, Activity.RESULT_OK.toString())
        Log.d(TAG, "onActivityResult: $requestCode")
        Log.d(TAG, "onActivityResult: $resultCode")
        Log.d(TAG, "onActivityResult")
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            Log.d(TAG, quizViewModel.isCheater.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

}