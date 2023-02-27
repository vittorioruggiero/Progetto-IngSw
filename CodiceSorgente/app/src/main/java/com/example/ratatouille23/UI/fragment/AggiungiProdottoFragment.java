package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.aggiungiProdotto;
import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;

import android.os.Bundle;

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
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;

import java.util.ArrayList;

public class AggiungiProdottoFragment extends Fragment {

    private Spinner tipologiaProdottoSpinner;
    private EditText nomeProdottoEditText;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText costoProdottoEditText;
    private EditText ingredientiProdottoEditText;
    private EditText ingredientiProdottoSecondaLinguaEditText;
    private Button aggiungiProdottoButton;

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
            ProdottoMenu nuovoProdotto = null;

            if(nomeProdotto.equals("") || ingredienti.equals("") || costo == 0){
                if(tipologiaProdottoSpinner.getSelectedItem() != null){
                    if(!(tipologiaProdottoSpinner.getSelectedItem().toString().equals("Bibite")))
                        Toast.makeText(getActivity(), "Riempire i campi obbligatori ", Toast.LENGTH_SHORT).show();
                    else{
                        if((nomeProdottoSecondaLingua.equals(""))){
                            nuovoProdotto = new ProdottoMenu(nomeProdotto, costo);
                            try {
                                aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItemPosition());
                                sostituisciFragment();
                            }catch(IndexOutOfBoundsException e){
                                Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            nuovoProdotto = new ProdottoMenu(nomeProdotto, costo, nomeProdottoSecondaLingua);
                            try {
                                aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItemPosition());
                                sostituisciFragment();
                            }catch(IndexOutOfBoundsException e){
                                Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                }
            }else{
                if(nomeProdottoSecondaLingua.equals("") && ingredientiSecondaLingua.equals("")){
                    nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo);
                    try {
                        aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItemPosition());
                        sostituisciFragment();
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
                        aggiungiProdotto(nuovoProdotto, tipologiaProdottoSpinner.getSelectedItemPosition());
                        sostituisciFragment();
                    }catch(IndexOutOfBoundsException e){
                        Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
                    }
                }
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
        if(getActivity().toString().contains("Admin")){
            transaction.replace(R.id.adminFragmentContainerView, PersonalizzaMenuFragment.class, null);
        }
        else if(getActivity().toString().contains("Supervisore")){
            transaction.replace(R.id.supervisoreFragmentContainerView, PersonalizzaMenuFragment.class, null);
        }
        transaction.commit();
    }

}