package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    var currentQuestionIsCheated: Boolean
        get() = questionBank[currentIndex].isCheated
        set(isCheated) {
            questionBank[currentIndex].isCheated = isCheated
        }

    fun moveToNext() {
        currentIndex += 1
    }

    fun moveToLast() {
        currentIndex -= 1
    }

    fun getQuestionSize() = questionBank.size

    init {
        Log.d(TAG, "ViewModel instantce created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}