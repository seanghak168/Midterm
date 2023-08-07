package com.example.iteonlineshop.ui.activity;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iteonlineshop.adapter.CultureAdapter;
import com.example.iteonlineshop.adapter.HomePostsAdapter;
import com.example.iteonlineshop.adapter.PopularAdapter;
import com.example.iteonlineshop.api.model.Culture;
import com.example.iteonlineshop.api.model.PhnomPenh;
import com.example.iteonlineshop.api.model.Popular_visit;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.ActivityCultureBinding;
import com.example.iteonlineshop.databinding.ActivityVisitPhnompenhBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPhnompenhActivity extends AppCompatActivity {
    private ActivityVisitPhnompenhBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisitPhnompenhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LoadPhnomPenhListFromServer();
        binding.imgback.setOnClickListener(v -> finish());
    }
    private void LoadPhnomPenhListFromServer(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //Create service object
        ApiService apiService = httpClient.create(ApiService.class);
        Call<List<Popular_visit>> task = apiService.LoadPopularvisitList();
        task.enqueue(new Callback<List<Popular_visit>>() {
            @Override
            public void onResponse(Call<List<Popular_visit>> call, Response<List<Popular_visit>> response)
            {
                if (response.isSuccessful()){
                    ShowProductList1(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Popular_visit>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    private void ShowProductList1(List<Popular_visit> popularVisitList){
        // create layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        binding.recyclerViewShow.setLayoutManager(layoutManager);

        // Create adapter
        PopularAdapter adapter = new PopularAdapter();
        adapter.submitList(popularVisitList);
        binding.recyclerViewShow.setAdapter(adapter);
    }
}
