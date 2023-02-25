package com.example.ratatouille23.UI.fragment;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.SingoliOrdiniAdapter;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SingoloOrdine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContiFragment extends Fragment {

    private View inflatedView;

    private FragmentTransaction transaction;

    private TextView numeroCommensaliCifraTextView, totaleCifraTextView;

    private Spinner selezionaTavoloSpinner;

    private Button visualizzaContoButton, chiudiContoButton;

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

        visualizzaContoButton = inflatedView.findViewById(R.id.visualizzaContoButton);
        chiudiContoButton = inflatedView.findViewById(R.id.chiudiContoButton);
        selezionaTavoloSpinner = inflatedView.findViewById(R.id.selezionaTavoloSpinner);
        numeroCommensaliCifraTextView = inflatedView.findViewById(R.id.numeroCommensaliCifraTextView);
        totaleCifraTextView = inflatedView.findViewById(R.id.totaleCifraTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tavoli_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selezionaTavoloSpinner.setAdapter(adapter);

        selezionaTavoloSpinner.setSelection(adapter.getPosition("Tavolo 1"));

        chiusuraContoAlertDialog = creaChiusuraContoAlertDialog();

        visualizzaContoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                creaPDF();
                sostituisciFragment(new VisualizzaContoPDFFragment());
            }
        });

        chiudiContoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chiusuraContoAlertDialog.show();
            }
        });

        List<SingoloOrdine> listaProdotti = new ArrayList<>();

        setOggettiDiProva(listaProdotti);

        RecyclerView recyclerView = (RecyclerView) inflatedView.findViewById(R.id.contiRecyclerView);
        SingoliOrdiniAdapter singoliOrdiniAdapter = new SingoliOrdiniAdapter(listaProdotti);
        recyclerView.setAdapter(singoliOrdiniAdapter);

        return inflatedView;
    }

    void setOggettiDiProva(List<SingoloOrdine> listaProdotti) {

        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 1", 5), 3));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 2", 3), 2));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 3", 2), 1));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 1", 5), 3));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 2", 3), 2));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 3", 2), 1));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 1", 5), 3));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 2", 3), 2));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 3", 2), 1));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 1", 5), 3));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 2", 3), 2));
        listaProdotti.add(new SingoloOrdine(new ProdottoMenu("Prodotto 3", 2), 1));

        Ordinazione ordinazione = new Ordinazione(listaProdotti);
        double totale = ordinazione.calcolaTotale();
        totaleCifraTextView.setText(String.valueOf(totale));

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

    public void sostituisciFragment(Fragment fragment){
        Bundle result = new Bundle();
        result.putString("numeroCommensali", String.valueOf(numeroCommensaliCifraTextView.getText()));
        fragment.setArguments(result);

        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.supervisoreFragmentContainerView, fragment);
        transaction.hide(this);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}