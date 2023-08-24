package com.example.iteonlineshop.ui.activity;

import static android.content.ContentValues.TAG;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.iteonlineshop.adapter.PopularAdapter;
import com.example.iteonlineshop.api.model.Popular_visit;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.ActivityVisitPopularBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowPopularActivity extends AppCompatActivity {
    private ActivityVisitPopularBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisitPopularBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LoadPhnomPenhListFromServer();
        binding.imgback.setOnClickListener(v -> finish());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
