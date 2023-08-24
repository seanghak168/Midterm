package com.example.iteonlineshop.ui.activity;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iteonlineshop.databinding.ActivityMountainBinding;
import com.example.iteonlineshop.databinding.ActivityTempleBinding;

public class MountainActivity extends AppCompatActivity {
    private ActivityMountainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMountainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgback.setOnClickListener(v -> finish());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
