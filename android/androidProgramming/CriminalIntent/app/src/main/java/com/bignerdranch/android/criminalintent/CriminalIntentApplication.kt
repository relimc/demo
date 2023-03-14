package com.bignerdranch.android.criminalintent

import android.app.Application

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 打开应用时，创建 CrimeRepository 单例
        CrimeRepository.initialize(this)
    }
}