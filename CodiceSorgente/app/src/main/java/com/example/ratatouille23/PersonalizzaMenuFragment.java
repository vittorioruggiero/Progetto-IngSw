package com.example.ratatouille23;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import android.telecom.ConnectionRequest;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalizzaMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class PersonalizzaMenuFragment extends Fragment implements Serializable {

    ArrayList<Prodotto> prodottiMenu = new ArrayList<>();

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

        cercaEditText = (EditText) v.findViewById(R.id.cercaEditText);
        menuAttivitaListView = (ListView) v.findViewById(R.id.menuAttivitaListView);
        opzioniButton = (FloatingActionButton) v.findViewById(R.id.opzioniButton);



        prodottiMenu.add(new SezioneMenu("Pizze Classiche"));
        // State Name
        prodottiMenu.add(new ProdottoMenu("Margherita"));
        prodottiMenu.add(new ProdottoMenu("Diavola"));
        prodottiMenu.add(new ProdottoMenu("Quattro Stagioni"));
        prodottiMenu.add(new ProdottoMenu("Primavera"));


        // Header
        prodottiMenu.add(new SezioneMenu("Pizze Rivisitate"));
        // State Name
        prodottiMenu.add(new ProdottoMenu("Margherita Due Cotture"));
        prodottiMenu.add(new ProdottoMenu("Carbonara"));
        prodottiMenu.add(new ProdottoMenu("Capricciosa"));
        prodottiMenu.add(new ProdottoMenu("Luigione"));

        // Header
        prodottiMenu.add(new SezioneMenu("Bibite"));
        // State Name
        prodottiMenu.add(new ProdottoMenu("Acqua Naturale"));
        prodottiMenu.add(new ProdottoMenu("Acqua Frizzante"));
        prodottiMenu.add(new ProdottoMenu("Birra"));

        // Header
        prodottiMenu.add(new SezioneMenu("Dolci"));
        // State Name
        prodottiMenu.add(new ProdottoMenu("Tiramisù"));
        prodottiMenu.add(new ProdottoMenu("Semifreddo"));
        prodottiMenu.add(new ProdottoMenu("Tortino al cioccolato"));


        final ProdottiAdapter adapter = new ProdottiAdapter(getActivity(), prodottiMenu);
        menuAttivitaListView.setAdapter(adapter);
        menuAttivitaListView.setTextFilterEnabled(true);



        // filter on text change
        cercaEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(adapter != null)
                {
                    adapter.getFilter().filter(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        opzioniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu opzioniMenu = new PopupMenu(getActivity(), opzioniButton);
                // Inflating popup menu from popup_menu.xml file
                opzioniMenu.getMenuInflater().inflate(R.menu.opzioni_personalizza_menu, opzioniMenu.getMenu());
                opzioniMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked


                        int id = menuItem.getItemId();

                        if(id == R.id.aggiungiProdotto){
                            AggiungiProdottoFragment fragment = new AggiungiProdottoFragment();
                            sostituisciFragment(fragment);
                            return true;
                        }else if(id == R.id.aggiungiSezione){
                            AggiungiSezioneFragment fragment = new AggiungiSezioneFragment();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("key", prodottiMenu);
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

}