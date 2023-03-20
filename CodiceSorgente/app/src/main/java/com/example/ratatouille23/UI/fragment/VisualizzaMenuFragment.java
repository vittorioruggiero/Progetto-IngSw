package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.MenuRecyclerAdapter;
import com.example.ratatouille23.adapter.ProdottiVisualizzaMenuAdapter;
import com.example.ratatouille23.adapter.VisualizzaMenuAdapter;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VisualizzaMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisualizzaMenuFragment extends Fragment implements ProdottiVisualizzaMenuAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private SearchView cercaProdottoSearchView;
    private Button indietroButton;
    private VisualizzaMenuAdapter visualizzaMenuAdapter;
    private FragmentTransaction transaction;
    private Controller controller;
    private ArrayList<SezioneMenu> sezioni;

    public VisualizzaMenuFragment() {
        // Required empty public constructor
    }

    public static VisualizzaMenuFragment newInstance(String param1, String param2) {
        VisualizzaMenuFragment fragment = new VisualizzaMenuFragment();
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
        View v = inflater.inflate(R.layout.fragment_visualizza_menu, container, false);

        recyclerView = v.findViewById(R.id.visualizzaMenuRecyclerView);
        cercaProdottoSearchView = v.findViewById(R.id.visualizzaMenuSearchView);
        indietroButton = v.findViewById(R.id.indietroButton);

        controller = new Controller(getActivity().toString());

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.addettoSalaBottomNavigationView);
        bottomNavigationView.setVisibility(View.GONE);

        controller.checkSezioniAddettoSala(getActivity(), this);

        //ArrayList<SezioneMenu> sezioni = getSezioni();


        //visualizzaMenuAdapter = new VisualizzaMenuAdapter(sezioni, getActivity(), this);
        //recyclerView.setAdapter(visualizzaMenuAdapter);

        cercaProdottoSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                visualizzaMenuAdapter.getFilter().filter(s);
                return true;
            }
        });

        indietroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sostituisciFragment();
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                sostituisciFragment();
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return v;
    }

    public void onItemClickVisual(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione) {
        Fragment fragment = VisualizzaProdottoFragment.newInstance(prodottoMenu.getNomeProdotto(), prodottoMenu.getNomeSecondaLingua(),
                prodottoMenu.getDescrizione(), prodottoMenu.getDescrizioneSecondaLingua(),
                prodottoMenu.getCosto(), posizione, posizioneSezione, sezioni);
        sostituisciFragmentSecond(fragment);
    }


    public void sostituisciFragment(){
        transaction = null;
        if (getFragmentManager() != null) {
            transaction = getFragmentManager().beginTransaction();
        }
        try {
            transaction.remove(this);
        }catch(NullPointerException e){
            transaction.commit();
        }
        transaction.addToBackStack(null);
        transaction.replace(R.id.addettoSalaFragmentContainerView, OrdinazioniFragment.class, null);
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

    public void sostituisciFragmentSecond(Fragment fragment){
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.addettoSalaFragmentContainerView, fragment, null);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setVisualizzaMenuRecyclerAdapter(ArrayList<SezioneMenu> sezioniAggiornate){
        sezioni = sezioniAggiornate;
        visualizzaMenuAdapter = new VisualizzaMenuAdapter(sezioni, getActivity(), VisualizzaMenuFragment.this);
        recyclerView.setAdapter(visualizzaMenuAdapter);
    }

    public void notifyDataChanged(){
        visualizzaMenuAdapter.notifyDataSetChanged();
    }



}