package com.example.runtimepermissiontest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

private const val TAG = "RequestPermissionFragment"

class RequestPermissionFragment : Fragment() {

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) call()
        else Log.d(TAG, "permission is not granted.")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_permission, container, false)
    }

    override fun onStart() {
        super.onStart()
        val activity = activity
        val makeCall = activity?.findViewById<Button>(R.id.makeCall)
        makeCall?.setOnClickListener {
            when {
                // 当已经有拨打电话的权限时，直接拨打电话
                ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED -> {
                    call()
                }
                // 已获取的权限被用户手动关闭时，需要再次申请权限
                shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {
                    Log.d(TAG, "shouldShowRequestPermissionRationale")
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
                // 当没有拨打电话的权限时，申请拨打电话的权限
                else -> requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
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