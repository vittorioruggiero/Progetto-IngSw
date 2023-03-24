package com.example.ratatouille23.UI.fragment;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.SingoloOrdine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class VisualizzaProdottoFragment extends Fragment {

    private Button aggiungiProdottoButton, indietroButton;
    private EditText nomeProdottoEditText;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText ingredientiEditText;
    private EditText ingredientiSecondaLinguaEditText;
    private EditText costoEditText;
    private EditText allergeniEditText;
    private EditText quantitaEditText;
    private static final String NOME_PRODOTTO = "nomeProdotto";
    private static final String NOME_PRODOTTO_SECONDA_LINGUA = "nomeProdottoSecondaLingua";
    private static final String INGREDIENTI = "ingredienti";
    private static final String INGREDIENTI_SECONDA_LINGUA = "ingredientiSecondaLingua";
    private static final String ALLERGENI = "allergeni";
    private static final String PREZZO = "prezzo";
    private static final String POSIZIONE = "posizione";
    private static final String POSIZIONE_SEZIONE = "posizione_sezione";
    private static final String SEZIONI_VISUALIZZA_PRODOTTO = "sezioni-visualizza-prodotto";
    private static final String TAVOLO_VISUALIZZA_PRODOTTO = "tavolo-visualizza-prodotto";
    private static final String COMMENSALI_VISUALIZZA_PRODOTTO = "commensali-visualizza-prodotto";
    private static final String PRODOTTI_VISUALIZZA_PRODOTTO = "prodotti-visualizza-prodotto";
    private String nomeProdotto;
    private String nomeProdottoSecondaLingua;
    private String ingredienti;
    private String ingredientiSecondaLingua;
    private String allergeni;
    private double prezzo;
    private int quantita;
    private int posizione;
    private int posizioneSezione;
    private SingoloOrdine singoloOrdine;
    private int tavolo;
    private int commensali;
    private ArrayList<SezioneMenu> sezioni = new ArrayList<>();
    private List<SingoloOrdine> prodottiOrdine = new ArrayList<>();


    public VisualizzaProdottoFragment() {
        // Required empty public constructor
    }

    public static VisualizzaProdottoFragment newInstance(String nomeProdotto, String nomeProdottoSecondaLingua,
                                                         String ingredienti, String ingredientiSecondaLingua,
                                                         double prezzo, int posizione, int posizioneSezione,
                                                         ArrayList<SezioneMenu> sezioniMenu, int tavolo, int commensali, List<SingoloOrdine> prodottiOrdine, String allergeni) {
        VisualizzaProdottoFragment fragment = new VisualizzaProdottoFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        args.putString(NOME_PRODOTTO, nomeProdotto);
        args.putString(NOME_PRODOTTO_SECONDA_LINGUA, nomeProdottoSecondaLingua);
        args.putString(INGREDIENTI, ingredienti);
        args.putString(INGREDIENTI_SECONDA_LINGUA, ingredientiSecondaLingua);
        args.putDouble(PREZZO, prezzo);
        args.putInt(POSIZIONE, posizione);
        args.putInt(POSIZIONE_SEZIONE, posizioneSezione);
        args.putInt(TAVOLO_VISUALIZZA_PRODOTTO, tavolo);
        args.putInt(COMMENSALI_VISUALIZZA_PRODOTTO, commensali);
        args.putString(ALLERGENI, allergeni);
        String myJsonSezioni = gson.toJson(sezioniMenu);
        String myJsonProdotti = gson.toJson(prodottiOrdine);
        args.putString(SEZIONI_VISUALIZZA_PRODOTTO, myJsonSezioni);
        args.putString(PRODOTTI_VISUALIZZA_PRODOTTO, myJsonProdotti);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Gson gson = new Gson();
            nomeProdotto = getArguments().getString(NOME_PRODOTTO);
            nomeProdottoSecondaLingua = getArguments().getString(NOME_PRODOTTO_SECONDA_LINGUA);
            ingredienti = getArguments().getString(INGREDIENTI);
            ingredientiSecondaLingua = getArguments().getString(INGREDIENTI_SECONDA_LINGUA);
            prezzo = getArguments().getDouble(PREZZO);
            posizione = getArguments().getInt(POSIZIONE);
            posizioneSezione = getArguments().getInt(POSIZIONE_SEZIONE);
            tavolo = getArguments().getInt(TAVOLO_VISUALIZZA_PRODOTTO);
            commensali = getArguments().getInt(COMMENSALI_VISUALIZZA_PRODOTTO);
            allergeni = getArguments().getString(ALLERGENI);
            sezioni = gson.fromJson(getArguments().getString(SEZIONI_VISUALIZZA_PRODOTTO), new TypeToken<ArrayList<SezioneMenu>>(){}.getType());
            prodottiOrdine = gson.fromJson(getArguments().getString(PRODOTTI_VISUALIZZA_PRODOTTO), new TypeToken<List<SingoloOrdine>>(){}.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_visualizza_prodotto, container, false);

        aggiungiProdottoButton = v.findViewById(R.id.aggiungiProdottoButton);
        nomeProdottoEditText = v.findViewById(R.id.nomeProdottoVisualizzaEditText);
        nomeProdottoSecondaLinguaEditText = v.findViewById(R.id.nomeProdottoSecondaLinguaVisualizzaEditText);
        ingredientiEditText = v.findViewById(R.id.ingredientiVisualizzaEditText);
        ingredientiSecondaLinguaEditText = v.findViewById(R.id.ingredientiSecondaLinguaVisualizzaEditText);
        costoEditText = v.findViewById(R.id.prezzoVisualizzaEditText);
        quantitaEditText = v.findViewById(R.id.quantitaVisualizzaEditText);
        indietroButton = v.findViewById(R.id.indietroButton);
        allergeniEditText = v.findViewById(R.id.allergeniVisualizzaEditText);

        //ArrayList<SezioneMenu> sezioni = getSezioni();

        nomeProdottoEditText.setText(nomeProdotto);
        if(nomeProdottoSecondaLingua != null)
            nomeProdottoSecondaLinguaEditText.setText(nomeProdottoSecondaLingua);
        if(ingredienti != null)
            ingredientiEditText.setText(ingredienti);
        if(ingredientiSecondaLingua != null)
            ingredientiSecondaLinguaEditText.setText(ingredientiSecondaLingua);
        costoEditText.setText(String.valueOf(prezzo));
        if(allergeni != null)
            allergeniEditText.setText(allergeni);

        aggiungiProdottoButton.setOnClickListener(view -> {
            try {
                quantita = Integer.parseInt(quantitaEditText.getText().toString());
                singoloOrdine = new SingoloOrdine(sezioni.get(posizioneSezione).getProdottiMenu().get(posizione), sezioni.get(posizioneSezione).getProdottiMenu().get(posizione).getNomeProdotto(), quantita);
                aggiungiProdottoOrdinazione(singoloOrdine);
                Fragment fragment = VisualizzaMenuFragment.newInstance(tavolo, commensali, prodottiOrdine);
                sostituisciFragmentSecond(fragment);
            }catch(NumberFormatException e){
                Toast.makeText(getActivity(), "Inserire una quantità valida", Toast.LENGTH_SHORT).show();
            }

        });

        indietroButton.setOnClickListener(view -> {
            Fragment fragment = VisualizzaMenuFragment.newInstance(tavolo, commensali, prodottiOrdine);
            sostituisciFragmentSecond(fragment);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment fragment = VisualizzaMenuFragment.newInstance(tavolo, commensali, prodottiOrdine);
                sostituisciFragmentSecond(fragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return v;
    }

    public void sostituisciFragmentSecond(Fragment fragment){
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
        transaction.replace(R.id.addettoSalaFragmentContainerView, fragment);
        transaction.commit();
    }

    public void aggiungiProdottoOrdinazione(SingoloOrdine ordine){
        prodottiOrdine.add(ordine);
        //prodottiOrdinazioneAdapter.notifyDataSetChanged();
    }
}