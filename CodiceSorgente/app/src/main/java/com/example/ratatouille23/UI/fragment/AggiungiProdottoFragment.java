package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.aggiungiProdotto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.ProdottoMenu;

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


        aggiungiProdottoButton.setOnClickListener(view -> {
            String nomeProdotto = nomeProdottoEditText.getText().toString();
            String ingredienti = ingredientiProdottoEditText.getText().toString();
            double costo = Double.parseDouble(costoProdottoEditText.getText().toString());

            //aggiungi prodotto
            ProdottoMenu nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo);

            try {
                aggiungiProdotto(nuovoProdotto, 0);
            }catch(IndexOutOfBoundsException e){
                Toast.makeText(getActivity(), "Non ci sono sezioni!", Toast.LENGTH_SHORT).show();
            }
            sostituisciFragment();

        });


        /*ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<SezioneMenu>(getActivity(),
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipologiaProdottoSpinner.setAdapter(adapter);*/

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