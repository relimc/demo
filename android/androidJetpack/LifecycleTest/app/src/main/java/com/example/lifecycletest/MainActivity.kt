package com.example.lifecycletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //跳过广告按钮
    lateinit var btnIngore: Button
    //广告时间
    lateinit var tvAdvertisingTime: TextView
    private var advertisingManage: AdvertisingManage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)
        btnIngore = findViewById(R.id.btn_ignore)
        tvAdvertisingTime = findViewById(R.id.tv_advertising_time)
        advertisingManage = AdvertisingManage()
        advertisingManage?.advertisingManageListern =
            object : AdvertisingManage.AdvertisingManageListern {
                override fun timing(second: Int) {
                    tvAdvertisingTime.text = "广告剩余$second秒"
                }
                override fun enterMainActivity() {
                    MainActivity.actionStart(this@MainActivity)
                    finish()
                }
            }
        //跳过广告点击事件
        btnIngore.setOnClickListener {
            MainActivity.actionStart(this@MainActivity)
            finish()
        }
        //开始广告
        advertisingManage?.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        advertisingManage?.onCancel()
    }
}