package com.example.iteonlineshop.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.iteonlineshop.adapter.CultureAdapter;
import com.example.iteonlineshop.adapter.MuseumAdapter;
import com.example.iteonlineshop.adapter.TempleAdapter;
import com.example.iteonlineshop.api.model.Culture;
import com.example.iteonlineshop.api.model.Museum;
import com.example.iteonlineshop.api.model.Temple;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.ActivityCultureBinding;
import com.example.iteonlineshop.databinding.ActivityTempleBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class TempleActivity extends AppCompatActivity{
    private ActivityTempleBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTempleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgback.setOnClickListener(v -> finish());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LoadTempleListFromServer();
    }
    private void LoadTempleListFromServer(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //Create service object
        ApiService apiService = httpClient.create(ApiService.class);
        Call<List<Temple>> task = apiService.LoadTempleList();
        task.enqueue(new Callback<List<Temple>>() {
            @Override
            public void onResponse(Call<List<Temple>> call, Response<List<Temple>> response) {
                if (response.isSuccessful()){
                    ShowProductList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Temple>> call, Throwable t) {

            }
        });
    }
    private void ShowProductList(List<Temple> templeList){
        // create layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        binding.recyclerViewTemple.setLayoutManager(layoutManager);

        // Create adapter
        TempleAdapter adapter = new TempleAdapter();
        adapter.submitList(templeList);
        binding.recyclerViewTemple.setAdapter(adapter);
    }
}
