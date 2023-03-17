package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.SearchView;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.MenuRecyclerAdapter;
import com.example.ratatouille23.adapter.ProdottiAdapter;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.ProdottoMenuAPI;
import com.example.ratatouille23.retrofit.API.SezioneMenuAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PersonalizzaMenuFragment extends Fragment implements ProdottiAdapter.ItemClickListener {

    private static ArrayList<SezioneMenu> sezioni = new ArrayList<>();
    private RecyclerView recyclerView;
    private FragmentTransaction transaction;
    private SearchView cercaProdottiSearchView;
    private MenuRecyclerAdapter menuRecyclerAdapter;
    private FloatingActionButton opzioniButton;
    private BottomNavigationView bottomNavigationView;
    private Controller controllerAdmin;
    private Controller controllerSupervisore;

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

        if(getActivity().toString().contains("Admin")){
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
            controllerAdmin = new Controller(getActivity().toString());
            controllerAdmin.checkSezioniAdmin(getActivity(), this);
        }else{
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
            controllerSupervisore = new Controller(getActivity().toString());
            controllerSupervisore.checkSezioniSupervisore(getActivity(), this);
        }

        opzioniButton.setOnClickListener(view -> {
            PopupMenu opzioniMenu = new PopupMenu(getActivity(), opzioniButton);
            //Inflating popup menu from popup_menu.xml file
            opzioniMenu.getMenuInflater().inflate(R.menu.opzioni_personalizza_menu, opzioniMenu.getMenu());
            opzioniMenu.setOnMenuItemClickListener(menuItem -> {
                //Toast message on menu item clicked
                int id = menuItem.getItemId();

                if(id == R.id.aggiungiProdotto){
                    Fragment fragment = AggiungiProdottoFragment.newIstance(sezioni);
                    sostituisciFragment(fragment);
                    return true;
                }else if(id == R.id.aggiungiSezione){
                    AggiungiSezioneFragment fragment = new AggiungiSezioneFragment();
                    //Fragment fragment = AggiungiSezioneFragment.newIstance(controllerAdmin);
                    sostituisciFragment(fragment);
                    return true;
                }else if(id == R.id.ordineSezioni){
                    //ModificaSezioniFragment fragment = new ModificaSezioniFragment();
                    Fragment fragment = ModificaSezioniFragment.newIstance(sezioni);
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
            transaction.replace(R.id.adminFragmentContainerView, fragment);
        }
        else if(getActivity().toString().contains("Supervisore")){
            transaction.replace(R.id.supervisoreFragmentContainerView, fragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();

        bottomNavigationView.setVisibility(View.INVISIBLE);
    }

    public static ArrayList<SezioneMenu> getSezioni(){
        return sezioni;
    }

    public void setMenuRecyclerAdapter(ArrayList<SezioneMenu> sezioniAggiornate){
        sezioni = sezioniAggiornate;
        menuRecyclerAdapter = new MenuRecyclerAdapter(sezioni, getActivity(), PersonalizzaMenuFragment.this);
        recyclerView.setAdapter(menuRecyclerAdapter);
    }

    public void notifyDataChanged(){
        menuRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione) {
        Fragment fragment = ModificaProdottoFragment.newInstance(prodottoMenu.getNomeProdotto(), prodottoMenu.getNomeSecondaLingua(),
                prodottoMenu.getDescrizione(), prodottoMenu.getDescrizioneSecondaLingua(), prodottoMenu.getCosto(), posizione, posizioneSezione, sezioni);
        sostituisciFragment(fragment);
    }

}