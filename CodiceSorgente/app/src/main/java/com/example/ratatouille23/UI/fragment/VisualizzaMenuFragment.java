package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment.getSezioni;

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

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.ProdottiVisualizzaMenuAdapter;
import com.example.ratatouille23.adapter.VisualizzaMenuAdapter;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;

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
    private static VisualizzaMenuAdapter visualizzaMenuAdapter;
    private FragmentTransaction transaction;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public VisualizzaMenuFragment() {
        // Required empty public constructor
    }

    public static VisualizzaMenuFragment newInstance(String param1, String param2) {
        VisualizzaMenuFragment fragment = new VisualizzaMenuFragment();
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
        View v = inflater.inflate(R.layout.fragment_visualizza_menu, container, false);

        recyclerView = v.findViewById(R.id.visualizzaMenuRecyclerView);
        cercaProdottoSearchView = v.findViewById(R.id.visualizzaMenuSearchView);
        indietroButton = v.findViewById(R.id.indietroButton);

        ArrayList<SezioneMenu> sezioni = getSezioni();


        visualizzaMenuAdapter = new VisualizzaMenuAdapter(sezioni, getActivity(), this);
        recyclerView.setAdapter(visualizzaMenuAdapter);

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

    public void onItemClickVisual(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione) {
        Fragment fragment = VisualizzaProdottoFragment.newInstance(prodottoMenu.getNome(), prodottoMenu.getNomeSecondaLingua(),
                prodottoMenu.getIngredienti(), prodottoMenu.getIngredientiSecondaLingua(), prodottoMenu.getPrezzo(), posizione, posizioneSezione);
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



}