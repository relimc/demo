package com.example.runtimepermissiontest

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val makeCall = findViewById<Button>(R.id.makeCall)
        makeCall.setOnClickListener {
            // checkSelfPermission 方法检查你是否有特定的权限，
            // 当你有权限时，返回 PackageManager.PERMISSION_GRANTED
            // 当你没有权限时，返回 PackageManager.PERMISSION_DENIED
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                // requestPermissions 方法用于请求运行时权限，第一个参数是个 Activity 对象，
                // 第二个参数是个数组，可将要申请的一个或者多个权限放在这里
                // 第三个参数是请求码，请求码将会传递给 onRequestPermissionsResult 方法
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                call()
            }
        }
    }

    // 在我们请求权限后，无论用户同意还是拒绝，都会执行这个方法
    override fun onRequestPermissionsResult(
        requestCode: Int,  // 这个是请求权限时，通过 requestPermissions 方法传递过来的参数
        permissions: Array<out String>,  // 这个是请求权限时，通过 requestPermissions 方法传递过来的参数
        grantResults: IntArray  // 这个参数是个数字数组，存放授权的结果，与第二个请求的权限数组一一对应，授权为 0，拒绝授权为 -1
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call()
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel: 10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}