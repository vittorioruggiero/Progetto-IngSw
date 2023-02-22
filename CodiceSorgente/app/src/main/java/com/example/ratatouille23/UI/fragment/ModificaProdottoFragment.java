package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.aggiungiProdotto;
import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.eliminaProdotto;
import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;
import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getTagFragment;
import static com.example.ratatouille23.adapter.ProdottiAdapter.getBundle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.ProdottiAdapter;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;

import java.util.ArrayList;

public class ModificaProdottoFragment extends Fragment {

    private Button salvaModificheButton;
    private EditText nomeProdottoEditText;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText ingredientiEditText;
    private EditText ingredientiSecondaLinguaEditText;
    private EditText costoEditText;
    private Spinner tipologiaProdottoModificaSpinner;
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
    private int posizione;
    private int posizioneSezione;

    public ModificaProdottoFragment() {
        // Required empty public constructor
    }

    public static ModificaProdottoFragment newInstance(String nomeProdotto, String nomeProdottoSecondaLingua,
                                                       String ingredienti, String ingredientiSecondaLingua, double prezzo, int posizione, int posizioneSezione){
        ModificaProdottoFragment fragment = new ModificaProdottoFragment();
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
        if(getArguments() != null){
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modifica_prodotto, container, false);

        salvaModificheButton = v.findViewById(R.id.salvaModificheButton);
        nomeProdottoEditText = v.findViewById(R.id.nomeProdottoModificaEditText);
        nomeProdottoSecondaLinguaEditText = v.findViewById(R.id.nomeProdottoSecondaLinguaModificaEditText);
        ingredientiEditText = v.findViewById(R.id.ingredientiModificaEditText);
        ingredientiSecondaLinguaEditText = v.findViewById(R.id.ingredientiSecondaLinguaModificaEditText);
        costoEditText = v.findViewById(R.id.prezzoModificaEditText);
        tipologiaProdottoModificaSpinner = v.findViewById(R.id.tipologiaProdottoModificaSpinner);

        nomeProdottoEditText.setText(nomeProdotto);
        if(nomeProdottoSecondaLingua != null)
            nomeProdottoSecondaLinguaEditText.setText(nomeProdottoSecondaLingua);
        if(ingredienti != null)
            ingredientiEditText.setText(ingredienti);
        if(ingredientiSecondaLingua != null)
            ingredientiSecondaLinguaEditText.setText(ingredientiSecondaLingua);
        costoEditText.setText(String.valueOf(prezzo));

        ArrayList<SezioneMenu> sezioni = getSezioni();

        ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, sezioni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaProdottoModificaSpinner.setAdapter(adapter);

        salvaModificheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eliminaProdotto(posizione, posizioneSezione);
                aggiungiProdotto(new ProdottoMenu(nomeProdottoEditText.getText().toString(), ingredientiEditText.getText().toString(),
                        Double.parseDouble(costoEditText.getText().toString())), tipologiaProdottoModificaSpinner.getSelectedItemPosition());

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
        transaction.replace(R.id.adminFragmentContainerView, PersonalizzaMenuFragment.class, null);
        transaction.commit();
    }
}