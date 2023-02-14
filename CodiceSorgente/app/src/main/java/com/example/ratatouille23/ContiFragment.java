package com.example.ratatouille23;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContiFragment extends Fragment {

    private View inflatedView;

    private Spinner selezionaTavoloSpinner;

    private Button chiudiContoButton;

    private AlertDialog chiusuraContoAlertDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContiFragment newInstance(String param1, String param2) {
        ContiFragment fragment = new ContiFragment();
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
        inflatedView = inflater.inflate(R.layout.fragment_conti, container, false);

        chiudiContoButton = inflatedView.findViewById(R.id.chiudiContoButton);
        selezionaTavoloSpinner = inflatedView.findViewById(R.id.selezionaTavoloSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tavoli_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selezionaTavoloSpinner.setAdapter(adapter);

        selezionaTavoloSpinner.setSelection(adapter.getPosition("Tavolo 1"));

        chiusuraContoAlertDialog = creaChiusuraContoAlertDialog();

        chiudiContoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chiusuraContoAlertDialog.show();
            }
        });

        return inflatedView;
    }

    AlertDialog creaChiusuraContoAlertDialog() {

        AlertDialog.Builder chiusuraContoAlertDialogBuilder = new AlertDialog.Builder(getContext());
        chiusuraContoAlertDialogBuilder.setMessage("Sei sicuro di voler chiudere il conto?");
        chiusuraContoAlertDialogBuilder.setCancelable(false);

        chiusuraContoAlertDialogBuilder.setPositiveButton(
                "Conferma",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        chiusuraContoAlertDialogBuilder.setNegativeButton(
                "Annulla",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return chiusuraContoAlertDialogBuilder.create();
    }
}