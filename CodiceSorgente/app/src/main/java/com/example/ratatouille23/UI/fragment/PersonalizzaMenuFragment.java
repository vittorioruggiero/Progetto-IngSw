package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.SearchView;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.MenuRecyclerAdapter;
import com.example.ratatouille23.adapter.ProdottiAdapter;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PersonalizzaMenuFragment extends Fragment implements ProdottiAdapter.ItemClickListener {

    private static final String TAG = "PersonalizzaMenuFragment";
    private static ArrayList<SezioneMenu> sezioni = new ArrayList<>();
    private RecyclerView recyclerView;
    private FragmentTransaction transaction;
    private SearchView cercaProdottiSearchView;
    private static MenuRecyclerAdapter menuRecyclerAdapter;
    private FloatingActionButton opzioniButton;
    private BottomNavigationView bottomNavigationView;

    public PersonalizzaMenuFragment() {
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
        View v = inflater.inflate(R.layout.fragment_personalizza_menu, container, false);
        opzioniButton = v.findViewById(R.id.opzioniButton);
        recyclerView = v.findViewById(R.id.menuAttivitaRecyclerView);
        cercaProdottiSearchView = v.findViewById(R.id.personalizzaMenuSearchView);

        if(getActivity().toString().contains("Admin"))
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
        else {
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
        }


        menuRecyclerAdapter = new MenuRecyclerAdapter(sezioni, getActivity(), this);
        recyclerView.setAdapter(menuRecyclerAdapter);

        opzioniButton.setOnClickListener(view -> {
            PopupMenu opzioniMenu = new PopupMenu(getActivity(), opzioniButton);
            //Inflating popup menu from popup_menu.xml file
            opzioniMenu.getMenuInflater().inflate(R.menu.opzioni_personalizza_menu, opzioniMenu.getMenu());
            opzioniMenu.setOnMenuItemClickListener(menuItem -> {
                //Toast message on menu item clicked
                int id = menuItem.getItemId();

                if(id == R.id.aggiungiProdotto){
                    AggiungiProdottoFragment fragment = new AggiungiProdottoFragment();
                    sostituisciFragment(fragment);
                    return true;
                }else if(id == R.id.aggiungiSezione){
                    AggiungiSezioneFragment fragment = new AggiungiSezioneFragment();
                    sostituisciFragment(fragment);
                    return true;
                }else if(id == R.id.ordineSezioni){
                    ModificaSezioniFragment fragment = new ModificaSezioniFragment();
                    sostituisciFragment(fragment);
                }
                return false;
            });
            // Showing the popup menu
            opzioniMenu.show();
        });

        cercaProdottiSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                menuRecyclerAdapter.getFilter().filter(s);
                return true;
            }
        });

        return v;

    }

    public void sostituisciFragment(Fragment fragment){
        transaction = getFragmentManager().beginTransaction();
        if(getActivity().toString().contains("Admin")){
            transaction.add(R.id.adminFragmentContainerView, fragment);
        }
        else if(getActivity().toString().contains("Supervisore")){
            transaction.add(R.id.supervisoreFragmentContainerView, fragment);
        }
        transaction.hide(this);
        transaction.addToBackStack(null);
        transaction.commit();

        bottomNavigationView.setVisibility(View.INVISIBLE);
    }


    public static void aggiungiProdotto(ProdottoMenu prodotto, int sezioneIndex) {
        sezioni.get(sezioneIndex).addItem(prodotto);
        menuRecyclerAdapter.notifyDataSetChanged();
    }

    public static void aggiungiSezione(SezioneMenu sezione) {
        sezioni.add(sezione);
        menuRecyclerAdapter.notifyDataSetChanged();
    }

    public static void eliminaSezione (int sezione){
        sezioni.remove(sezione);
        menuRecyclerAdapter.notifyDataSetChanged();
    }

    public static void eliminaProdotto(int prodottoIndex, int sezioneIndex) {
        sezioni.get(sezioneIndex).getProdottiMenu().remove(prodottoIndex);
        menuRecyclerAdapter.notifyDataSetChanged();
    }

    public static ArrayList<SezioneMenu> getSezioni(){
        return sezioni;
    }

    public static String getTagFragment(){
        return TAG;
    }

    @Override
    public void onItemClick(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione) {
        Fragment fragment = ModificaProdottoFragment.newInstance(prodottoMenu.getNomeProdotto(), prodottoMenu.getNomeSecondaLingua(),
                prodottoMenu.getDescrizione(), prodottoMenu.getDescrizioneSecondaLingua(), prodottoMenu.getCosto(), posizione, posizioneSezione);
        sostituisciFragment(fragment);
    }

}