package com.example.ratatouille23.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.ratatouille23.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeAddettoSalaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_addetto_sala);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.addettoSalaFragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.addettoSalaBottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}