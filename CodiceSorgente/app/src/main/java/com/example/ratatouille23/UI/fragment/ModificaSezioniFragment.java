package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.eliminaSezione;
import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.SezioneMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ModificaSezioniFragment extends Fragment {

    private Spinner sezioniSpinner;
    private Button eliminaSezioneButtom;

    public ModificaSezioniFragment() {
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
        View v = inflater.inflate(R.layout.fragment_modifica_sezioni, container, false);

        sezioniSpinner = v.findViewById(R.id.sezioniSpinner);
        eliminaSezioneButtom = v.findViewById(R.id.eliminaSezioneButton);

        ArrayList<SezioneMenu> sezioni = getSezioni();

        ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, sezioni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sezioniSpinner.setAdapter(adapter);

        eliminaSezioneButtom.setOnClickListener(view -> {
            try {
                eliminaSezione(sezioniSpinner.getSelectedItemPosition());
                sostituisciFragment();
            }catch(ArrayIndexOutOfBoundsException e){
                Toast.makeText(getActivity(), "Non ci sono sezioni da eliminare", Toast.LENGTH_SHORT).show();
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