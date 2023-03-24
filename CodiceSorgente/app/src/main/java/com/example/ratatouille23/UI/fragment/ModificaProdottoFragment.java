package com.example.ratatouille23.UI.fragment;

//import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.retrofit.API.ProdottoMenuAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModificaProdottoFragment extends Fragment {

    private Button salvaModificheButton, eliminaProdottoButton;
    private EditText nomeProdottoEditText;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText ingredientiEditText;
    private EditText allergeniEditText;
    private EditText ingredientiSecondaLinguaEditText;
    private EditText costoEditText;
    private Spinner tipologiaProdottoModificaSpinner;
    private BottomNavigationView bottomNavigationView;
    private static final String NOME_PRODOTTO = "nomeProdotto";
    private static final String NOME_PRODOTTO_SECONDA_LINGUA = "nomeProdottoSecondaLingua";
    private static final String INGREDIENTI = "ingredienti";
    private static final String INGREDIENTI_SECONDA_LINGUA = "ingredientiSecondaLingua";
    private static final String ALLERGENI = "allergeni";
    private static final String PREZZO = "prezzo";
    private static final String POSIZIONE = "posizione";
    private static final String POSIZIONE_SEZIONE = "posizione_sezione";
    private static final String SEZIONI_MODIFICA_PRODOTTO = "sezioniModificaProdotto";
    private String nomeProdottoOriginale;
    private String nomeProdottoSecondaLingua;
    private String ingredienti;
    private String ingredientiSecondaLingua;
    private double prezzo;
    private String allergeni;
    private int posizione;
    private int posizioneSezione;
    private Controller controllerAdmin;
    private Controller controllerSupervisore;
    private ArrayList<SezioneMenu> sezioni;

    public ModificaProdottoFragment() {
        // Required empty public constructor
    }

    public static ModificaProdottoFragment newInstance(String nomeProdotto, String nomeProdottoSecondaLingua,
                                                       String ingredienti, String ingredientiSecondaLingua,
                                                       double prezzo, int posizione, int posizioneSezione,
                                                       ArrayList<SezioneMenu> sezioni, String allergeni){
        ModificaProdottoFragment fragment = new ModificaProdottoFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        args.putString(NOME_PRODOTTO, nomeProdotto);
        args.putString(NOME_PRODOTTO_SECONDA_LINGUA, nomeProdottoSecondaLingua);
        args.putString(INGREDIENTI, ingredienti);
        args.putString(INGREDIENTI_SECONDA_LINGUA, ingredientiSecondaLingua);
        args.putString(ALLERGENI, allergeni);
        args.putDouble(PREZZO, prezzo);
        args.putInt(POSIZIONE, posizione);
        args.putInt(POSIZIONE_SEZIONE, posizioneSezione);
        String myJson = gson.toJson(sezioni);
        args.putString(SEZIONI_MODIFICA_PRODOTTO, myJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Gson gson = new Gson();
            nomeProdottoOriginale = getArguments().getString(NOME_PRODOTTO);
            nomeProdottoSecondaLingua = getArguments().getString(NOME_PRODOTTO_SECONDA_LINGUA);
            ingredienti = getArguments().getString(INGREDIENTI);
            ingredientiSecondaLingua = getArguments().getString(INGREDIENTI_SECONDA_LINGUA);
            prezzo = getArguments().getDouble(PREZZO);
            allergeni = getArguments().getString(ALLERGENI);
            posizione = getArguments().getInt(POSIZIONE);
            posizioneSezione = getArguments().getInt(POSIZIONE_SEZIONE);
            sezioni = gson.fromJson(getArguments().getString(SEZIONI_MODIFICA_PRODOTTO), new TypeToken<ArrayList<SezioneMenu>>(){}.getType());

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modifica_prodotto, container, false);

        salvaModificheButton = v.findViewById(R.id.salvaModificheButton);
        nomeProdottoEditText = v.findViewById(R.id.nomeProdottoModificaEditText);
        nomeProdottoSecondaLinguaEditText = v.findViewById(R.id.nomeProdottoSecondaLinguaModificaEditText);
        ingredientiEditText = v.findViewById(R.id.ingredientiModificaEditText);
        ingredientiSecondaLinguaEditText = v.findViewById(R.id.ingredientiSecondaLinguaModificaEditText);
        allergeniEditText = v.findViewById(R.id.allergeniModificaEditText);
        costoEditText = v.findViewById(R.id.prezzoModificaEditText);
        tipologiaProdottoModificaSpinner = v.findViewById(R.id.tipologiaProdottoModificaSpinner);
        eliminaProdottoButton = v.findViewById(R.id.eliminaProdottoButton);

        if(getActivity().toString().contains("Admin")){
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
            controllerAdmin = new Controller(getActivity().toString());
        }else{
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
            controllerSupervisore = new Controller(getActivity().toString());
        }

        nomeProdottoEditText.setText(nomeProdottoOriginale);
        if(nomeProdottoSecondaLingua != null)
            nomeProdottoSecondaLinguaEditText.setText(nomeProdottoSecondaLingua);
        ingredientiEditText.setText(ingredienti);
        if(ingredientiSecondaLingua != null)
            ingredientiSecondaLinguaEditText.setText(ingredientiSecondaLingua);
        costoEditText.setText(String.valueOf(prezzo));
        if(allergeni != null)
            allergeniEditText.setText(allergeni);

        ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, sezioni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaProdottoModificaSpinner.setAdapter(adapter);

        salvaModificheButton.setOnClickListener(view -> {

            String nomeProdotto = nomeProdottoEditText.getText().toString();
            String nomeProdottoSecondaLingua = nomeProdottoSecondaLinguaEditText.getText().toString();
            String ingredienti = ingredientiEditText.getText().toString();
            String ingredientiSecondaLingua = ingredientiSecondaLinguaEditText.getText().toString();
            String allergeni = allergeniEditText.getText().toString();
            double costo;

            try {
                costo = Double.parseDouble(costoEditText.getText().toString());
            }catch(NumberFormatException e){
                costo = 0;
            }
            ProdottoMenu nuovoProdotto;

            if(!(nomeProdotto.equals("")) && !(ingredienti.equals("")) && costo != 0){
                if(nomeProdottoSecondaLingua.equals("") && ingredientiSecondaLingua.equals("")){
                    if(allergeni.equals("")){
                        nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo);
                        try {
                            if(getActivity().toString().contains("Admin")){
                                controllerAdmin.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }else{
                                controllerSupervisore.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }
                        }catch(IndexOutOfBoundsException e){
                            Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo, allergeni);
                        try {
                            if(getActivity().toString().contains("Admin")){
                                controllerAdmin.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }else{
                                controllerSupervisore.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }
                        }catch(IndexOutOfBoundsException e){
                            Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else if((nomeProdottoSecondaLingua.equals("") || ingredientiSecondaLingua.equals(""))){
                    Toast.makeText(getActivity(), "Devi inserire entrambi i campi della seconda lingua", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(allergeni.equals("")){
                        nuovoProdotto = new ProdottoMenu(nomeProdotto, nomeProdottoSecondaLingua, ingredienti, ingredientiSecondaLingua, costo);
                        try {
                            if(getActivity().toString().contains("Admin")){
                                controllerAdmin.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }else{
                                controllerSupervisore.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }
                        }catch(IndexOutOfBoundsException e){
                            Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        nuovoProdotto = new ProdottoMenu(nomeProdotto, nomeProdottoSecondaLingua, ingredienti, ingredientiSecondaLingua, costo, allergeni);
                        try {
                            if(getActivity().toString().contains("Admin")){
                                controllerAdmin.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }else{
                                controllerSupervisore.eliminaEdAggiungiProdotto(nuovoProdotto, tipologiaProdottoModificaSpinner.getSelectedItem().toString(), nomeProdottoOriginale,
                                        getActivity(), this);
                            }
                        }catch(IndexOutOfBoundsException e){
                            Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }else{
                Toast.makeText(getActivity(), "Compilare i campi correttamente", Toast.LENGTH_SHORT).show();
            }

        });

        eliminaProdottoButton.setOnClickListener(view ->{

            if(getActivity().toString().contains("Admin")){
                controllerAdmin.eliminaProdotto(nomeProdottoOriginale, getActivity(), this);
            }else{
                controllerSupervisore.eliminaProdotto(nomeProdottoOriginale, getActivity(), this);
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
        transaction.setReorderingAllowed(true);
        transaction.commit();

        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}