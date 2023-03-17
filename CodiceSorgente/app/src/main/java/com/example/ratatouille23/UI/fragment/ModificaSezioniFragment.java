package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.retrofit.API.SezioneMenuAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModificaSezioniFragment extends Fragment {

    private Spinner sezioniSpinner;
    private Button eliminaSezioneButtom;
    private BottomNavigationView bottomNavigationView;
    private Controller controllerAdmin;
    private Controller controllerSupervisore;
    private ArrayList<SezioneMenu> sezioni;
    private static final String SEZIONI_MODIFICA_SEZIONI = "sezioniModificaSezioni";

    public ModificaSezioniFragment() {
        // Required empty public constructor
    }

    public static ModificaSezioniFragment newIstance(ArrayList<SezioneMenu> sezioni){

        ModificaSezioniFragment fragment = new ModificaSezioniFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String myJson = gson.toJson(sezioni);
        args.putString(SEZIONI_MODIFICA_SEZIONI, myJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Gson gson = new Gson();
            sezioni = gson.fromJson(getArguments().getString(SEZIONI_MODIFICA_SEZIONI), new TypeToken<ArrayList<SezioneMenu>>(){}.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modifica_sezioni, container, false);

        sezioniSpinner = v.findViewById(R.id.sezioniSpinner);
        eliminaSezioneButtom = v.findViewById(R.id.eliminaSezioneButton);

        if(getActivity().toString().contains("Admin")){
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
            controllerAdmin = new Controller(getActivity().toString());
        }else{
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
            controllerSupervisore = new Controller(getActivity().toString());
        }

        ArrayAdapter<SezioneMenu> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, sezioni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sezioniSpinner.setAdapter(adapter);

        eliminaSezioneButtom.setOnClickListener(view -> {
            try {
                if(getActivity().toString().contains("Admin")){
                    controllerAdmin.eliminaSezione(sezioniSpinner.getSelectedItem().toString(), getActivity(), this);
                }else{
                    controllerSupervisore.eliminaSezione(sezioniSpinner.getSelectedItem().toString(), getActivity(), this);
                }

            }catch(ArrayIndexOutOfBoundsException e){
                Toast.makeText(getActivity(), "Non ci sono sezioni da eliminare", Toast.LENGTH_SHORT).show();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                sostituisciFragment();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

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

        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}