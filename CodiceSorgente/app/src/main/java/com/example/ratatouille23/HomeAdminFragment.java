package com.example.ratatouille23;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeAdminFragment extends Fragment {
    FloatingActionButton modificaButton, selezionaFotoButton;
    ImageView foto;
    EditText nomeAttivitaEditText, luogoAttivitaEditText, capienzaAttivitaEditText, telefonoAttivitaEditText;
    boolean isEditing = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_admin, container, false);

        selezionaFotoButton = (FloatingActionButton) v.findViewById(R.id.selezionaFotoButton);
        modificaButton = (FloatingActionButton) v.findViewById(R.id.modificaButton);
        foto = (ImageView) v.findViewById(R.id.foto);
        nomeAttivitaEditText = (EditText) v.findViewById(R.id.nomeAttivitaEditText);
        nomeAttivitaEditText.setBackgroundColor(Color.TRANSPARENT);
        luogoAttivitaEditText = (EditText) v.findViewById(R.id.indirizzoAttivitaEditText);
        //luogoAttivitaEditText.setBackgroundColor(Color.TRANSPARENT);
        telefonoAttivitaEditText = (EditText) v.findViewById(R.id.telefonoAttivitaEditText);
        //telefonoAttivitaEditText.setBackgroundColor(Color.TRANSPARENT);
        capienzaAttivitaEditText = (EditText) v.findViewById(R.id.capienzaAttivitaEditText);
        //capienzaAttivitaEditText.setBackgroundColor(Color.TRANSPARENT);
        selezionaFotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scegliImmagine();
            }
        });

        modificaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nomeAttivitaEditText.isEnabled()){
                    nomeAttivitaEditText.setEnabled(false);
                    luogoAttivitaEditText.setEnabled(false);
                    capienzaAttivitaEditText.setEnabled(false);
                    telefonoAttivitaEditText.setEnabled(false);
                    selezionaFotoButton.setVisibility(View.INVISIBLE);
                }else{
                    nomeAttivitaEditText.setEnabled(true);
                    luogoAttivitaEditText.setEnabled(true);
                    telefonoAttivitaEditText.setEnabled(true);
                    capienzaAttivitaEditText.setEnabled(true);
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
                };
            });


}