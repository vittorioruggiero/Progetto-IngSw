package com.example.ratatouille23.UI.fragment;

import static com.example.ratatouille23.UI.activity.LoginActivity.getSupervisore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.ratatouille23.adapter.MenuRecyclerAdapter;
import com.example.ratatouille23.adapter.SingoliOrdiniAdapter;
import com.example.ratatouille23.entity.Attivita;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.SezioneMenu;
import com.example.ratatouille23.entity.SingoloOrdine;
import com.example.ratatouille23.entity.Supervisore;

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
    private ArrayList<Integer> tavoli = new ArrayList<>();
    private List<SingoloOrdine> singoliOrdini = new ArrayList<>();
    private SingoliOrdiniAdapter singoliOrdiniAdapter;
    private RecyclerView recyclerView;
    private Button visualizzaContoButton, chiudiContoButton;
    private AlertDialog chiusuraContoAlertDialog;
    private Supervisore supervisore = getSupervisore();

    public ContiFragment() {
        // Required empty public constructor
    }
    public static ContiFragment newInstance() {
        ContiFragment fragment = new ContiFragment();
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
        inflatedView = inflater.inflate(R.layout.fragment_conti, container, false);

        controller = new Controller(getActivity().toString());

        //findViewById

        visualizzaContoButton = inflatedView.findViewById(R.id.visualizzaContoButton);
        chiudiContoButton = inflatedView.findViewById(R.id.chiudiContoButton);
        selezionaTavoloSpinner = inflatedView.findViewById(R.id.selezionaTavoloSpinner);
        numeroCommensaliCifraTextView = inflatedView.findViewById(R.id.numeroCommensaliCifraTextView);
        totaleCifraTextView = inflatedView.findViewById(R.id.totaleCifraTextView);
        recyclerView = inflatedView.findViewById(R.id.contiRecyclerView);

        if(supervisore.getNomeAttivita() != null){
            String nome = supervisore.getNomeAttivita();
            String indirizzo = supervisore.getIndirizzoAttivita();
            controller.checkAttivitaSupervisore(nome, indirizzo, this);
        }else{
            Toast.makeText(getActivity(), "Errore! Contattare l'amministratore", Toast.LENGTH_SHORT).show();
        }

        selezionaTavoloSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //controller.setOrdinazioneInConti(ContiFragment.this, getSupervisore().getNomeAttivita(), getSupervisore().getIndirizzoAttivita(), i+1);
                controller.getOrdinazioneByTavolo(ContiFragment.this, supervisore.getNomeAttivita(), supervisore.getIndirizzoAttivita(), i+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



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

        visualizzaContoButton.setOnClickListener(view -> {

            if(ordinazione != null){
                sostituisciFragment(preparaBundle(ordinazione.getListaProdotti()));
            }else{
                Toast.makeText(getActivity(), "Nessuna ordinazione!", Toast.LENGTH_SHORT).show();
            }
        });

        chiudiContoButton.setOnClickListener(view -> {

            //controller.chiusuraConto(Integer.parseInt(selezionaTavoloSpinner.getSelectedItem().toString()), ContiFragment.this, supervisore.getNomeAttivita(), supervisore.getIndirizzoAttivita());

            if(ordinazione != null){
                chiusuraContoAlertDialog.show();
            }else{
                Toast.makeText(getActivity(), "Nessuna ordinazione!", Toast.LENGTH_SHORT).show();
            }
        });

        return inflatedView;
    }

    public Spinner getSelezionaTavoloSpinner() {
        return selezionaTavoloSpinner;
    }

    public void setTextView(String numeroCommensali, String totale) {
        numeroCommensaliCifraTextView.setText(numeroCommensali);
        totaleCifraTextView.setText(totale);
    }

    public void setSingoliOrdiniRecyclerAdapter(List<SingoloOrdine> singoliOrdini){
        this.singoliOrdini = singoliOrdini;
        singoliOrdiniAdapter = new SingoliOrdiniAdapter(this.singoliOrdini);
        recyclerView.setAdapter(singoliOrdiniAdapter);
    }

    public void notifyDataChanged(){
        singoliOrdiniAdapter.notifyDataSetChanged();
    }

    public void setChiusuraContoAlertDialog(AlertDialog alertDialog) {
        chiusuraContoAlertDialog = alertDialog;
    }

    public AlertDialog creaChiusuraContoAlertDialog(List<SingoloOrdine> listaProdotti, SingoliOrdiniAdapter singoliOrdiniAdapter) {

        AlertDialog.Builder chiusuraContoAlertDialogBuilder = new AlertDialog.Builder(getContext());
        chiusuraContoAlertDialogBuilder.setMessage("Sei sicuro di voler chiudere il conto?");
        chiusuraContoAlertDialogBuilder.setCancelable(false);

        chiusuraContoAlertDialogBuilder.setPositiveButton(
                "Conferma",
                (dialog, id) -> {
                    sostituisciFragment(preparaBundle(listaProdotti));
                    ordinazione.setNumeroCommensali(0);
                    ordinazione.setNumeroTavolo(0);
                    listaProdotti.clear();
                    singoliOrdiniAdapter.notifyDataSetChanged();
                    dialog.cancel();
                });

        chiusuraContoAlertDialogBuilder.setNegativeButton(
                "Annulla",
                (dialog, id) -> dialog.cancel());

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

    public void setTavoliAttivita(Attivita attivita) {
        if(attivita != null){
            for(int i = 1; i <= attivita.getCapienza(); i++){
                tavoli.add(i);
            }
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, tavoli);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            selezionaTavoloSpinner.setAdapter(adapter);

        }
    }
}