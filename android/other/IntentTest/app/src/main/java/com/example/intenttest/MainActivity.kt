package com.example.intenttest

import android.Manifest
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.intenttest.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ActionViewBtn.setOnClickListener {
            actionViewTest()
        }

        binding.ActionDialBtn.setOnClickListener {
            actionDialTest()
        }

        binding.ActionCallBtn.setOnClickListener {
            actionCallTest()
        }

        binding.ActionPickContactBtn.setOnClickListener {
            actionPickContactTest()
        }

    }

    private fun actionViewTest() {
        val uri = Uri.parse("https://www.baidu.com/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun actionDialTest() {
        val uri = Uri.parse("tel: 10086")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intent)
    }

    private fun actionCallTest() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED -> {
                val uri = Uri.parse("tel: 10086")
                val intent = Intent(Intent.ACTION_CALL, uri)
                startActivity(intent)
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE) -> {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
            else -> {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
        }
    }

    private fun actionPickContactTest() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, 3)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val uri = Uri.parse("tel: 10086")
                    val intent = Intent(Intent.ACTION_CALL, uri)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, requestCode.toString())
        if (requestCode == 3 && data != null) {
            Log.d(TAG, data.data.toString())
            val contactUri = data.data
            val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
            val cursor = contactUri?.let { contentResolver.query(it, queryFields, null, null, null) }
            cursor?.use {
                if (it.count == 0) return
                it.moveToFirst()
                Log.d(TAG, "get contact is ${it.getString(0)}")
            }
        }
    }

}