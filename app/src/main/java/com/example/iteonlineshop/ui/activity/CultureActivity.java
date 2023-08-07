package com.example.iteonlineshop.ui.activity;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iteonlineshop.adapter.CultureAdapter;
import com.example.iteonlineshop.adapter.PopularAdapter;
import com.example.iteonlineshop.api.model.Culture;
import com.example.iteonlineshop.api.model.Popular_visit;
import com.example.iteonlineshop.api.model.Profiles;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.ActivityCultureBinding;
import com.example.iteonlineshop.databinding.ActivityEditBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CultureActivity extends AppCompatActivity {
    private ActivityCultureBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCultureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LoadCultureListFromServer();
        binding.imgback.setOnClickListener(v -> finish());
    }
    private void LoadCultureListFromServer(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //Create service object
        ApiService apiService = httpClient.create(ApiService.class);
        Call<List<Culture>> task = apiService.LoadCulturevisitList();
        task.enqueue(new Callback<List<Culture>>() {
            @Override
            public void onResponse(Call<List<Culture>> call, Response<List<Culture>> response) {
                if (response.isSuccessful()){
                    ShowProductList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Culture>> call, Throwable t) {

            }
        });
    }
    private void ShowProductList(List<Culture> cultureList){
        // create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.recyclerViewCulture.setLayoutManager(layoutManager);

        // Create adapter
        CultureAdapter adapter = new CultureAdapter();
        adapter.submitList(cultureList);
        binding.recyclerViewCulture.setAdapter(adapter);
    }
}
