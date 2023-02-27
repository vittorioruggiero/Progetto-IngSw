package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.OrdinazioniFragment.aggiungiProdottoOrdinazione;
import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.SingoloOrdine;

import java.util.ArrayList;

public class VisualizzaProdottoFragment extends Fragment {

    private Button aggiungiProdottoButton, indietroButton;
    private EditText nomeProdottoEditText;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText ingredientiEditText;
    private EditText ingredientiSecondaLinguaEditText;
    private EditText costoEditText;
    private EditText quantitaEditText;
    private static final String NOME_PRODOTTO = "nomeProdotto";
    private static final String NOME_PRODOTTO_SECONDA_LINGUA = "nomeProdottoSecondaLingua";
    private static final String INGREDIENTI = "ingredienti";
    private static final String INGREDIENTI_SECONDA_LINGUA = "ingredientiSecondaLingua";
    private static final String PREZZO = "prezzo";
    private static final String POSIZIONE = "posizione";
    private static final String POSIZIONE_SEZIONE = "posizione_sezione";
    private String nomeProdotto;
    private String nomeProdottoSecondaLingua;
    private String ingredienti;
    private String ingredientiSecondaLingua;
    private double prezzo;
    private int quantita;
    private int posizione;
    private int posizioneSezione;
    private SingoloOrdine singoloOrdine;


    public VisualizzaProdottoFragment() {
        // Required empty public constructor
    }

    public static VisualizzaProdottoFragment newInstance(String nomeProdotto, String nomeProdottoSecondaLingua,
                                                         String ingredienti, String ingredientiSecondaLingua, double prezzo, int posizione, int posizioneSezione) {
        VisualizzaProdottoFragment fragment = new VisualizzaProdottoFragment();
        Bundle args = new Bundle();
        args.putString(NOME_PRODOTTO, nomeProdotto);
        args.putString(NOME_PRODOTTO_SECONDA_LINGUA, nomeProdottoSecondaLingua);
        args.putString(INGREDIENTI, ingredienti);
        args.putString(INGREDIENTI_SECONDA_LINGUA, ingredientiSecondaLingua);
        args.putDouble(PREZZO, prezzo);
        args.putInt(POSIZIONE, posizione);
        args.putInt(POSIZIONE_SEZIONE, posizioneSezione);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nomeProdotto = getArguments().getString(NOME_PRODOTTO);
            nomeProdottoSecondaLingua = getArguments().getString(NOME_PRODOTTO_SECONDA_LINGUA);
            ingredienti = getArguments().getString(INGREDIENTI);
            ingredientiSecondaLingua = getArguments().getString(INGREDIENTI_SECONDA_LINGUA);
            prezzo = getArguments().getDouble(PREZZO);
            posizione = getArguments().getInt(POSIZIONE);
            posizioneSezione = getArguments().getInt(POSIZIONE_SEZIONE);
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

        ArrayList<SezioneMenu> sezioni = getSezioni();

        nomeProdottoEditText.setText(nomeProdotto);
        if(nomeProdottoSecondaLingua != null)
            nomeProdottoSecondaLinguaEditText.setText(nomeProdottoSecondaLingua);
        if(ingredienti != null)
            ingredientiEditText.setText(ingredienti);
        if(ingredientiSecondaLingua != null)
            ingredientiSecondaLinguaEditText.setText(ingredientiSecondaLingua);
        costoEditText.setText(String.valueOf(prezzo));

        aggiungiProdottoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantita = Integer.parseInt(quantitaEditText.getText().toString());
                singoloOrdine = new SingoloOrdine(sezioni.get(posizioneSezione).getProdottiMenu().get(posizione), quantita);
                aggiungiProdottoOrdinazione(singoloOrdine);
                sostituisciFragment();
            }
        });

        indietroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sostituisciFragment();
            }
        });

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
        transaction.replace(R.id.addettoSalaFragmentContainerView, OrdinazioniFragment.class, null);
        transaction.commit();
    }
}