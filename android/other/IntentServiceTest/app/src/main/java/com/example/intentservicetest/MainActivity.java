package com.example.intentservicetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.intentservicetest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public final static String ACTION_TYPE_THREAD = "action.type.thread";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        LocalBroadcastManager mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        MyBroadcastReceiver mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_THREAD);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);

        initView();

        binding.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyIntentService.class);
            startService(intent);
        });

    }

    private void initView() {
        binding.tvStatus.setText("线程状态：未运行");
        binding.progressBar.setMax(100);
        binding.progressBar.setProgress(0);
        binding.tvProgress.setText("0%");
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_TYPE_THREAD)) {
                int progress = intent.getIntExtra("progress", 0);
                binding.tvStatus.setText("线程状态：" + intent.getStringExtra("status"));
                binding.progressBar.setProgress(progress);
                binding.tvProgress.setText(progress + "%");
                if (progress >= 100) {
                    binding.tvStatus.setText("线程结束");
                }
            }
        }
    }
}