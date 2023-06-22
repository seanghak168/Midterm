package com.example.iteonlineshop.api.service;

import com.example.iteonlineshop.api.model.Product;
import com.example.iteonlineshop.api.model.Profiles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("kimsongsao/ferupp/main/products.json")
    Call<List<Product>> LoadProductList();


    @GET("/kimsongsao/ferupp/main/profile.json")
     Call <Profiles> loadProfile();
}
