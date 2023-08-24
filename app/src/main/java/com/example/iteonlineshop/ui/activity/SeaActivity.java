package com.example.iteonlineshop.ui.activity;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.iteonlineshop.adapter.CultureAdapter;
import com.example.iteonlineshop.api.model.Culture;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.ActivityCultureBinding;
import com.example.iteonlineshop.databinding.ActivitySeaBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class SeaActivity extends AppCompatActivity {
    private ActivitySeaBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgback.setOnClickListener(v -> finish());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
