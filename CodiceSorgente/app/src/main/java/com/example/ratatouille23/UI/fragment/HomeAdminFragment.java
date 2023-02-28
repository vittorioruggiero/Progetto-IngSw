package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.fragment.HomeAddettoSalaFragment.getAvvisiAddettoSala;
import static com.example.ratatouille23.UI.fragment.HomeSupervisoreFragment.getAvvisiSupervisore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.entity.Attivita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAdminFragment extends Fragment {
    private FloatingActionButton modificaButton, selezionaFotoButton;
    private ImageView foto;
    private EditText nomeAttivitaEditText, luogoAttivitaEditText, capienzaAttivitaEditText, telefonoAttivitaEditText;
    private AlertDialog inserisciAvvisoAlertDialog, confermaCreazioneAvvisoAlertDialog;
    private Button creaAvvisoButton;
    private static Attivita attivita;
    private boolean isEditing = false;

    public HomeAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeAdminFragment newInstance(String param1, String param2) {
        HomeAdminFragment fragment = new HomeAdminFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_admin, container, false);

        selezionaFotoButton = (FloatingActionButton) v.findViewById(R.id.selezionaFotoButton);
        modificaButton = (FloatingActionButton) v.findViewById(R.id.opzioniButton);
        foto = (ImageView) v.findViewById(R.id.foto);
        nomeAttivitaEditText = (EditText) v.findViewById(R.id.nomeAttivitaEditText);
        nomeAttivitaEditText.setBackgroundColor(Color.TRANSPARENT);
        luogoAttivitaEditText = (EditText) v.findViewById(R.id.indirizzoAttivitaEditText);
        telefonoAttivitaEditText = (EditText) v.findViewById(R.id.telefonoAttivitaEditText);
        capienzaAttivitaEditText = (EditText) v.findViewById(R.id.capienzaAttivitaEditText);

        inserisciAvvisoAlertDialog = creaInserisciAvvisoAlertDialog();
        creaAvvisoButton = (Button) v.findViewById(R.id.creaAvvisoButton);

        selezionaFotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scegliImmagine();
            }
        });

        modificaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    attivita = new Attivita(nomeAttivita, indirizzoAttivita, telefonoAttivita, capienzaAttivita);
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
            }
        });

        creaAvvisoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserisciAvvisoAlertDialog.show();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //Provvisorio: fatto per non dover riavviare l'applicazione per tornare alla LoginActivity
                Intent intent = new Intent(getContext(), LoginActivity.class);
                HomeAdminFragment.this.startActivity(intent);
                //Esce dall'applicazione come il bottone centrale di Android
//                getActivity().moveTaskToBack(true);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        return v;
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
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String avviso = testoAvvisoEditText.getText().toString();
                        sendAvvisi(avviso);
                        testoAvvisoEditText.setText("");
                        dialog.cancel();
                    }
                });

        inserisciAvvisoAlertDialogBuilder.setNegativeButton(
                "Annulla",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return inserisciAvvisoAlertDialogBuilder.create();
    }

    /*AlertDialog creaCreazioneAvvisoAlertDialog() {

        AlertDialog.Builder creazioneAvvisoAlertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        creazioneAvvisoAlertDialogBuilder.setMessage("Sei sicuro di voler creare l'avviso?");

        creazioneAvvisoAlertDialogBuilder.setPositiveButton(
                "Conferma",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        creazioneAvvisoAlertDialogBuilder.setNegativeButton(
                "Annulla",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        inserisciAvvisoAlertDialog.show();
                    }
                });

        return creazioneAvvisoAlertDialogBuilder.create();
    }*/
    public static Attivita getAttivita(){
        return attivita;
    }

    private void sendAvvisi(String avviso){
        getAvvisiAddettoSala().add(avviso);
        getAvvisiSupervisore().add(avviso);
    }

}