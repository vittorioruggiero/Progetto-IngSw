package com.example.ratatouille23.UI.fragment;

import static androidx.navigation.Navigation.findNavController;

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

import com.example.ratatouille23.R;
import com.google.android.material.textfield.TextInputEditText;

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

        View inflatedView = inflater.inflate(R.layout.fragment_crea_utente, container, false);

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
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        creazioneUtenteAlertDialogBuilder.setNegativeButton(
                "Annulla",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return creazioneUtenteAlertDialogBuilder.create();
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