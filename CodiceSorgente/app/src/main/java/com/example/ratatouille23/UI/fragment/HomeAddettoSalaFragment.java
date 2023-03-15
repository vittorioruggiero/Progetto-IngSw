package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.clearAll;

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

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.adapter.AvvisiAdapter;
import com.example.ratatouille23.entity.Avviso;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeAddettoSalaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAddettoSalaFragment extends Fragment implements AvvisiAdapter.ItemClickListenerAvvisi {

    private static final ArrayList<Avviso> avvisiAddettoSala = new ArrayList<>();
    private RecyclerView recyclerView;
    private AvvisiAdapter avvisiAddettoSalaAdapter;
    private Button logoutButton;

    public HomeAddettoSalaFragment() {
        // Required empty public constructor
    }

    public static HomeAddettoSalaFragment newInstance(String param1, String param2) {
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

        recyclerView = inflatedView.findViewById(R.id.avvisiAddettoSalaRecyclerView);
        avvisiAddettoSalaAdapter = new AvvisiAdapter(avvisiAddettoSala, getContext(), this);
        recyclerView.setAdapter(avvisiAddettoSalaAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
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
        //avvisiAddettoSala.get(posizione);
        avvisiAddettoSala.remove(posizione);
        avvisiAddettoSalaAdapter.notifyDataSetChanged();
    }

    public static void addAvvisiAddettoSala(Avviso avviso){
        avvisiAddettoSala.add(avviso);
    }
}