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
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.HomeSupervisoreActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.adapter.AvvisiAdapter;
import com.example.ratatouille23.entity.Avviso;
import com.example.ratatouille23.retrofit.API.AvvisoAPI;
import com.example.ratatouille23.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeSupervisoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeSupervisoreFragment extends Fragment implements AvvisiAdapter.ItemClickListenerAvvisi{

    private static final ArrayList<Avviso> avvisiSupervisore = new ArrayList<>();
    private RecyclerView recyclerView;
    private List<Avviso> listaAvvisi;
    private AvvisiAdapter avvisiSupervisoreAdapter;
    private RetrofitService retrofitService;
    private AvvisoAPI avvisoAPI;
    private Button logoutButton;

    public HomeSupervisoreFragment() {
        // Required empty public constructor
    }
    public static HomeSupervisoreFragment newInstance(String param1, String param2) {
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

        logoutButton.setOnClickListener(view -> {
            Intent loginscreen = new Intent(getActivity(), LoginActivity.class);
            loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            clearAll();
            startActivity(loginscreen);
            getActivity().finish();
        });


        avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        avvisoAPI.getAllAvvisi()
                .enqueue(new Callback<List<Avviso>>() {
                    @Override
                    public void onResponse(Call<List<Avviso>> call, Response<List<Avviso>> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body().toString());
                            listaAvvisi = response.body();
                            avvisiSupervisoreAdapter = new AvvisiAdapter(listaAvvisi, getContext(), HomeSupervisoreFragment.this);
                            recyclerView.setAdapter(avvisiSupervisoreAdapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                        }else{
                            Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Avviso>> call, Throwable t) {
                        Logger.getLogger(HomeSupervisoreActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(getContext(), "Server Spento", Toast.LENGTH_SHORT).show();
                    }
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
        listaAvvisi.remove(posizione);
        avvisiSupervisoreAdapter.notifyDataSetChanged();
    }

    public static void addAvvisoSupervisore(Avviso avviso){
        avvisiSupervisore.add(avviso);
    }
}