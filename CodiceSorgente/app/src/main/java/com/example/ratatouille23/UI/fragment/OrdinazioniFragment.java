package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.ProdottiOrdinazioneAdapter;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SingoloOrdine;

import java.util.ArrayList;

public class OrdinazioniFragment extends Fragment implements ProdottiOrdinazioneAdapter.ItemClickListenerOrdinazione{

    private Button aggiungiProdottoButton, salvaOrdinazioneButton;
    private FragmentTransaction transaction;
    private RecyclerView recyclerView;
    private static ProdottiOrdinazioneAdapter prodottiOrdinazioneAdapter;
    private static Ordinazione ordinazione;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public OrdinazioniFragment() {
        // Required empty public constructor
    }

    public static OrdinazioniFragment newInstance(String param1, String param2) {
        OrdinazioniFragment fragment = new OrdinazioniFragment();
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
        View v = inflater.inflate(R.layout.fragment_ordinazioni, container, false);

        aggiungiProdottoButton = v.findViewById(R.id.aggiungiProdottoButton);
        salvaOrdinazioneButton = v.findViewById(R.id.salvaOrdinazioneButton);
        recyclerView = v.findViewById(R.id.contiRecyclerView);
        ordinazione = new Ordinazione(new ArrayList<>());

        prodottiOrdinazioneAdapter = new ProdottiOrdinazioneAdapter(ordinazione.getListaProdotti(), getActivity(), this);
        recyclerView.setAdapter(prodottiOrdinazioneAdapter);

        aggiungiProdottoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisualizzaMenuFragment fragment = new VisualizzaMenuFragment();
                sostituisciFragment(fragment);
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
        ordinazione.getListaProdotti().add(ordine);
        prodottiOrdinazioneAdapter.notifyDataSetChanged();
    }

    public void onItemClickOrdinazione(int posizione) {
        ordinazione.getListaProdotti().remove(posizione);
        prodottiOrdinazioneAdapter.notifyDataSetChanged();
    }
}