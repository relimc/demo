package com.example.hilttest

import android.util.Log
import javax.inject.Inject

class MobilePhone @Inject constructor(private val simCard: SimCard) {
    fun dialNumber() {
        simCard.dialNumber()
    }
}

class SimCard @Inject constructor() {
    private val TAG = "SimCard"
    fun dialNumber() {
        Log.d(TAG, "拨打电话")
    }
}