package com.example.ratatouille23;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AggiungiSezioneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AggiungiSezioneFragment extends Fragment implements Serializable {


    EditText nomeSezioneEditText;
    Button salvaSezioneButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AggiungiSezioneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AggiungiSezioneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AggiungiSezioneFragment newInstance(String param1, String param2) {
        AggiungiSezioneFragment fragment = new AggiungiSezioneFragment();
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
        View v = inflater.inflate(R.layout.fragment_aggiungi_sezione, container, false);

        nomeSezioneEditText = (EditText) v.findViewById(R.id.nuovaSezioneEditText);
        salvaSezioneButton = (Button) v.findViewById(R.id.salvaSezioneButton);

        ArrayList<Prodotto> prodottiMenu = (ArrayList<Prodotto>)getArguments().getSerializable("key");

        salvaSezioneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeSezione = nomeSezioneEditText.getText().toString();
                prodottiMenu.add(new SezioneMenu("Dolci2"));
                PersonalizzaMenuFragment fragment = new PersonalizzaMenuFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("key2", prodottiMenu);
                fragment.setArguments(bundle);
                sostituisciFragment(fragment);
            }
        });

        return v;

    }

    public void sostituisciFragment(Fragment fragment){
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.adminFragmentContainerView, fragment);
        transaction.commit();
    }
}