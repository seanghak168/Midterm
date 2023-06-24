package com.example.iteonlineshop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iteonlineshop.adapter.NewProductAdapter;
import com.example.iteonlineshop.adapter.NewProfileAdapter;
import com.example.iteonlineshop.adapter.ProductAdapter;
import com.example.iteonlineshop.adapter.ProfileAdapter;
import com.example.iteonlineshop.api.model.Product;
import com.example.iteonlineshop.api.model.Profiles;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.FragmentProfileBinding;
import com.example.iteonlineshop.ui.activity.EditActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        LoadingProfile();
        binding.imgEdit.setOnClickListener(v -> startEditActivity());
        return binding.getRoot();
    }
    private void LoadingProfile(){
        binding.recyclerViewProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        ProfileAdapter profileAdapter = new ProfileAdapter();
        binding.recyclerViewProfile.setAdapter(profileAdapter);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Profiles> task = apiService.loadProfile();
        task.enqueue(new Callback<Profiles>() {
            @Override
            public void onResponse(Call<Profiles> call, Response<Profiles> response) {
                profileAdapter.setProfile(response.body());
            }
            @Override
            public void onFailure(Call<Profiles> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void startEditActivity(){
        Intent intent = new Intent(getActivity(), EditActivity.class);
        startActivity(intent);
    }

}
