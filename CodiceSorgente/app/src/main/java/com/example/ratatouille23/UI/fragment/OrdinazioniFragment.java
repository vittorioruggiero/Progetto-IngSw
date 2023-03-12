package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.HomeAdminFragment.getAttivita;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.ProdottiOrdinazioneAdapter;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.SingoloOrdine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OrdinazioniFragment extends Fragment implements ProdottiOrdinazioneAdapter.ItemClickListenerOrdinazione{

    private Button aggiungiProdottoButton, salvaOrdinazioneButton;
    private FragmentTransaction transaction;
    private Spinner selezionaTavoloSpinner;
    private EditText numeroCommensaliEditText;
    private RecyclerView recyclerView;
    private Attivita attivita;
    private static ArrayList<Integer> tavoli;
    private static ProdottiOrdinazioneAdapter prodottiOrdinazioneAdapter;
    private static Ordinazione ordinazione;
    private static List<SingoloOrdine> prodottiOrdine = new ArrayList<>();

    public OrdinazioniFragment() {
        // Required empty public constructor
    }

    public static OrdinazioniFragment newInstance(String param1, String param2) {
        OrdinazioniFragment fragment = new OrdinazioniFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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

        tavoli = new ArrayList<>();
        attivita = getAttivita();

        if(attivita != null){
            for(int i = 1; i <= attivita.getCapienza(); i++){
                tavoli.add(i);
            }
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, tavoli);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selezionaTavoloSpinner.setAdapter(adapter);

        }

        prodottiOrdinazioneAdapter = new ProdottiOrdinazioneAdapter(prodottiOrdine, getActivity(), this);
        recyclerView.setAdapter(prodottiOrdinazioneAdapter);

        aggiungiProdottoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisualizzaMenuFragment fragment = new VisualizzaMenuFragment();
                sostituisciFragment(fragment);
            }
        });
        salvaOrdinazioneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selezionaTavoloSpinner.getSelectedItem() != null){
                    //ordinazione = new Ordinazione(prodottiOrdine, Integer.parseInt(selezionaTavoloSpinner.getSelectedItem().toString()), Integer.parseInt(numeroCommensaliEditText.getText().toString()));
                    Toast.makeText(getActivity(), "Ordinazione creata con successo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Non esistono ordinazioni!", Toast.LENGTH_SHORT).show();
                }
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

    public static void aggiungiProdottoOrdinazione(SingoloOrdine ordine){
        prodottiOrdine.add(ordine);
        prodottiOrdinazioneAdapter.notifyDataSetChanged();
    }

    public void onItemClickOrdinazione(int posizione) {
        prodottiOrdine.remove(posizione);
        prodottiOrdinazioneAdapter.notifyDataSetChanged();
    }

    public static Ordinazione getOrdinazione(){
        return ordinazione;
    }

    public static ArrayList<Integer> getTavoli(){
        return tavoli;
    }
}