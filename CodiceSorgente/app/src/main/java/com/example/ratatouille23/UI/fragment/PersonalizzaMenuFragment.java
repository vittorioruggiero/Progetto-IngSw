package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalizzaMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class PersonalizzaMenuFragment extends Fragment implements Serializable{

    public static final String TAG = "PersonalizzaMenuFragment";
    private ArrayList<SezioneMenu> sezioni = new ArrayList<>();

    private RecyclerView recyclerView;
    private MenuRecyclerAdapter menuRecyclerAdapter;

    private ListView menuAttivitaListView;
    private EditText cercaEditText;
    private FloatingActionButton opzioniButton;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalizzaMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalizzaMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalizzaMenuFragment newInstance(String param1, String param2) {
        PersonalizzaMenuFragment fragment = new PersonalizzaMenuFragment();
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
        View v = inflater.inflate(R.layout.fragment_personalizza_menu, container, false);

        recyclerView = v.findViewById(R.id.menuAttivitaRecyclerView);
        opzioniButton = (FloatingActionButton) v.findViewById(R.id.opzioniButton);

        String nome = "Margherita";
        String ingredienti = "salsa, pomodoro";
        double prezzo = 50.00;
        String nomeSezione2 = "Dolci";

        ProdottoMenu nuovoProdotto = new ProdottoMenu(nome, ingredienti, prezzo);

        String nomeSezionePizze = "Pizze";
        List<ProdottoMenu> sezionePizze = new ArrayList<>();

        sezionePizze.add(nuovoProdotto);

        sezioni.add(new SezioneMenu(nomeSezionePizze, sezionePizze));

        sezioni.add(new SezioneMenu(nomeSezione2));

        menuRecyclerAdapter = new MenuRecyclerAdapter(sezioni);
        recyclerView.setAdapter(menuRecyclerAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        ProdottoMenu nuovoProdotto2 = new ProdottoMenu("Capricciosa", "Prosciutto, mozzarella", 50.00);
        sezionePizze.add(nuovoProdotto2);

        menuRecyclerAdapter.notifyDataSetChanged();

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
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.adminFragmentContainerView, fragment);
        transaction.commit();
    }

    /*public void aggiungiProdotto(ProdottoMenu prodotto, int sezioneIndex) {
        sezioni.get(sezioneIndex).addItem(prodotto);
        prodottiAdapter.notifyDataSetChanged();
    }*/

    public MenuRecyclerAdapter getAdapter() {
        return menuRecyclerAdapter;
    }


}