package com.example.iteonlineshop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.iteonlineshop.R;
import com.example.iteonlineshop.databinding.ActivityMainBinding;
import com.example.iteonlineshop.ui.fragment.HomeFragment;
import com.example.iteonlineshop.ui.fragment.MoreFragment;
import com.example.iteonlineshop.ui.fragment.LocationFragment;
import com.example.iteonlineshop.ui.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showFragment(new HomeFragment());
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.mnuHome) {
                showFragment(new HomeFragment());
            } else if(item.getItemId() == R.id.mnuProduct) {
                showFragment(new LocationFragment());
            } else if(item.getItemId() == R.id.mnuProfile) {
                showFragment(new ProfileFragment());
            } else {
                showFragment(new MoreFragment());
            }

            return true;
        });
    }
    private void showFragment(Fragment fragment){
        // FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace fragment in lytFragment
        fragmentTransaction.replace(R.id.lytFragment, fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }
}