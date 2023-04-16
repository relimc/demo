package com.example.databindingtest1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databindingtest1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.clickHandlers = ClickHandlers(activityMainBinding)
        activityMainBinding.user = User(getUserName(), getUserId(), "https://toadlive.cn/static/images/avatar.jpg", 1)
    }

    private fun getUserName(): String? {
        return activityMainBinding.edUserName.text?.toString()
    }

    private fun getUserId(): String? {
        return activityMainBinding.edUserId.text?.toString()
    }
}