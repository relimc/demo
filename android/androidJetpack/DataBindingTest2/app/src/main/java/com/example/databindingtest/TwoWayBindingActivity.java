package com.example.databindingtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.databindingtest.databinding.ActivityTwoWayBindingBinding;

public class TwoWayBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTwoWayBindingBinding activityTwoWayBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding);
        activityTwoWayBindingBinding.setViewModel(new TwoWayBindingViewModel());
    }
}