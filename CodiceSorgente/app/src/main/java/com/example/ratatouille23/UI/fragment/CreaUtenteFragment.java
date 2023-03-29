package com.example.ratatouille23.UI.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreaUtenteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreaUtenteFragment extends Fragment {

    private View inflatedView;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static TextInputEditText nuovoUtenteEmailTextInputEditText, nuovoUtenteNomeTextInputEditText, nuovoUtentePasswordTextInputEditText;
    private Spinner tipologiaUtenteSpinner;
    private Button creaUtenteButton;
    private AlertDialog creazioneUtenteAlertDialog, uscitaCreazioneUtenteAlertDialog;
    private Controller controllerAdmin;

    public CreaUtenteFragment() {
        // Required empty public constructor
    }
    public static CreaUtenteFragment newInstance() {
        CreaUtenteFragment fragment = new CreaUtenteFragment();
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

        inflatedView = inflater.inflate(R.layout.fragment_crea_utente, container, false);
        controllerAdmin = new Controller(getActivity().toString());

        nuovoUtenteNomeTextInputEditText = inflatedView.findViewById(R.id.nuovoUtenteNomeTextInputEditText);
        nuovoUtenteNomeTextInputEditText.setSaveEnabled(false);

        nuovoUtenteEmailTextInputEditText = inflatedView.findViewById(R.id.nuovoUtenteEmailTextInputEditText);
        nuovoUtenteEmailTextInputEditText.setSaveEnabled(false);

        nuovoUtentePasswordTextInputEditText = inflatedView.findViewById(R.id.nuovoUtentePasswordTextInputEditText);
        nuovoUtentePasswordTextInputEditText.setSaveEnabled(false);

        tipologiaUtenteSpinner = inflatedView.findViewById(R.id.tipologiaUtenteSpinner);
        tipologiaUtenteSpinner.setSaveEnabled(false);

        creaUtenteButton = inflatedView.findViewById(R.id.creaUtenteButton);

        uscitaCreazioneUtenteAlertDialog = creaUscitaCreazioneUtenteAlertDialog();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tipologia_utente_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaUtenteSpinner.setAdapter(adapter);

        tipologiaUtenteSpinner.setSelection(adapter.getPosition("Supervisore"));

        creazioneUtenteAlertDialog = creaCreazioneUtenteAlertDialog();
        creaUtenteButton.setOnClickListener(view -> {
            creazioneUtenteAlertDialog.show();
        });


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setEnabled(false);
                if(!(nuovoUtenteNomeTextInputEditText.getText().toString().equals("") &&
                        nuovoUtenteEmailTextInputEditText.getText().toString().equals("") &&
                        nuovoUtentePasswordTextInputEditText.getText().toString().equals(""))){
                    uscitaCreazioneUtenteAlertDialog.show();
                }

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
                        if(controlloCampiCreazioneUtente(nomeUtente, email, password, tipologiaUtente)){
                            if(tipologiaUtente.equals("Supervisore")){
                                controllerAdmin.salvaSupervisore(nomeUtente, email, password, getActivity());
                                clearCampi();
                            }else if(tipologiaUtente.equals("Addetto alla sala")){
                                controllerAdmin.salvaAddettoSala(nomeUtente, email, password, getActivity());
                                clearCampi();
                            }
                        }else{
                            //Toast.makeText(getActivity(), "Email non corretta", Toast.LENGTH_SHORT).show();
                        }

                        /*if(validate(email)){
                            if(tipologiaUtente.equals("Supervisore")){
                                controllerAdmin.salvaSupervisore(nomeUtente, email, password, getActivity());
                                clearCampi();
                            }else if(tipologiaUtente.equals("Addetto alla sala")){
                                controllerAdmin.salvaAddettoSala(nomeUtente, email, password, getActivity());
                                clearCampi();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Email non corretta", Toast.LENGTH_SHORT).show();
                        }*/

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

    private boolean controlloCampiCreazioneUtente(String nomeUtente, String email, String password, String tipologiaUtente){

        if(nomeUtente == null || email == null || password == null || tipologiaUtente == null){
            Toast.makeText(getActivity(), "Qualcosa è andato storto", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!(VALID_EMAIL_ADDRESS_REGEX.matcher(email)).matches()){
            Toast.makeText(getActivity(), "Email non corretta", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(nomeUtente.equals("") || email.equals("") || password.equals("") || tipologiaUtente.equals("")){
            Toast.makeText(getActivity(), "Compilare bene i campi", Toast.LENGTH_SHORT).show();
            return false;
        }



        return true;


    }

    private void clearCampi() {
        nuovoUtenteNomeTextInputEditText.setText("");
        nuovoUtenteEmailTextInputEditText.setText("");
        nuovoUtentePasswordTextInputEditText.setText("");
    }

    private boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
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

    public static boolean checkTextView(){
        return nuovoUtenteNomeTextInputEditText.getText().toString().equals("") &&
                nuovoUtenteEmailTextInputEditText.getText().toString().equals("") &&
                nuovoUtentePasswordTextInputEditText.getText().toString().equals("");
    }

}