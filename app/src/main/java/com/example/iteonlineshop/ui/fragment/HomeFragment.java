package com.example.iteonlineshop.ui.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iteonlineshop.adapter.HomePostsAdapter;
import com.example.iteonlineshop.adapter.PopularAdapter;
import com.example.iteonlineshop.api.model.PhnomPenh;
import com.example.iteonlineshop.api.model.Popular_visit;
import com.example.iteonlineshop.api.service.ApiService;
import com.example.iteonlineshop.databinding.FragmentHomeBinding;
import com.example.iteonlineshop.ui.activity.CultureActivity;
import com.example.iteonlineshop.ui.activity.HistoryActivity;
import com.example.iteonlineshop.ui.activity.MountainActivity;
import com.example.iteonlineshop.ui.activity.MuseumsActivity;
import com.example.iteonlineshop.ui.activity.SeaActivity;
import com.example.iteonlineshop.ui.activity.ShowPopularActivity;
import com.example.iteonlineshop.ui.activity.TempleActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.txtCulture.setOnClickListener(v -> startEditActivity());
        binding.History.setOnClickListener(v -> startHistoryActivity());
        binding.Mountain.setOnClickListener(v -> startMountainActivity());
        binding.Museums.setOnClickListener(v -> startMuseumsActivity());
        binding.Sea.setOnClickListener(v -> startSeaActivity());
        binding.Temple.setOnClickListener(v -> startTempleActivity());
        binding.more.setOnClickListener(v -> startMoreActivity());
        return binding.getRoot();
    }
    private void startEditActivity(){
        Intent intent = new Intent(getActivity(), CultureActivity.class);
        startActivity(intent);
    }
    private void startMoreActivity(){
        Intent intent = new Intent(getActivity(), ShowPopularActivity.class);
        startActivity(intent);
    }
    private void startMountainActivity(){
        Intent intent = new Intent(getActivity(), MountainActivity.class);
        startActivity(intent);
    }
    private void startHistoryActivity(){
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }
    private void startMuseumsActivity(){
        Intent intent = new Intent(getActivity(), MuseumsActivity.class);
        startActivity(intent);
    }

    private void startSeaActivity(){
        Intent intent = new Intent(getActivity(), SeaActivity.class);
        startActivity(intent);
    }

    private void startTempleActivity(){
        Intent intent = new Intent(getActivity(), TempleActivity.class);
        startActivity(intent);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoadPopularvisitListFromServer();
        LoadPhnomPenhListFromServer();
    }
    private void LoadPopularvisitListFromServer(){
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
                    ShowProductList(response.body());
                }else {
                    Toast.makeText(getContext(), "No",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Popular_visit>> call, Throwable t) {
                Toast.makeText(getContext(), "No",Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    private void ShowProductList(List<Popular_visit> productList){
        // create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        binding.RecyclerViewVisitPhnompenh.setLayoutManager(layoutManager);

        // Create adapter
        PopularAdapter adapter = new PopularAdapter();
        adapter.submitList(productList);
        binding.RecyclerViewVisitPhnompenh.setAdapter(adapter);
    }
    private void LoadPhnomPenhListFromServer(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        //Create service object
        ApiService apiService = httpClient.create(ApiService.class);
        Call<List<PhnomPenh>> task = apiService.LoadPhnompenhvisitList();
        task.enqueue(new Callback<List<PhnomPenh>>() {
            @Override
            public void onResponse(Call<List<PhnomPenh>> call, Response<List<PhnomPenh>> response)
            {
                if (response.isSuccessful()){
                    ShowProductList1(response.body());
                }else {
                    Toast.makeText(getContext(), "No",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<PhnomPenh>> call, Throwable t) {
                Toast.makeText(getContext(), "No",Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    private void ShowProductList1(List<PhnomPenh> phnomPenhList){
        // create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.RecyclerViewPopularVisit.setLayoutManager(layoutManager);

        // Create adapter
        HomePostsAdapter adapter = new HomePostsAdapter();
        adapter.submitList(phnomPenhList);
        binding.RecyclerViewPopularVisit.setAdapter(adapter);
    }

}
