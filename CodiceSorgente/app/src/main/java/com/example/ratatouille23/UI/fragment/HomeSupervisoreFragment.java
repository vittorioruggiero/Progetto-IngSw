package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.clearAll;
import static com.example.ratatouille23.UI.activity.LoginActivity.getAddettoSala;
import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;

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
import com.example.ratatouille23.UI.activity.HomeSupervisoreActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.adapter.AvvisiAdapter;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Avviso;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.AvvisoAPI;
import com.example.ratatouille23.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeSupervisoreFragment extends Fragment implements AvvisiAdapter.ItemClickListenerAvvisi{

    private RecyclerView recyclerView;
    private List<Avviso> listaAvvisi;
    private AvvisiAdapter avvisiSupervisoreAdapter;
    private RetrofitService retrofitService;
    private AvvisoAPI avvisoAPI;
    private Button logoutButton;
    private Controller controller;
    private Supervisore supervisore = getSupervisore();

    public HomeSupervisoreFragment() {
        // Required empty public constructor
    }
    public static HomeSupervisoreFragment newInstance() {
        HomeSupervisoreFragment fragment = new HomeSupervisoreFragment();
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

        View inflatedView = inflater.inflate(R.layout.fragment_home_supervisore, container, false);

        retrofitService = new RetrofitService();

        recyclerView = inflatedView.findViewById(R.id.avvisiSupervisoreRecyclerView);
        logoutButton = inflatedView.findViewById(R.id.logoutButtonSupervisore);

        controller = new Controller(getActivity().toString());
        controller.checkAvvisiSupervisore(supervisore.getEmail(), this);

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
        controller.eliminaAvvisoSupervisore(listaAvvisi.get(posizione), this, supervisore.getEmail());
    }

    public void notifyDataChanged(){
        avvisiSupervisoreAdapter.notifyDataSetChanged();
    }

    public void setAvvisiRecyclerView(List<Avviso> avvisi){
        listaAvvisi = avvisi;
        avvisiSupervisoreAdapter = new AvvisiAdapter(listaAvvisi, getContext(), HomeSupervisoreFragment.this);
        recyclerView.setAdapter(avvisiSupervisoreAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}