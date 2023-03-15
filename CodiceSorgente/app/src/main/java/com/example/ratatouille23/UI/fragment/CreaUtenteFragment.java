package com.example.ratatouille23.UI.fragment;

import static androidx.navigation.Navigation.findNavController;

import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.entity.AddettoSala;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Supervisore;
import com.example.ratatouille23.retrofit.API.AddettoSalaAPI;
import com.example.ratatouille23.retrofit.API.SupervisoreAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreaUtenteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaUtenteFragment extends Fragment {

    private View inflatedView;
    private TextInputEditText nuovoUtenteEmailTextInputEditText, nuovoUtenteNomeTextInputEditText, nuovoUtentePasswordTextInputEditText;
    private Spinner tipologiaUtenteSpinner;
    private Button creaUtenteButton;
    private AlertDialog creazioneUtenteAlertDialog, uscitaCreazioneUtenteAlertDialog;
    private RetrofitService retrofitService;
    private Amministratore admin = getAdmin();
    private AddettoSalaAPI addettoSalaAPI;
    private SupervisoreAPI supervisoreAPI;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreaUtenteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreaUtenteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreaUtenteFragment newInstance(String param1, String param2) {
        CreaUtenteFragment fragment = new CreaUtenteFragment();
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

        inflatedView = inflater.inflate(R.layout.fragment_crea_utente, container, false);

        retrofitService = new RetrofitService();
        addettoSalaAPI = retrofitService.getRetrofit().create(AddettoSalaAPI.class);
        supervisoreAPI = retrofitService.getRetrofit().create(SupervisoreAPI.class);

        nuovoUtenteNomeTextInputEditText = inflatedView.findViewById(R.id.nuovoUtenteNomeTextInputEditText);
        nuovoUtenteNomeTextInputEditText.setSaveEnabled(false);

        nuovoUtenteEmailTextInputEditText = inflatedView.findViewById(R.id.nuovoUtenteEmailTextInputEditText);
        nuovoUtenteEmailTextInputEditText.setSaveEnabled(false);

        nuovoUtentePasswordTextInputEditText = inflatedView.findViewById(R.id.nuovoUtentePasswordTextInputEditText);
        nuovoUtentePasswordTextInputEditText.setSaveEnabled(false);

        tipologiaUtenteSpinner = (Spinner) inflatedView.findViewById(R.id.tipologiaUtenteSpinner);
        tipologiaUtenteSpinner.setSaveEnabled(false);

        creaUtenteButton = (Button) inflatedView.findViewById(R.id.creaUtenteButton);

        uscitaCreazioneUtenteAlertDialog = creaUscitaCreazioneUtenteAlertDialog();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tipologia_utente_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaUtenteSpinner.setAdapter(adapter);

        tipologiaUtenteSpinner.setSelection(adapter.getPosition("Supervisore"));

        creazioneUtenteAlertDialog = creaCreazioneUtenteAlertDialog();
        creaUtenteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                creazioneUtenteAlertDialog.show();
            }
        });


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setEnabled(false);
                uscitaCreazioneUtenteAlertDialog.show();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return inflatedView;
    }
    AlertDialog creaCreazioneUtenteAlertDialog() {

        AlertDialog.Builder creazioneUtenteAlertDialogBuilder = new AlertDialog.Builder(getContext());
        creazioneUtenteAlertDialogBuilder.setMessage("Sei sicuro di voler creare l'utente?");
        creazioneUtenteAlertDialogBuilder.setCancelable(false);

        creazioneUtenteAlertDialogBuilder.setPositiveButton(
                "Conferma",
                (dialog, id) -> {
                    try{
                        String nomeUtente = nuovoUtenteNomeTextInputEditText.getText().toString();
                        String email = nuovoUtenteEmailTextInputEditText.getText().toString();
                        String password = nuovoUtentePasswordTextInputEditText.getText().toString();
                        String tipologiaUtente = tipologiaUtenteSpinner.getSelectedItem().toString();
                        if(tipologiaUtente.equals("Supervisore")){
                            Supervisore supervisore = new Supervisore(email, nomeUtente, password);
                            if(admin.getNomeAttivita() != null){
                                supervisore.setNomeAttivita(admin.getNomeAttivita());
                                supervisore.setIndirizzoAttivita(admin.getIndirizzoAttivita());
                            }
                            salvaSupervisore(supervisore);
                            clearCampi();
                        }else if(tipologiaUtente.equals("Addetto alla sala")){
                            AddettoSala addettoSala = new AddettoSala(email, nomeUtente, password);
                            if(admin.getNomeAttivita() != null){
                                addettoSala.setNomeAttivita(admin.getNomeAttivita());
                                addettoSala.setIndirizzoAttivita(admin.getIndirizzoAttivita());
                            }
                            salvaAddettoSala(addettoSala);
                            clearCampi();
                        }
                    }catch(NullPointerException e){
                        Toast.makeText(getActivity(), "Inserisci tutti i campi", Toast.LENGTH_SHORT).show();
                    }
                    dialog.cancel();
                });

        creazioneUtenteAlertDialogBuilder.setNegativeButton(
                "Annulla",
                (dialog, id) -> {
                    dialog.cancel();
                });

        return creazioneUtenteAlertDialogBuilder.create();

    }

    private void clearCampi() {
        nuovoUtenteNomeTextInputEditText.setText("");
        nuovoUtenteEmailTextInputEditText.setText("");
        nuovoUtentePasswordTextInputEditText.setText("");
    }

    private void salvaAddettoSala(AddettoSala addettoSala) {

        addettoSalaAPI.save(addettoSala)
                .enqueue(new Callback<AddettoSala>() {
                    @Override
                    public void onResponse(Call<AddettoSala> call, Response<AddettoSala> response) {
                        if(response.body() != null){
                            Toast.makeText(getActivity(), "Addetto Sala salvato correttamente", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: ", response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<AddettoSala> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(getActivity(), "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void salvaSupervisore(Supervisore supervisore) {

        supervisoreAPI.salvataggioSupervisore(supervisore)
                .enqueue(new Callback<Supervisore>() {
                    @Override
                    public void onResponse(Call<Supervisore> call, Response<Supervisore> response) {
                        if(response.body() != null){
                            Toast.makeText(getActivity(), "Supervisore salvato correttamente", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: ", response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Supervisore> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                        Toast.makeText(getActivity(), "Server Spento", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    AlertDialog creaUscitaCreazioneUtenteAlertDialog() {

        AlertDialog.Builder uscitaCreazioneUtenteAlertDialogBuilder = new AlertDialog.Builder(getContext());
        uscitaCreazioneUtenteAlertDialogBuilder.setMessage("Uscendo non creerai l'utente. Vuoi proseguire?");
        uscitaCreazioneUtenteAlertDialogBuilder.setCancelable(false);

        uscitaCreazioneUtenteAlertDialogBuilder.setPositiveButton(
                "Conferma",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requireActivity().onBackPressed();
                    }
                });

        uscitaCreazioneUtenteAlertDialogBuilder.setNegativeButton(
                "Annulla",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return uscitaCreazioneUtenteAlertDialogBuilder.create();
    }

}