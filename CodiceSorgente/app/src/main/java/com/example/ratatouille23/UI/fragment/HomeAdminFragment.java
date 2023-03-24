package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.clearAll;
import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.Avviso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeAdminFragment extends Fragment {
    private FloatingActionButton modificaButton, selezionaFotoButton;
    private ImageView foto;
    private EditText nomeAttivitaEditText, luogoAttivitaEditText, capienzaAttivitaEditText, telefonoAttivitaEditText;
    private AlertDialog inserisciAvvisoAlertDialog;
    private static Amministratore amministratore = getAdmin();
    private Button creaAvvisoButton, logoutButton;
    private static Attivita attivita;
    private Controller controllerAdmin;

    private boolean isEditing = false;

    public HomeAdminFragment() {
        // Required empty public constructor
    }

    public static HomeAdminFragment newInstance() {
        HomeAdminFragment fragment = new HomeAdminFragment();
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

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_admin, container, false);

        selezionaFotoButton = v.findViewById(R.id.selezionaFotoButton);
        modificaButton = v.findViewById(R.id.opzioniButton);
        foto = v.findViewById(R.id.foto);
        nomeAttivitaEditText = v.findViewById(R.id.nomeAttivitaEditText);
        nomeAttivitaEditText.setBackgroundColor(Color.TRANSPARENT);
        luogoAttivitaEditText = v.findViewById(R.id.indirizzoAttivitaEditText);
        telefonoAttivitaEditText = v.findViewById(R.id.telefonoAttivitaEditText);
        capienzaAttivitaEditText = v.findViewById(R.id.capienzaAttivitaEditText);
        creaAvvisoButton = v.findViewById(R.id.creaAvvisoButton);
        logoutButton = v.findViewById(R.id.logoutButton);
        controllerAdmin = new Controller(getActivity().toString());

        if(amministratore.getNomeAttivita() != null){
            String nome = amministratore.getNomeAttivita();
            String indirizzo = amministratore.getIndirizzoAttivita();
            controllerAdmin.checkAttivita(nome, indirizzo, this);
        }else{
            Toast.makeText(getActivity(), "Inserire dettagli attività", Toast.LENGTH_SHORT).show();
        }

        inserisciAvvisoAlertDialog = creaInserisciAvvisoAlertDialog();

        selezionaFotoButton.setOnClickListener(view -> scegliImmagine());

        logoutButton.setOnClickListener(view -> {
            Intent loginscreen = new Intent(getActivity(), LoginActivity.class);
            clearAll();
            startActivity(loginscreen);
            getActivity().finish();
        });

        modificaButton.setOnClickListener(view -> {

            String nomeAttivita;
            String indirizzoAttivita;
            String telefonoAttivita;
            int capienzaAttivita;


            if(nomeAttivitaEditText.isFocusable()){
                nomeAttivitaEditText.setFocusableInTouchMode(false);
                nomeAttivitaEditText.setFocusable(false);
                luogoAttivitaEditText.setFocusable(false);
                luogoAttivitaEditText.setFocusableInTouchMode(false);
                capienzaAttivitaEditText.setFocusable(false);
                capienzaAttivitaEditText.setFocusableInTouchMode(false);
                telefonoAttivitaEditText.setFocusable(false);
                telefonoAttivitaEditText.setFocusableInTouchMode(false);
                selezionaFotoButton.setVisibility(View.INVISIBLE);
                nomeAttivita = nomeAttivitaEditText.getText().toString();
                indirizzoAttivita = luogoAttivitaEditText.getText().toString();
                telefonoAttivita = telefonoAttivitaEditText.getText().toString();
                try {
                    capienzaAttivita = Integer.parseInt(capienzaAttivitaEditText.getText().toString());
                }catch(NumberFormatException e){
                    capienzaAttivita = 0;
                }
                if(nomeAttivita.equals("") || indirizzoAttivita.equals("") || telefonoAttivita.equals("")
                        || capienzaAttivita == 0){
                    Toast.makeText(getActivity(), "Inserisci tutti i dettagli correttamente", Toast.LENGTH_SHORT).show();
                }else{
                    attivita = new Attivita(nomeAttivita, indirizzoAttivita, telefonoAttivita, capienzaAttivita);
                    controllerAdmin.salvaAttivitaEdAdmin(attivita, getActivity());
                }
            }else{
                nomeAttivitaEditText.setFocusableInTouchMode(true);
                nomeAttivitaEditText.setFocusable(true);
                luogoAttivitaEditText.setFocusable(true);
                luogoAttivitaEditText.setFocusableInTouchMode(true);
                capienzaAttivitaEditText.setFocusable(true);
                capienzaAttivitaEditText.setFocusableInTouchMode(true);
                telefonoAttivitaEditText.setFocusable(true);
                telefonoAttivitaEditText.setFocusableInTouchMode(true);
                selezionaFotoButton.setVisibility(View.VISIBLE);
            }
            if(isEditing){
                modificaButton.setImageResource(R.drawable.edit_icon);
            }else{
                modificaButton.setImageResource(R.drawable.check_icon);
            }
            isEditing = !isEditing;
        });

        creaAvvisoButton.setOnClickListener(view -> inserisciAvvisoAlertDialog.show());

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //Esce dall'applicazione come il bottone centrale di Android
                getActivity().moveTaskToBack(true);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return v;
    }

    public void setAttivita(Attivita attivitaBody){
        attivita = attivitaBody;
        nomeAttivitaEditText.setText(attivita.getNome());
        luogoAttivitaEditText.setText(attivita.getIndirizzo());
        capienzaAttivitaEditText.setText(String.valueOf(attivita.getCapienza()));
        telefonoAttivitaEditText.setText(attivita.getTelefono());
    }

    private void scegliImmagine()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startForMediaPickerResult.launch(i);
    }

    private final ActivityResultLauncher<Intent> startForMediaPickerResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Intent data = result.getData();
                if (data != null && result.getResultCode() == Activity.RESULT_OK) {
                    Uri resultUri = data.getData();
                    foto.setImageURI(resultUri);
                    Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + resultUri);
                }
            });

    AlertDialog creaInserisciAvvisoAlertDialog() {

        AlertDialog.Builder inserisciAvvisoAlertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        inserisciAvvisoAlertDialogBuilder.setTitle("Avviso");
        inserisciAvvisoAlertDialogBuilder.setMessage("Inserisci testo");

        //confermaCreazioneAvvisoAlertDialog = creaCreazioneAvvisoAlertDialog();

        final EditText testoAvvisoEditText = new EditText(getActivity());
        inserisciAvvisoAlertDialogBuilder.setView(testoAvvisoEditText);

        inserisciAvvisoAlertDialogBuilder.setPositiveButton(
                "Conferma",
                    (dialog, id) -> {
                    if(testoAvvisoEditText.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "Inserire testo avviso", Toast.LENGTH_SHORT).show();
                    }else{
                        Avviso avviso = new Avviso();
                        avviso.setAvviso(testoAvvisoEditText.getText().toString());
                        avviso.setNomeAttivita(amministratore.getNomeAttivita());
                        avviso.setIndirizzoAttivita(amministratore.getIndirizzoAttivita());
                        controllerAdmin.salvaAvvisoSupervisore(avviso, getActivity());
                        controllerAdmin.salvaAvvisoAddettoSala(avviso, getActivity());
                        //sendAvvisi(avviso);
                        testoAvvisoEditText.setText("");
                        dialog.cancel();
                    }

                });

        inserisciAvvisoAlertDialogBuilder.setNegativeButton(
                "Annulla",
                (dialog, id) -> dialog.cancel());

        return inserisciAvvisoAlertDialogBuilder.create();
    }

}