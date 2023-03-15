package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;
import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;

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
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalizzaMenuFragment extends Fragment implements ProdottiAdapter.ItemClickListener {

    private static final String TAG = "PersonalizzaMenuFragment";
    private static ArrayList<SezioneMenu> sezioni = new ArrayList<>();
    private static ArrayList<ProdottoMenu> prodottiMenu = new ArrayList<>();
    private Amministratore admin;
    private Supervisore supervisore;
    private RecyclerView recyclerView;
    private FragmentTransaction transaction;
    private SearchView cercaProdottiSearchView;
    private static MenuRecyclerAdapter menuRecyclerAdapter;
    private FloatingActionButton opzioniButton;
    private BottomNavigationView bottomNavigationView;
    private RetrofitService retrofitService;
    private SezioneMenuAPI sezioneMenuAPI;
    private ProdottoMenuAPI prodottoMenuAPI;

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
        retrofitService = new RetrofitService();
        sezioneMenuAPI = retrofitService.getRetrofit().create(SezioneMenuAPI.class);
        prodottoMenuAPI = retrofitService.getRetrofit().create(ProdottoMenuAPI.class);

        if(getActivity().toString().contains("Admin")){
            admin = getAdmin();
            checkSezioniAdmin();
        }else{
            supervisore = getSupervisore();
            checkSezioniSupervisore();
        }

        if(getActivity().toString().contains("Admin"))
            bottomNavigationView = requireActivity().findViewById(R.id.adminBottomNavigationView);
        else {
            bottomNavigationView = requireActivity().findViewById(R.id.supervisoreBottomNavigationView);
        }


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

    private void checkSezioniAdmin() {

        sezioneMenuAPI.getSezioniByAttivita(admin.getNomeAttivita(), admin.getIndirizzoAttivita())
                .enqueue(new Callback<ArrayList<SezioneMenu>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SezioneMenu>> call, Response<ArrayList<SezioneMenu>> response) {
                        if(response.body() != null){
                            setProdotti(response.body());
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                        }else{
                            Toast.makeText(getActivity(), "Non ci sono prodotti da visualizzare", Toast.LENGTH_SHORT).show();
                        }
                        menuRecyclerAdapter = new MenuRecyclerAdapter(sezioni, getActivity(), PersonalizzaMenuFragment.this);
                        recyclerView.setAdapter(menuRecyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SezioneMenu>> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(getActivity(), "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });



    }

    private void checkSezioniSupervisore() {

        sezioneMenuAPI.getSezioniByAttivita(supervisore.getNomeAttivita(), supervisore.getIndirizzoAttivita())
                .enqueue(new Callback<ArrayList<SezioneMenu>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SezioneMenu>> call, Response<ArrayList<SezioneMenu>> response) {
                        if(response.body() != null){
                            setProdotti(response.body());
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                        }else{
                            Toast.makeText(getActivity(), "Non ci sono prodotti da visualizzare", Toast.LENGTH_SHORT).show();
                        }
                        menuRecyclerAdapter = new MenuRecyclerAdapter(sezioni, getActivity(), PersonalizzaMenuFragment.this);
                        recyclerView.setAdapter(menuRecyclerAdapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SezioneMenu>> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(getActivity(), "Controlla la connessione", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setProdotti(ArrayList<SezioneMenu> sezioniMenu) {
        sezioni = sezioniMenu;
        for(SezioneMenu sezioneMenu : sezioni){
            prodottoMenuAPI.getProdottiBySezione(sezioneMenu.getNome())
                    .enqueue(new Callback<List<ProdottoMenu>>() {
                        @Override
                        public void onResponse(Call<List<ProdottoMenu>> call, Response<List<ProdottoMenu>> response) {
                            if(response.body() != null){
                                sezioneMenu.setProdottiMenu(response.body());
                                Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            }
                            menuRecyclerAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<List<ProdottoMenu>> call, Throwable t) {
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                            Toast.makeText(getActivity(), "Controlla la connessione", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

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

    @Override
    public void onItemClick(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione) {
        Fragment fragment = ModificaProdottoFragment.newInstance(prodottoMenu.getNomeProdotto(), prodottoMenu.getNomeSecondaLingua(),
                prodottoMenu.getDescrizione(), prodottoMenu.getDescrizioneSecondaLingua(), prodottoMenu.getCosto(), posizione, posizioneSezione);
        sostituisciFragment(fragment);
    }

}