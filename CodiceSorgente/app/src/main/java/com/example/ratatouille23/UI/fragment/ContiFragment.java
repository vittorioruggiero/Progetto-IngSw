package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.adapter.SingoliOrdiniAdapter;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.SingoloOrdine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContiFragment extends Fragment {

    private Controller controller;
    private View inflatedView;
    private FragmentTransaction transaction;
    private TextView numeroCommensaliCifraTextView, totaleCifraTextView;
    private Spinner selezionaTavoloSpinner;
    private Ordinazione ordinazione;
    private ArrayList<Integer> tavoli;
    SingoliOrdiniAdapter singoliOrdiniAdapter;
    RecyclerView recyclerView;
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

        controller = new Controller(getActivity().toString());

        //findViewById

        visualizzaContoButton = inflatedView.findViewById(R.id.visualizzaContoButton);
        chiudiContoButton = inflatedView.findViewById(R.id.chiudiContoButton);
        selezionaTavoloSpinner = inflatedView.findViewById(R.id.selezionaTavoloSpinner);
        numeroCommensaliCifraTextView = inflatedView.findViewById(R.id.numeroCommensaliCifraTextView);
        totaleCifraTextView = inflatedView.findViewById(R.id.totaleCifraTextView);
        recyclerView = inflatedView.findViewById(R.id.contiRecyclerView);

        controller.setOrdinazioneinConto(ContiFragment.this, getSupervisore().getNomeAttivita(), getSupervisore().getIndirizzoAttivita());

//        ordinazione = getOrdinazione();
//        tavoli = getTavoli();
//
//        //set selezionaTavoloSpinner
//
//        if(tavoli != null){
//            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(),
//                    android.R.layout.simple_spinner_item, tavoli);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            selezionaTavoloSpinner.setAdapter(adapter);
//        }
//
//        if(ordinazione != null){
//            numeroCommensaliCifraTextView.setText(String.valueOf(ordinazione.getNumeroCommensali()));
//            totaleCifraTextView.setText(String.valueOf(ordinazione.calcolaTotale()));
//
//            singoliOrdiniAdapter = new SingoliOrdiniAdapter(ordinazione.getListaProdotti());
//            recyclerView.setAdapter(singoliOrdiniAdapter);
//
//            chiusuraContoAlertDialog = creaChiusuraContoAlertDialog(ordinazione.getListaProdotti(), singoliOrdiniAdapter);
//        }

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

    public Spinner getSelezionaTavoloSpinner() {
        return selezionaTavoloSpinner;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setTextView(String numeroCommensali, String totale) {
        numeroCommensaliCifraTextView.setText(numeroCommensali);
        totaleCifraTextView.setText(totale);
    }

    public AlertDialog creaChiusuraContoAlertDialog(List<SingoloOrdine> listaProdotti, SingoliOrdiniAdapter singoliOrdiniAdapter) {

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
            listaQuantitaProdotti.add(String.valueOf(currentSingoloOrdine.getQuantita()));
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