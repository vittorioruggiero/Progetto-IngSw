package com.example.ratatouille23.UI.fragment;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import static com.example.ratatouille23.UI.fragment.OrdinazioniFragment.getOrdinazione;
import static com.example.ratatouille23.UI.fragment.OrdinazioniFragment.getTavoli;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
    private Ordinazione ordinazione;
    private ArrayList<Integer> tavoli;

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

        //findViewById

        visualizzaContoButton = inflatedView.findViewById(R.id.visualizzaContoButton);
        chiudiContoButton = inflatedView.findViewById(R.id.chiudiContoButton);
        selezionaTavoloSpinner = inflatedView.findViewById(R.id.selezionaTavoloSpinner);
        numeroCommensaliCifraTextView = inflatedView.findViewById(R.id.numeroCommensaliCifraTextView);
        totaleCifraTextView = inflatedView.findViewById(R.id.totaleCifraTextView);

        ordinazione = getOrdinazione();
        tavoli = getTavoli();

        //set selezionaTavoloSpinner

        if(tavoli != null){
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, tavoli);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selezionaTavoloSpinner.setAdapter(adapter);
        }

        SingoliOrdiniAdapter singoliOrdiniAdapter;
        RecyclerView recyclerView;

        if(ordinazione != null){
            numeroCommensaliCifraTextView.setText(String.valueOf(ordinazione.getNumeroCommensali()));
            totaleCifraTextView.setText(String.valueOf(ordinazione.calcolaTotale()));
            recyclerView = inflatedView.findViewById(R.id.contiRecyclerView);
            singoliOrdiniAdapter = new SingoliOrdiniAdapter(ordinazione.getListaProdotti());
            recyclerView.setAdapter(singoliOrdiniAdapter);
            chiusuraContoAlertDialog = creaChiusuraContoAlertDialog(ordinazione.getListaProdotti(), singoliOrdiniAdapter);
        }
        //set RecyclerView

        //List<SingoloOrdine> listaProdotti = new ArrayList<>();

        //setOggettiDiProva(listaProdotti);

        //set Listener e AlertDialog



        visualizzaContoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ordinazione != null){
                    sostituisciFragment(preparaBundle(ordinazione.getListaProdotti()));
                }else{
                    Toast.makeText(getActivity(), "Nessuna ordinazione!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chiudiContoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ordinazione != null){
                    chiusuraContoAlertDialog.show();
                }else{
                    Toast.makeText(getActivity(), "Nessuna ordinazione!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return inflatedView;
    }

    AlertDialog creaChiusuraContoAlertDialog(List<SingoloOrdine> listaProdotti, SingoliOrdiniAdapter singoliOrdiniAdapter) {

        AlertDialog.Builder chiusuraContoAlertDialogBuilder = new AlertDialog.Builder(getContext());
        chiusuraContoAlertDialogBuilder.setMessage("Sei sicuro di voler chiudere il conto?");
        chiusuraContoAlertDialogBuilder.setCancelable(false);

        chiusuraContoAlertDialogBuilder.setPositiveButton(
                "Conferma",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sostituisciFragment(preparaBundle(listaProdotti));
                        ordinazione.setNumeroCommensali(0);
                        ordinazione.setNumeroTavolo(0);
                        listaProdotti.clear();
                        singoliOrdiniAdapter.notifyDataSetChanged();
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

    public Bundle preparaBundle(List<SingoloOrdine> listaProdotti) {
        Bundle bundle = new Bundle();
        Iterator<SingoloOrdine> listaProdottiIterator = listaProdotti.iterator();
        ArrayList<String> listaNomiProdotti = new ArrayList<>();
        ArrayList<String> listaQuantitaProdotti = new ArrayList<>();
        SingoloOrdine currentSingoloOrdine;

        while(listaProdottiIterator.hasNext()) {
            currentSingoloOrdine = listaProdottiIterator.next();
            listaNomiProdotti.add(currentSingoloOrdine.getProdottoMenu().getNomeProdotto());
            listaQuantitaProdotti.add(String.valueOf(currentSingoloOrdine.getQuantitaProdotto()));
        }

        bundle.putString("numeroCommensali", String.valueOf(numeroCommensaliCifraTextView.getText()));
        bundle.putStringArrayList("listaNomiProdotti", listaNomiProdotti);
        bundle.putStringArrayList("listaQuantitaProdotti", listaQuantitaProdotti);
        bundle.putString("totale", String.valueOf(totaleCifraTextView.getText()));

        return bundle;
    }

    public void sostituisciFragment(Bundle bundle){
        Navigation.findNavController(inflatedView).navigate(R.id.visualizzaContoPDFFragment, bundle);
    }
}