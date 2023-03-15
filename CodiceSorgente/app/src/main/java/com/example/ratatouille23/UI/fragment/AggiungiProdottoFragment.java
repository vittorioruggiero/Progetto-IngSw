package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.retrofit.API.ProdottoMenuAPI;
import com.example.ratatouille23.retrofit.API.SezioneMenuAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AggiungiProdottoFragment extends Fragment {

    private Spinner tipologiaProdottoSpinner;
    private EditText nomeProdottoEditText;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText costoProdottoEditText;
    private EditText ingredientiProdottoEditText;
    private EditText ingredientiProdottoSecondaLinguaEditText;
    private Button aggiungiProdottoButton;
    private BottomNavigationView bottomNavigationView;
    private RetrofitService retrofitService;
    private ProdottoMenuAPI prodottoMenuAPI;

    public AggiungiProdottoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_aggiungi_prodotto, container, false);

        nomeProdottoEditText = v.findViewById(R.id.nomeProdottoEditText);
        nomeProdottoSecondaLinguaEditText = v.findViewById(R.id.nomeProdottoSecondaLinguaEditText);
        costoProdottoEditText = v.findViewById(R.id.costoProdottoEditText);
        ingredientiProdottoEditText = v.findViewById(R.id.ingredientiProdottoEditText);
        ingredientiProdottoSecondaLinguaEditText = v.findViewById(R.id.ingredientiProdottoSecondaLinguaEditText);
        tipologiaProdottoSpinner = v.findViewById(R.id.tipologiaProdottoSpinner);
        aggiungiProdottoButton = v.findViewById(R.id.aggiungiProdottoButton);

        retrofitService = new RetrofitService();
        prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);

        if(getActivity().toString().contains("Admin"))
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
        else {
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
        }

        ArrayList<SezioneMenu> sezioni = getSezioni();

        ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, sezioni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaProdottoSpinner.setAdapter(adapter);


        aggiungiProdottoButton.setOnClickListener(view -> {
            String nomeProdotto = nomeProdottoEditText.getText().toString();
            String nomeProdottoSecondaLingua = nomeProdottoSecondaLinguaEditText.getText().toString();
            String ingredienti = ingredientiProdottoEditText.getText().toString();
            String ingredientiSecondaLingua = ingredientiProdottoSecondaLinguaEditText.getText().toString();
            double costo;

            try {
                costo = Double.parseDouble(costoProdottoEditText.getText().toString());
            }catch(NumberFormatException e){
                costo = 0;
            }
            ProdottoMenu nuovoProdotto;

            if(!(nomeProdotto.equals("")) && !(ingredienti.equals("")) && costo != 0){
                if(nomeProdottoSecondaLingua.equals("") && ingredientiSecondaLingua.equals("")){
                    nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo);
                    try {
                        aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString());
                    }catch(IndexOutOfBoundsException e){
                        Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if((nomeProdottoSecondaLingua.equals("") || ingredientiSecondaLingua.equals(""))){
                    Toast.makeText(getActivity(), "Devi inserire entrambi i campi della seconda lingua", Toast.LENGTH_SHORT).show();
                }
                else{
                    nuovoProdotto = new ProdottoMenu(nomeProdotto, nomeProdottoSecondaLingua, ingredienti, ingredientiSecondaLingua, costo);
                    try {
                        aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString());
                    }catch(IndexOutOfBoundsException e){
                        Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                Toast.makeText(getActivity(), "Compilare i campi correttamente", Toast.LENGTH_SHORT).show();
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

    private void aggiungiProdotto(ProdottoMenu nuovoProdotto, String nomeSezione) {

        nuovoProdotto.setNomeSezione(nomeSezione);
        prodottoMenuAPI.save(nuovoProdotto)
                .enqueue(new Callback<ProdottoMenu>() {
                    @Override
                    public void onResponse(Call<ProdottoMenu> call, Response<ProdottoMenu> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            Toast.makeText(getActivity(), "Prodotto aggiunto correttamente", Toast.LENGTH_SHORT).show();
                        }
                        sostituisciFragment();
                    }

                    @Override
                    public void onFailure(Call<ProdottoMenu> call, Throwable t) {
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