package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.ProdottoMenuOpenData;
import com.example.ratatouille23.entity.SezioneMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class AggiungiProdottoFragment extends Fragment {

    private Spinner tipologiaProdottoSpinner;
    private AutoCompleteTextView nomeProdottoAutoCompleteTextView;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText costoProdottoEditText;
    private EditText ingredientiProdottoEditText;
    private EditText ingredientiProdottoSecondaLinguaEditText;
    private EditText allergeniEditText;
    private Button aggiungiProdottoButton;
    private BottomNavigationView bottomNavigationView;
    private Controller controllerAdmin;
    private Controller controllerSupervisore;
    private static final String SEZIONI_AGGIUNGI_PRODOTTO = "sezioniAggiungiProdotto";
    private ArrayList<SezioneMenu> sezioni = new ArrayList<>();

    public AggiungiProdottoFragment() {
        // Required empty public constructor
    }

    public static AggiungiProdottoFragment newIstance(ArrayList<SezioneMenu> sezioni){

        AggiungiProdottoFragment fragment = new AggiungiProdottoFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String myJson = gson.toJson(sezioni);
        args.putString(SEZIONI_AGGIUNGI_PRODOTTO, myJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Gson gson = new Gson();
            sezioni = gson.fromJson(getArguments().getString(SEZIONI_AGGIUNGI_PRODOTTO), new TypeToken<ArrayList<SezioneMenu>>(){}.getType());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_aggiungi_prodotto, container, false);

        nomeProdottoAutoCompleteTextView = v.findViewById(R.id.nomeProdottoEditText);
        nomeProdottoSecondaLinguaEditText = v.findViewById(R.id.nomeProdottoSecondaLinguaEditText);
        costoProdottoEditText = v.findViewById(R.id.costoProdottoEditText);
        ingredientiProdottoEditText = v.findViewById(R.id.ingredientiProdottoEditText);
        ingredientiProdottoSecondaLinguaEditText = v.findViewById(R.id.ingredientiProdottoSecondaLinguaEditText);
        allergeniEditText = v.findViewById(R.id.allergeniEditText);
        tipologiaProdottoSpinner = v.findViewById(R.id.tipologiaProdottoSpinner);
        aggiungiProdottoButton = v.findViewById(R.id.aggiungiProdottoButton);

        if(getActivity().toString().contains("Admin")){
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
            controllerAdmin = new Controller(getActivity().toString());
        }else{
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
            controllerSupervisore = new Controller(getActivity().toString());
        }

        ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, sezioni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaProdottoSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapterTemp = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        nomeProdottoAutoCompleteTextView.setAdapter(adapterTemp);

        tipologiaProdottoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (getActivity().toString().contains("Admin")) {
                    controllerAdmin.getAllProdottiOpenData(AggiungiProdottoFragment.this, tipologiaProdottoSpinner.getSelectedItem().toString());

                } else {
                    controllerSupervisore.getAllProdottiOpenData(AggiungiProdottoFragment.this, tipologiaProdottoSpinner.getSelectedItem().toString());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*nomeProdottoAutoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProdottoMenuOpenData prodottoMenuOpenData = (ProdottoMenuOpenData) adapterView.getSelectedItem();
                Toast.makeText(getActivity(), "PROVA:" + prodottoMenuOpenData, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        nomeProdottoAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (getActivity().toString().contains("Admin")) {
                    controllerAdmin.checkProdottoOpenData(nomeProdottoAutoCompleteTextView.getText().toString(), AggiungiProdottoFragment.this);

                } else {
                    controllerSupervisore.checkProdottoOpenData(nomeProdottoAutoCompleteTextView.getText().toString(), AggiungiProdottoFragment.this);
                }
            }
        });



        aggiungiProdottoButton.setOnClickListener(view -> {
            String nomeProdotto = nomeProdottoAutoCompleteTextView.getText().toString();
            String nomeProdottoSecondaLingua = nomeProdottoSecondaLinguaEditText.getText().toString();
            String ingredienti = ingredientiProdottoEditText.getText().toString();
            String ingredientiSecondaLingua = ingredientiProdottoSecondaLinguaEditText.getText().toString();
            String allergeni = allergeniEditText.getText().toString();
            double costo;

            try {
                costo = Double.parseDouble(costoProdottoEditText.getText().toString());
            }catch(NumberFormatException e){
                costo = 0;
            }

            controlloProdotto(nomeProdotto, nomeProdottoSecondaLingua, ingredienti, ingredientiSecondaLingua, allergeni, costo);

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

    private void controlloProdotto(String nomeProdotto, String nomeProdottoSecondaLingua, String ingredienti, String ingredientiSecondaLingua, String allergeni, double costo) {

        ProdottoMenu nuovoProdotto;

        if(!(nomeProdotto.equals("")) && !(ingredienti.equals("")) && costo != 0){
            if(nomeProdottoSecondaLingua.equals("") && ingredientiSecondaLingua.equals("")){
                if(allergeni.equals("")){
                    nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo);
                    try {
                        if(getActivity().toString().contains("Admin")){
                            controllerAdmin.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
                        }else{
                            controllerSupervisore.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
                        }
                    }catch(IndexOutOfBoundsException e){
                        Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo, allergeni);
                    try {
                        if(getActivity().toString().contains("Admin")){
                            controllerAdmin.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
                        }else{
                            controllerSupervisore.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
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
                            controllerAdmin.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
                        }else{
                            controllerSupervisore.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
                        }                    }catch(IndexOutOfBoundsException e){
                        Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    nuovoProdotto = new ProdottoMenu(nomeProdotto, nomeProdottoSecondaLingua, ingredienti, ingredientiSecondaLingua, costo, allergeni);
                    try {
                        if(getActivity().toString().contains("Admin")){
                            controllerAdmin.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
                        }else{
                            controllerSupervisore.aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItem().toString(), getActivity(), this);
                        }                    }catch(IndexOutOfBoundsException e){
                        Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }else{
            Toast.makeText(getActivity(), "Compilare i campi correttamente", Toast.LENGTH_SHORT).show();
        }

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

    public AutoCompleteTextView getAutoCompleteTextView(){
        return nomeProdottoAutoCompleteTextView;
    }


    public void setEditText(String ingredienti, String allergeni) {

        if(ingredienti != null){
            ingredientiProdottoEditText.setText(ingredienti);
        }
        if(allergeni != null){
            allergeniEditText.setText(allergeni);
        }


    }
}