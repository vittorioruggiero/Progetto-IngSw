package com.example.ratatouille23.UI.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.MenuRecyclerAdapter;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AggiungiProdottoFragment extends Fragment {

    private static final String TAG = "ONE";

    private ArrayList<SezioneMenu> sezioni = new ArrayList<>();

    private RecyclerView recyclerView;
    private MenuRecyclerAdapter menuRecyclerAdapter;

    private Spinner tipologiaProdottoSpinner;
    private EditText nomeProdottoEditText;
    private EditText nomeProdottoSecondaLinguaEditText;
    private EditText costoProdottoEditText;
    private EditText ingredientiProdottoEditText;
    private EditText ingredientiProdottoSecondaLinguaEditText;
    private Button aggiungiProdottoButton;
    private FragmentManager fragmentManager = getFragmentManager();

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_aggiungi_prodotto, container, false);

        //PersonalizzaMenuFragment fragment = (PersonalizzaMenuFragment)fragmentManager.findFragmentById(R.id.personalizzaMenuAdminFragment);

        nomeProdottoEditText = (EditText) v.findViewById(R.id.nomeProdottoEditText);
        nomeProdottoSecondaLinguaEditText = (EditText) v.findViewById(R.id.nomeProdottoSecondaLinguaEditText);
        costoProdottoEditText = (EditText) v.findViewById(R.id.costoProdottoEditText);
        ingredientiProdottoEditText = (EditText) v.findViewById(R.id.ingredientiProdottoEditText);
        ingredientiProdottoSecondaLinguaEditText = (EditText) v.findViewById(R.id.ingredientiProdottoSecondaLinguaEditText);
        tipologiaProdottoSpinner = (Spinner) v.findViewById(R.id.tipologiaProdottoSpinner);
        aggiungiProdottoButton = (Button) v.findViewById(R.id.aggiungiProdottoButton);


        aggiungiProdottoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeProdotto = nomeProdottoEditText.getText().toString();
                String ingredienti = ingredientiProdottoEditText.getText().toString();
                double costo = Double.parseDouble(costoProdottoEditText.getText().toString());

                //aggiungi prodotto
                ProdottoMenu nuovoProdotto = new ProdottoMenu(nomeProdotto, ingredienti, costo);

                //fragment.aggiungiProdotto(nuovoProdotto, 0);
                //sostituisciFragment(fragment);

            }
        });


        /*ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<SezioneMenu>(getActivity(),
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipologiaProdottoSpinner.setAdapter(adapter);*/

        return v;
    }

    public void sostituisciFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.hide(this);
        //transaction.show(fragment).commit();
    }
}