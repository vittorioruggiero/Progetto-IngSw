package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.clearAll;
import static com.example.ratatouille23.UI.activity.LoginActivity.getAddettoSala;
//import static com.example.ratatouille23.UI.activity.LoginActivity.getAddettoSala;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAddettoSalaActivity;
import com.example.ratatouille23.UI.activity.HomeSupervisoreActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.adapter.AvvisiAdapter;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Avviso;
import com.example.ratatouille23.retrofit.API.AvvisoAPI;
import com.example.ratatouille23.retrofit.RetrofitService;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAddettoSalaFragment extends Fragment implements AvvisiAdapter.ItemClickListenerAvvisi{

    private RecyclerView recyclerView;
    private AvvisiAdapter avvisiAddettoSalaAdapter;
    private Button logoutButton;
    private RetrofitService retrofitService;
    private AvvisoAPI avvisoAPI;
    private List<Avviso> listaAvvisi = new ArrayList<>();
    private Controller controller;
    private AddettoSala addettoSala = getAddettoSala();

    public HomeAddettoSalaFragment() {
        // Required empty public constructor
    }

    public static HomeAddettoSalaFragment newInstance() {
        HomeAddettoSalaFragment fragment = new HomeAddettoSalaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_home_addetto_sala, container, false);

        retrofitService = new RetrofitService();

        recyclerView = inflatedView.findViewById(R.id.avvisiAddettoSalaRecyclerView);
        controller = new Controller(getActivity().toString());

        controller.checkAvvisiAddettoSala(addettoSala.getEmail(), this);

        logoutButton = inflatedView.findViewById(R.id.logoutButtonAddettoSala);
        logoutButton.setOnClickListener(view -> {
            Intent loginscreen = new Intent(getActivity(), LoginActivity.class);
            loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            clearAll();
            startActivity(loginscreen);
            getActivity().finish();
        });


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //Esce dall'applicazione come il bottone centrale di Android
                getActivity().moveTaskToBack(true);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return inflatedView;
    }

    @Override
    public void onItemClickAvviso(int posizione) {
        controller.eliminaAvvisoAddettoSala(listaAvvisi.get(posizione), this, addettoSala.getEmail());
    }

    public void notifyDataChanged(){
        avvisiAddettoSalaAdapter.notifyDataSetChanged();
    }

    public void setAvvisiRecyclerView(List<Avviso> avvisi) {
        listaAvvisi = avvisi;
        avvisiAddettoSalaAdapter = new AvvisiAdapter(listaAvvisi, getContext(), HomeAddettoSalaFragment.this);
        recyclerView.setAdapter(avvisiAddettoSalaAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}