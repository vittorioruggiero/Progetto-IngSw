package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.MenuRecyclerAdapter;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonalizzaMenuFragment extends Fragment {

    private static final String TAG = "PersonalizzaMenuFragment";
    private ArrayList<SezioneMenu> sezioni = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuRecyclerAdapter menuRecyclerAdapter;

    private ListView menuAttivitaListView;
    private EditText cercaEditText;
    private FloatingActionButton opzioniButton;
    private FragmentManager fragmentManager = getFragmentManager();

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
        opzioniButton = (FloatingActionButton) v.findViewById(R.id.opzioniButton);

        recyclerView = v.findViewById(R.id.menuAttivitaRecyclerView);

        List<ProdottoMenu> prodottiSezionePizze = new ArrayList<>();

        prodottiSezionePizze.add(new ProdottoMenu("Margherita", "Salsa, Mozzarella", 7));
        prodottiSezionePizze.add(new ProdottoMenu("Capricciosa", "Salsa, Mozzarella, Prosciutto", 9));
        prodottiSezionePizze.add(new ProdottoMenu("Diavola", "Salsa, Mozzarella, Salame", 8));
        prodottiSezionePizze.add(new ProdottoMenu("Salsiccia e Patatine", "Salsiccia, Patatine, Mozzarella", 10));

        sezioni.add(new SezioneMenu("Pizze", prodottiSezionePizze));

        List<ProdottoMenu> prodottiSezioneBibite = new ArrayList<>();

        prodottiSezioneBibite.add(new ProdottoMenu("Acqua Naturale", 3));
        prodottiSezioneBibite.add(new ProdottoMenu("Acqua Frizzante", 3));
        prodottiSezioneBibite.add(new ProdottoMenu("Coca-Cola", 3.5));

        sezioni.add(new SezioneMenu("Bibite", prodottiSezioneBibite));

        List<ProdottoMenu> prodottiSezioneDolci = new ArrayList<>();

        prodottiSezioneDolci.add(new ProdottoMenu("Tortino Cioccolato", "Cioccolato, Latte", 15));
        prodottiSezioneDolci.add(new ProdottoMenu("Cheesecake", "Biscotto, Latte, Fragola", 18));
        prodottiSezioneDolci.add(new ProdottoMenu("Tiramisu", "Cioccolato, Latte, Caffe", 10));

        sezioni.add(new SezioneMenu("Dolci", prodottiSezioneDolci));

        menuRecyclerAdapter = new MenuRecyclerAdapter(sezioni, getContext());
        recyclerView.setAdapter(menuRecyclerAdapter);

        opzioniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu opzioniMenu = new PopupMenu(getActivity(), opzioniButton);
                //Inflating popup menu from popup_menu.xml file
                opzioniMenu.getMenuInflater().inflate(R.menu.opzioni_personalizza_menu, opzioniMenu.getMenu());
                opzioniMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast message on menu item clicked

                        int id = menuItem.getItemId();

                        if(id == R.id.aggiungiProdotto){
                            AggiungiProdottoFragment fragment = new AggiungiProdottoFragment();
                            sostituisciFragment(fragment);
                            return true;
                        }else if(id == R.id.aggiungiSezione){
                            AggiungiSezioneFragment fragment = new AggiungiSezioneFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("key", sezioni);
                            fragment.setArguments(bundle);
                            sostituisciFragment(fragment);
                            return true;
                        }else if(id == R.id.ordineSezioni){
                            Toast.makeText(getActivity(), "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }

                });
                // Showing the popup menu
                opzioniMenu.show();
            }


        });

        return v;

    }

    public void sostituisciFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.show(getFragmentManager().findFragmentByTag("ONE"));
        //transaction.hide(this);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void aggiungiProdotto(ProdottoMenu prodotto, int sezioneIndex) {
        sezioni.get(sezioneIndex).addItem(prodotto);
        menuRecyclerAdapter.notifyDataSetChanged();
    }

    public void aggiungiSezione(SezioneMenu sezione) {
        sezioni.add(sezione);
        menuRecyclerAdapter.notifyDataSetChanged();
    }


}