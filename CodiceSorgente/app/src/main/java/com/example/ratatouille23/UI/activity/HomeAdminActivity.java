package com.example.ratatouille23.UI.activity;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.ratatouille23.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeAdminActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;

    private NavController navController;

    public BottomNavigationView bottomNavigationView;

    private AlertDialog uscitaCreazioneUtenteAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        uscitaCreazioneUtenteAlertDialog = creaUscitaCreazioneUtenteAlertDialog();

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.adminFragmentContainerView);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.adminBottomNavigationView);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(bottomNavigationView.getMenu().findItem(R.id.creaUtenteFragment).isChecked() && item.getItemId()!=R.id.creaUtenteFragment) {
                    uscitaCreazioneUtenteAlertDialog.show();
                }
                else navController.navigate(item.getItemId());

                return true;
            }
        });

//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//            @Override
//            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
//                if(navDestination.getId() == R.id.aggiungiSezioneFragment
//                        || navDestination.getId() == R.id.modificaSezioniFragment
//                        || navDestination.getId() == R.id.aggiungiProdottoFragment
//                        || navDestination.getId() == R.id.modificaProdottoFragment)
//                    bottomNavigationView.setVisibility(View.GONE);
//                else if(bottomNavigationView.getVisibility() == View.GONE) bottomNavigationView.setVisibility(View.VISIBLE);
//            }
//        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navController.navigate(R.id.homeAdminFragment);
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

    }


    AlertDialog creaUscitaCreazioneUtenteAlertDialog() {

        AlertDialog.Builder uscitaCreazioneUtenteAlertDialogBuilder = new AlertDialog.Builder(this);
        uscitaCreazioneUtenteAlertDialogBuilder.setMessage("Uscendo non creerai l'utente. Vuoi proseguire?");
        uscitaCreazioneUtenteAlertDialogBuilder.setCancelable(false);

        uscitaCreazioneUtenteAlertDialogBuilder.setPositiveButton(
                "Conferma",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        navController.navigate(bottomNavigationView.getSelectedItemId());
                    }
                });

        uscitaCreazioneUtenteAlertDialogBuilder.setNegativeButton(
                "Annulla",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        bottomNavigationView.getMenu().findItem(R.id.creaUtenteFragment).setChecked(true);
//                        bottomNavigationView.setSelectedItemId(R.id.creaUtenteFragment);
                        dialog.cancel();
                    }
                });

        return uscitaCreazioneUtenteAlertDialogBuilder.create();
    }

}