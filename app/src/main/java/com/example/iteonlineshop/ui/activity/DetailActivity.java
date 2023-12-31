package com.example.iteonlineshop.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iteonlineshop.databinding.ActivityDetailBinding;
public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgback.setOnClickListener(v -> finish());
    }
}
