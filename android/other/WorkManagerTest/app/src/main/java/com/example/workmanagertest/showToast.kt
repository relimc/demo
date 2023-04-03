package com.example.workmanagertest

import android.widget.Toast
import java.time.Duration

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}