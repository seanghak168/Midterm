package com.example.iteonlineshop.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iteonlineshop.adapter.ProfileAdapter;
import com.example.iteonlineshop.api.model.Profiles;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.ActivityEditBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgback.setOnClickListener(v -> finish());
        loadProfileFromServer();
    }
    public void loadProfileFromServer(){
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = httpClient.create(ApiService.class);
        Call<Profiles> task = apiService.loadProfile();
        task.enqueue(new Callback<Profiles>() {
            @Override
            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
                if(response.isSuccessful()){
                    Profiles data = response.body();
                    assert data != null;
                    binding.Email.setText(data.getEmail());
                    binding.PhoneNumber.setText(data.getPhoneNumber());
                    binding.Gender.setText(data.getGender());
                    binding.HBD.setText(data.getBirthday());
                    binding.Address.setText(data.getAddress());
                }
                else {
                    Toast.makeText(getContext(),"Province list falsed!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Profiles> call, Throwable t) {
                Toast.makeText(getContext(), "Please check your internet!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Context getContext() {
        return null;
    }
}
