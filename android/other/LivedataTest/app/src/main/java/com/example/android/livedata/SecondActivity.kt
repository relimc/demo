package com.example.android.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.arch.core.util.Function
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.livedata.databinding.ActivitySecondBinding

private const val TAG = "SecondActivity"

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val secondViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
//        secondViewModel.userData.observe(this, Observer {
//            binding.mTvShow.text = "姓名：${it.name} \n薪水：${it.salary}"
//        })

        binding.mBtnData.setOnClickListener {
            Log.d(TAG, "mBtnData")
            secondViewModel.getUserInfo()
        }

//        Transformations.map(secondViewModel.userData) {
//            it?.name = "张三"
//            it
//        }.observe(this, Observer {
//            binding.mTvShow.text = "姓名：${it.name} \n薪水：${it.salary}"
//        })

        Transformations.switchMap(secondViewModel.userData) {
            secondViewModel.getUserName(it)
        }.observe(this) {
            binding.mTvShow.text = "姓名：${it.name} \n薪水：${it.salary}"
        }

    }
}