package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.MenuRecyclerAdapter;
import com.example.ratatouille23.adapter.ProdottiAdapter;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PersonalizzaMenuFragment extends Fragment implements ProdottiAdapter.ItemClickListener {

    private static final String TAG = "PersonalizzaMenuFragment";
    private static ArrayList<SezioneMenu> sezioni = new ArrayList<>();
    private RecyclerView recyclerView;
    private FragmentTransaction transaction;
    private SearchView cercaProdottiSearchView;
    private static MenuRecyclerAdapter menuRecyclerAdapter;
    private FloatingActionButton opzioniButton;

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
                filter(s);
                return false;
            }
        });

        return v;

    }

    public void sostituisciFragment(Fragment fragment){
        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.adminFragmentContainerView, fragment);
        transaction.hide(this);
        transaction.addToBackStack(null);
        transaction.commit();
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
        Fragment fragment = ModificaProdottoFragment.newInstance(prodottoMenu.getNome(), prodottoMenu.getNomeSecondaLingua(),
                prodottoMenu.getIngredienti(), prodottoMenu.getIngredientiSecondaLingua(), prodottoMenu.getPrezzo(), posizione, posizioneSezione);
        sostituisciFragment(fragment);
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        List<ProdottoMenu> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        /*for (SezioneMenu item : sezioni) {
            for(ProdottoMenu prodotto : item.getProdottiMenu()){
                if (prodotto.getNome().toLowerCase().contains(text.toLowerCase())) {
                    // if the item is matched we are
                    // adding it to our filtered list.
                    filteredlist.add(prodotto);
                }
            }
        }*/

        for(int i = 0; i < sezioni.size(); i++){

            for(int j = 0; j < sezioni.get(i).getProdottiMenu().size(); j++){
                if(sezioni.get(i).getProdottiMenu().get(j).getNome().toLowerCase().contains(text.toLowerCase())){
                    filteredlist.add(sezioni.get(i).getProdottiMenu().get(j));
                }
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getActivity(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            menuRecyclerAdapter.getProdottoAdapter().filterList(filteredlist);
        }
    }

}