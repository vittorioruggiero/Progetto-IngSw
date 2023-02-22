package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.aggiungiSezione;

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

import java.util.ArrayList;

public class AggiungiSezioneFragment extends Fragment {

    EditText nomeSezioneEditText;
    Button salvaSezioneButton;

    public AggiungiSezioneFragment() {
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
        View v = inflater.inflate(R.layout.fragment_aggiungi_sezione, container, false);

        nomeSezioneEditText = v.findViewById(R.id.nuovaSezioneEditText);
        salvaSezioneButton = v.findViewById(R.id.salvaSezioneButton);

        salvaSezioneButton.setOnClickListener(view -> {
            String nomeSezione = nomeSezioneEditText.getText().toString();
            SezioneMenu nuovaSezione = new SezioneMenu(nomeSezione, new ArrayList<>());

            aggiungiSezione(nuovaSezione);
            sostituisciFragment();
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