package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.clearAll;
import static com.example.ratatouille23.UI.activity.LoginActivity.getAdmin;
import static com.example.ratatouille23.UI.fragment.HomeAddettoSalaFragment.addAvvisiAddettoSala;
import static com.example.ratatouille23.UI.fragment.HomeSupervisoreFragment.addAvvisoSupervisore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.UI.activity.LoginActivity;
import com.example.ratatouille23.entity.Amministratore;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.AttivitaPkey;
import com.example.ratatouille23.entity.Avviso;
import com.example.ratatouille23.retrofit.API.AmministratoreAPI;
import com.example.ratatouille23.retrofit.API.AttivitaAPI;
import com.example.ratatouille23.retrofit.API.AvvisoAPI;
import com.example.ratatouille23.retrofit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeAdminFragment extends Fragment {
    private FloatingActionButton modificaButton, selezionaFotoButton;
    private ImageView foto;
    private EditText nomeAttivitaEditText, luogoAttivitaEditText, capienzaAttivitaEditText, telefonoAttivitaEditText;
    private AttivitaAPI attivitaAPI;
    private AvvisoAPI avvisoAPI;
    private RetrofitService retrofitService;
    private AlertDialog inserisciAvvisoAlertDialog;
    private static Amministratore amministratore = getAdmin();
    private AmministratoreAPI amministratoreAPI;
    private Button creaAvvisoButton, logoutButton;
    private static Attivita attivita;
    private Bitmap bitmap;

    private boolean isEditing = false;

    public HomeAdminFragment() {
        // Required empty public constructor
    }

    public static HomeAdminFragment newInstance(String param1, String param2) {
        HomeAdminFragment fragment = new HomeAdminFragment();
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

        retrofitService = new RetrofitService();

        attivitaAPI = retrofitService.getRetrofit().create(AttivitaAPI.class);
        avvisoAPI = retrofitService.getRetrofit().create(AvvisoAPI.class);
        amministratoreAPI = retrofitService.getRetrofit().create(AmministratoreAPI.class);

        if(!(amministratore.getNomeAttivita() == null)){
            String nome = amministratore.getNomeAttivita();
            String indirizzo = amministratore.getIndirizzoAttivita();
            checkAttivita(nome, indirizzo);
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
                    if(amministratore.getNomeAttivita() == null || !(amministratore.getNomeAttivita().equals(attivita.getNome()))){
                        amministratore.setNomeAttivita(nomeAttivita);
                        amministratore.setIndirizzoAttivita(indirizzoAttivita);
                        salvaAdmin(amministratore);
                    }
                    salvaAttivita(attivita);
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

        creaAvvisoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserisciAvvisoAlertDialog.show();
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

        return v;
    }

    private void salvaAdmin(Amministratore amministratore) {

        amministratoreAPI.salvataggioAdmin(amministratore)
                .enqueue(new Callback<Amministratore>() {
                    @Override
                    public void onResponse(Call<Amministratore> call, Response<Amministratore> response) {

                    }

                    @Override
                    public void onFailure(Call<Amministratore> call, Throwable t) {

                    }
                });

    }

    private void checkAttivita(String nome, String indirizzo){

        attivitaAPI.getAttivitaById(nome, indirizzo)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        if(response.body() != null){
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body());
                            attivita = response.body();
                            nomeAttivitaEditText.setText(attivita.getNome());
                            luogoAttivitaEditText.setText(attivita.getIndirizzo());
                            capienzaAttivitaEditText.setText(String.valueOf(attivita.getCapienza()));
                            telefonoAttivitaEditText.setText(attivita.getTelefono());
                        }else{
                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Attivita> call, Throwable t) {
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                    }
                });

    }

    private void salvaAttivita(Attivita nuovaAttivita) {

        attivitaAPI.save(nuovaAttivita)
                .enqueue(new Callback<Attivita>() {
                    @Override
                    public void onResponse(Call<Attivita> call, Response<Attivita> response) {
                        Toast.makeText(getActivity(), "Salvataggio Completato Correttamente", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Attivita> call, Throwable t) {
                        Toast.makeText(getActivity(), "Salvataggio Fallito", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error", t);
                    }
                });
        //salvaImmagine();

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

    /*private void scegliImmagine2(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResultLauncher.launch(intent);
    }


    private final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                            foto.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

    private void salvaImmagine() {
        ByteArrayOutputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayOutputStream();
        if(bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

        }else{
            Toast.makeText(getActivity(), "Seleziona la foto da salvare", Toast.LENGTH_SHORT).show();
        }
    }*/




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
                        Avviso avviso = new Avviso(testoAvvisoEditText.getText().toString());
                        avvisoAPI.salvataggioAvviso(avviso)
                                .enqueue(new Callback<Avviso>() {
                                    @Override
                                    public void onResponse(Call<Avviso> call, Response<Avviso> response) {
                                        if(response.body() != null){
                                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "OK: " + response.body().toString());
                                        }else{
                                            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: " + response.body());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Avviso> call, Throwable t) {
                                        Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "Error: ", t);
                                        Toast.makeText(getContext(), "Server Spento", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        //sendAvvisi(avviso);
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

    private void sendAvvisi(Avviso avviso){
        addAvvisiAddettoSala(avviso);
        addAvvisoSupervisore(avviso);
    }

}