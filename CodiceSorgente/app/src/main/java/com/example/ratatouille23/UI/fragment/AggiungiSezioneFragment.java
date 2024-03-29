package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;
import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.SezioneMenuAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AggiungiSezioneFragment extends Fragment {

    private EditText nomeSezioneEditText;
    private Button salvaSezioneButton;
    private BottomNavigationView bottomNavigationView;
    private Controller controllerAdmin;
    private Controller controllerSupervisore;

    private static final String CONTROLLER_AGGIUNGI_SEZIONE = "controllerAggiungiSezione";


    public AggiungiSezioneFragment() {
        // Required empty public constructor
    }

    public static AggiungiSezioneFragment newIstance(Controller controller){

        AggiungiSezioneFragment fragment = new AggiungiSezioneFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String myJson = gson.toJson(controller);
        args.putString(CONTROLLER_AGGIUNGI_SEZIONE, myJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Gson gson = new Gson();
            //controller = gson.fromJson(getArguments().getString(CONTROLLER_AGGIUNGI_SEZIONE), Controller.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_aggiungi_sezione, container, false);

        nomeSezioneEditText = v.findViewById(R.id.nuovaSezioneEditText);
        salvaSezioneButton = v.findViewById(R.id.eliminaSezioneButton);

        if(getActivity().toString().contains("Admin")){
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
            controllerAdmin = new Controller(getActivity().toString());
        }else{
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
            controllerSupervisore = new Controller(getActivity().toString());
        }

        salvaSezioneButton.setOnClickListener(view -> {
            String nomeSezione = nomeSezioneEditText.getText().toString();
            if(nomeSezione.equals("")){
                Toast.makeText(getActivity(), "Inserisci il nome della sezione", Toast.LENGTH_SHORT).show();
            }else{
                if(getActivity().toString().contains("Admin")){
                    if(getAdmin().getIdAttivita() != 0){
                        SezioneMenu nuovaSezione = new SezioneMenu(nomeSezione);
                        controllerAdmin.addSezioneAdmin(nuovaSezione, getActivity(), this);
                    }else{
                        Toast.makeText(getActivity(), "Inserire dettagli attività", Toast.LENGTH_SHORT).show();
                    }

                }else if(getActivity().toString().contains("Supervisore")){
                    if(getSupervisore().getIdAttivita() != 0){
                        SezioneMenu nuovaSezione = new SezioneMenu(nomeSezione);
                        controllerSupervisore.addSezioneSupervisore(nuovaSezione, getActivity(), this);
                    }else{
                        Toast.makeText(getActivity(), "Inserire dettagli attività", Toast.LENGTH_SHORT).show();
                    }

                }
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