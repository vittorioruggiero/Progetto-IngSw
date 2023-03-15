package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.retrofit.API.SezioneMenuAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AggiungiSezioneFragment extends Fragment {

    private EditText nomeSezioneEditText;
    private Button salvaSezioneButton;
    private BottomNavigationView bottomNavigationView;
    private RetrofitService retrofitService;
    private SezioneMenuAPI sezioneMenuAPI;
    private Amministratore admin = getAdmin();


    public AggiungiSezioneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_aggiungi_sezione, container, false);

        nomeSezioneEditText = v.findViewById(R.id.nuovaSezioneEditText);
        salvaSezioneButton = v.findViewById(R.id.eliminaSezioneButton);
        retrofitService = new RetrofitService();
        sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);

        if(getActivity().toString().contains("Admin"))
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
        else {
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
        }

        salvaSezioneButton.setOnClickListener(view -> {
            String nomeSezione = nomeSezioneEditText.getText().toString();
            if(nomeSezione.equals("")){
                Toast.makeText(getActivity(), "Inserisci il nome della sezione", Toast.LENGTH_SHORT).show();
            }else{
                SezioneMenu nuovaSezione = new SezioneMenu(nomeSezione);
                nuovaSezione.setNomeAttivita(admin.getNomeAttivita());
                nuovaSezione.setIndirizzoAttivita(admin.getIndirizzoAttivita());
                addSezione(nuovaSezione);
            }

        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                sostituisciFragment();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return v;

    }

    private void addSezione(SezioneMenu nuovaSezione) {

        sezioneMenuAPI.save(nuovaSezione)
                .enqueue(new Callback<SezioneMenu>() {
                    @Override
                    public void onResponse(Call<SezioneMenu> call, Response<SezioneMenu> response) {
                        if(response.body() != null){
                            Toast.makeText(getActivity(), "Sezione Salvata Correttamente", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                        sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<SezioneMenu> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(getActivity(), "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void sostituisciFragment(){
        FragmentTransaction transaction = null;
        if (getFragmentManager() != null) {
            transaction = getFragmentManager().beginTransaction();
        }
        try {
            transaction.remove(this);
        }catch(NullPointerException e){
            transaction.commit();
        }
        transaction.addToBackStack(null);
        if(getActivity().toString().contains("Admin")){
            transaction.replace(R.id.adminFragmentContainerView, PersonalizzaMenuFragment.class, null);
        }
        else if(getActivity().toString().contains("Supervisore")){
            transaction.replace(R.id.supervisoreFragmentContainerView, PersonalizzaMenuFragment.class, null);
        }
        transaction.commit();

        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}