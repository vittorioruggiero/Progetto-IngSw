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

import com.example.ratatouille23.Controller.Controller;
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
    private Controller controllerAdmin;

    public CreaUtenteFragment() {
        // Required empty public constructor
    }
    public static CreaUtenteFragment newInstance(String param1, String param2) {
        CreaUtenteFragment fragment = new CreaUtenteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_crea_utente, container, false);
        controllerAdmin = new Controller(getActivity().toString());

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
        creaUtenteButton.setOnClickListener(view -> creazioneUtenteAlertDialog.show());


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
                            controllerAdmin.salvaSupervisore(nomeUtente, email, password, getActivity());
                            clearCampi();
                        }else if(tipologiaUtente.equals("Addetto alla sala")){
                            controllerAdmin.salvaAddettoSala(nomeUtente, email, password, getActivity());
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

    AlertDialog creaUscitaCreazioneUtenteAlertDialog() {

        AlertDialog.Builder uscitaCreazioneUtenteAlertDialogBuilder = new AlertDialog.Builder(getContext());
        uscitaCreazioneUtenteAlertDialogBuilder.setMessage("Uscendo non creerai l'utente. Vuoi proseguire?");
        uscitaCreazioneUtenteAlertDialogBuilder.setCancelable(false);

        uscitaCreazioneUtenteAlertDialogBuilder.setPositiveButton(
                "Conferma",
                (dialog, id) -> requireActivity().onBackPressed());

        uscitaCreazioneUtenteAlertDialogBuilder.setNegativeButton(
                "Annulla",
                (dialog, id) -> dialog.cancel());

        return uscitaCreazioneUtenteAlertDialogBuilder.create();
    }

}