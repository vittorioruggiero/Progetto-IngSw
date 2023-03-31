package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.getAddettoSala;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.ProdottiOrdinazioneAdapter;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.SingoloOrdine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class OrdinazioniFragment extends Fragment implements ProdottiOrdinazioneAdapter.ItemClickListenerOrdinazione{

    private Button aggiungiProdottoButton, salvaOrdinazioneButton;
    private FragmentTransaction transaction;
    private Spinner selezionaTavoloSpinner;
    private EditText numeroCommensaliEditText;
    private RecyclerView recyclerView;
    private static ArrayList<Integer> tavoli = new ArrayList<>();
    private ProdottiOrdinazioneAdapter prodottiOrdinazioneAdapter;
    private List<SingoloOrdine> prodottiOrdine = new ArrayList<>();
    private static final String TAVOLO_ORDINAZIONI = "tavoloOrdinazioni";
    private static final String COMMENSALI_ORDINAZIONI = "commensaliOrdinazioni";
    private static final String PRODOTTI_ORDINAZIONI = "prodottirdinazioni";
    private Controller controller;
    private int tavolo;
    private int commensali;
    private AddettoSala addettoSala = getAddettoSala();

    public OrdinazioniFragment() {
        // Required empty public constructor
    }

    public static OrdinazioniFragment newInstance(int tavolo, int commensali, List<SingoloOrdine> prodottiOrdine) {
        OrdinazioniFragment fragment = new OrdinazioniFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String myJson = gson.toJson(prodottiOrdine);
        args.putString(PRODOTTI_ORDINAZIONI, myJson);
        args.putInt(TAVOLO_ORDINAZIONI, tavolo);
        args.putInt(COMMENSALI_ORDINAZIONI, commensali);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Gson gson = new Gson();
            tavolo = getArguments().getInt(TAVOLO_ORDINAZIONI);
            commensali = getArguments().getInt(COMMENSALI_ORDINAZIONI);
            prodottiOrdine = gson.fromJson(getArguments().getString(PRODOTTI_ORDINAZIONI), new TypeToken<List<SingoloOrdine>>(){}.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ordinazioni, container, false);

        aggiungiProdottoButton = v.findViewById(R.id.aggiungiProdottoButton);
        salvaOrdinazioneButton = v.findViewById(R.id.salvaOrdinazioneButton);
        numeroCommensaliEditText = v.findViewById(R.id.numeroCommensaliOrdinazioneEditText);
        selezionaTavoloSpinner = v.findViewById(R.id.selezionaTavoloOrdinazioneSpinner);
        recyclerView = v.findViewById(R.id.contiRecyclerView);
        controller = new Controller(getActivity().toString());



        if(addettoSala.getIdAttivita() != 0){
            int idAttivita = addettoSala.getIdAttivita();
            if(tavoli.isEmpty())
                controller.checkAttivitaAddettoSala(idAttivita, this);
            else{
                ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_spinner_item, tavoli);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                selezionaTavoloSpinner.setAdapter(adapter);
                if(getArguments() != null){
                    selezionaTavoloSpinner.setSelection(tavolo - 1);
                    numeroCommensaliEditText.setText(String.valueOf(commensali));
                }
            }
        }else{
            Toast.makeText(getActivity(), "Errore! Contattare l'amministratore", Toast.LENGTH_SHORT).show();
        }

        prodottiOrdinazioneAdapter = new ProdottiOrdinazioneAdapter(prodottiOrdine, getActivity(), this);
        recyclerView.setAdapter(prodottiOrdinazioneAdapter);

        aggiungiProdottoButton.setOnClickListener(view -> {
            if(!(numeroCommensaliEditText.getText().toString().equals("")) && selezionaTavoloSpinner.getSelectedItem() != null){
                Fragment fragment = VisualizzaMenuFragment.newInstance(Integer.parseInt(selezionaTavoloSpinner.getSelectedItem().toString()),
                        Integer.parseInt(numeroCommensaliEditText.getText().toString()), prodottiOrdine);
                sostituisciFragment(fragment);
            }else{
                Toast.makeText(getActivity(), "Inserire i campi correttamente", Toast.LENGTH_SHORT).show();
            }

        });
        salvaOrdinazioneButton.setOnClickListener(view -> {
            if(selezionaTavoloSpinner.getSelectedItem() != null){
                controller.checkOrdinazione(Integer.parseInt(selezionaTavoloSpinner.getSelectedItem().toString()),
                        Integer.parseInt(numeroCommensaliEditText.getText().toString()), prodottiOrdine,
                        OrdinazioniFragment.this, addettoSala.getIdAttivita());
            }else{
                Toast.makeText(getActivity(), "Non esistono ordinazioni!", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    public void sostituisciFragment(Fragment fragment){
        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.addettoSalaFragmentContainerView, fragment);
        transaction.hide(this);
        transaction.addToBackStack(null);
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    public void onItemClickOrdinazione(int posizione) {
        prodottiOrdine.remove(posizione);
        prodottiOrdinazioneAdapter.notifyDataSetChanged();
    }

    public void setTavoliAttivita(Attivita attivita){
        if(attivita != null){
            for(int i = 1; i <= attivita.getCapienza(); i++){
                tavoli.add(i);
            }
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, tavoli);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selezionaTavoloSpinner.setAdapter(adapter);

        }
    }

    public void clearProdotti(){
        prodottiOrdine.clear();
        prodottiOrdinazioneAdapter.notifyDataSetChanged();
    }




}