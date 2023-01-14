package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

private const val TAG = "CheatActivity"
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var showAPILevel: TextView

    private var cheatTimes = 0
    private var answerIsTrue = false

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean, cheatTimes: Int): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                putExtra("cheatTimes", cheatTimes)
            }
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
            putExtra("cheatTimes", cheatTimes)
        }
        setResult(Activity.RESULT_OK, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatTimes = intent.getIntExtra("cheatTimes", 0)

        answerTextView = findViewById(R.id.answer_text_view)

        val answer = savedInstanceState?.getString("answer")
        if (answer != null) {
            answerTextView.text = answer
            setAnswerShownResult(true)
        }

        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> "true"
                else -> "false"
            }
            cheatTimes += 1
            answerTextView.text = answerText
            setAnswerShownResult(true)
        }

        showAPILevel = findViewById(R.id.show_api_level)
        showAPILevel.text = "API Level ${Build.VERSION.SDK_INT}"

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("answer", answerTextView.text.toString())
    }
}