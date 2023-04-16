package com.example.databindingtest1

import android.util.Log
import android.view.View
import com.example.databindingtest1.databinding.ActivityMainBinding

class ClickHandlers(private val binding: ActivityMainBinding) {
    var TAG = "ClickHandlers"
    fun confirm(view: View) {
        Log.d(TAG, "触发点击事件了")
        binding.user = getUser()
    }
    fun confirm2(view: View, user: User) {
        Log.d(TAG, "触发点击事件了")
        binding.user = getUser()
    }

    fun getUser(): User {
        return User(getUserName(), getUserId(), "https://toadlive.cn/static/images/avatar.jpg", 1)
    }

    private fun getUserName(): String? {
        return binding.edUserName.text?.toString()
    }

    private fun getUserId(): String? {
        return binding.edUserId.text?.toString()
    }


}