package com.chenxinjian.dynamicloadjar;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chenxinjian.dynamicloadjar.databinding.ActivityMainBinding;
import com.chenxinjian.dynamicloadjar.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(MainViewModel.getInstance());

    }
}
