package com.example.iteonlineshop.ui.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iteonlineshop.adapter.NewProductAdapter;
import com.example.iteonlineshop.adapter.ProductAdapter;
import com.example.iteonlineshop.api.model.Product;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.FragmentProductBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {
    private FragmentProductBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Load list Product from server (api)
        LoadProductListFromServer();
    }
    private void LoadProductListFromServer(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //Create service object
        ApiService apiService = httpClient.create(ApiService.class);
        Call<List<Product>> task = apiService.LoadProductList();
        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response)
            {
                if (response.isSuccessful()){
                    ShowGridProductList(response.body());
                }else {
                    Toast.makeText(getContext(), "No",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "No",Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    private void ShowProductList(List<Product> productList){
        // create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        // Create adapter
        ProductAdapter adapter = new ProductAdapter();
        adapter.submitList(productList);
        binding.recyclerView.setAdapter(adapter);
    }

    private void ShowGridProductList(List<Product> productList){
        // create layout manager
        GridLayoutManager gridLayout = new GridLayoutManager(getContext(),2);
        binding.recyclerView.setLayoutManager(gridLayout);

        // Create adapter
        NewProductAdapter adapter = new NewProductAdapter();
        adapter.submitList(productList);
        binding.recyclerView.setAdapter(adapter);
    }
}
