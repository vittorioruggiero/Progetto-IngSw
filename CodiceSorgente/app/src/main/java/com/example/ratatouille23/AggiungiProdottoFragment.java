package com.example.ratatouille23;

import android.content.Intent;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AggiungiProdottoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AggiungiProdottoFragment extends Fragment {

    Spinner tipologiaProdottoSpinner;
    EditText nomeProdottoEditText, nomeProdottoSecondaLinguaEditText, costoProdottoEditText, ingredientiProdottoEditText, ingredientiProdottoSecondaLinguaEditText;
    Button aggiungiProdottoButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AggiungiProdottoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AggiungiProdottoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AggiungiProdottoFragment newInstance(String param1, String param2) {
        AggiungiProdottoFragment fragment = new AggiungiProdottoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_aggiungi_prodotto, container, false);

        nomeProdottoEditText = (EditText) v.findViewById(R.id.nomeProdottoEditText);
        nomeProdottoSecondaLinguaEditText = (EditText) v.findViewById(R.id.nomeProdottoSecondaLinguaEditText);
        costoProdottoEditText = (EditText) v.findViewById(R.id.costoProdottoEditText);
        ingredientiProdottoEditText = (EditText) v.findViewById(R.id.ingredientiProdottoEditText);
        ingredientiProdottoSecondaLinguaEditText = (EditText) v.findViewById(R.id.ingredientiProdottoSecondaLinguaEditText);
        tipologiaProdottoSpinner = (Spinner) v.findViewById(R.id.tipologiaProdottoSpinner);
        aggiungiProdottoButton = (Button) v.findViewById(R.id.aggiungiProdottoButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tipologia_prodotto_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaProdottoSpinner.setAdapter(adapter);

        tipologiaProdottoSpinner.setSelection(adapter.getPosition("Pizze Classiche"));

        aggiungiProdottoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //salva prodotto
                Fragment fragment = null;
                fragment = new PersonalizzaMenuFragment();
                sostituisciFragment(fragment);
            }
        });

        return v;
    }

    public void sostituisciFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.adminFragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}