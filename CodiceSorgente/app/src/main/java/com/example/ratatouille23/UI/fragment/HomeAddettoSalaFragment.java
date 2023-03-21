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

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAddettoSalaActivity;
import com.example.ratatouille23.UI.activity.HomeSupervisoreActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.adapter.AvvisiAdapter;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeAddettoSalaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAddettoSalaFragment extends Fragment {

    private RecyclerView recyclerView;
    private AvvisiAdapter avvisiAddettoSalaAdapter;
    private Button logoutButton;
    private RetrofitService retrofitService;
    private AvvisoAPI avvisoAPI;
    private List<Avviso> listaAvvisi;

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

        avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        avvisoAPI.getAllAvvisiByAttivita(getAddettoSala().getNomeAttivita(), getAddettoSala().getIndirizzoAttivita())
                .enqueue(new Callback<List<Avviso>>() {
                    @Override
                    public void onResponse(Call<List<Avviso>> call, Response<List<Avviso>> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAddettoSalaActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body().toString());
                            listaAvvisi = response.body();
                            avvisiAddettoSalaAdapter = new AvvisiAdapter(listaAvvisi, getContext());
                            recyclerView.setAdapter(avvisiAddettoSalaAdapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                        }else{
                            Logger.getLogger(HomeAddettoSalaActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Avviso>> call, Throwable t) {
                        Logger.getLogger(HomeAddettoSalaActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(getContext(), "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

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
}