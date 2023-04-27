package com.example.hilttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var userManager: UserManager
    @Inject lateinit var mobilePhone: MobilePhone
    @Inject lateinit var okHttpClient: OkHttpClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userManager.getUserToken()
        mobilePhone.dialNumber()
    }
}