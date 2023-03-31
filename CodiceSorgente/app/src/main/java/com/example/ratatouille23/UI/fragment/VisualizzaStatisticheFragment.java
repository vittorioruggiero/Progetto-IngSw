package com.example.ratatouille23.UI.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizzaStatisticheFragment extends Fragment {

    private Button dataInizioButton, dataFineButton, cercaButton;
    private java.sql.Date dataInizio, dataFine;
    private TextView valoreTextView;
    private Spinner tipologiaStatisticaSpinner;
    private Controller controller;
    private FragmentTransaction transaction;

    public VisualizzaStatisticheFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_visualizza_statistiche, container, false);

        cercaButton = v.findViewById(R.id.cercaButton);
        dataInizioButton = v.findViewById(R.id.dataInizioButton);
        dataFineButton = v.findViewById(R.id.dataFineButton);
        valoreTextView = v.findViewById(R.id.valoreTextView);

        tipologiaStatisticaSpinner = v.findViewById(R.id.tipologiaStatisticaSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.tipologia_statistica_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipologiaStatisticaSpinner.setAdapter(adapter);

        tipologiaStatisticaSpinner.setSelection(adapter.getPosition("Incassi totali"));


        controller = new Controller(getActivity().toString());



        dataInizioButton.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, year1, month1, day1) -> {
                month1 += 1;
                String dataInizioString = year1 + "-" + month1 + "-" + day1;
                java.util.Date formatter;
                try {
                    formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN).parse(dataInizioString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                dataInizio = new Date(formatter.getTime());
                dataInizioButton.setText(dataInizioString);
            },
                year, month, day);
            datePickerDialog.show();
        });

        dataFineButton.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), (datePicker, year12, month12, day12) -> {
                month12 += 1;
                String dataFineString = year12 + "-" + month12 + "-" + day12;
                java.util.Date formatter;
                try {
                    formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN).parse(dataFineString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                dataFine = new Date(formatter.getTime());
                dataFineButton.setText(dataFineString);
            },
                    year, month, day);
            datePickerDialog.show();
        });

        cercaButton.setOnClickListener(view -> {

            long timeDiff = Math.abs(dataFine.getTime() - dataInizio.getTime());
            long dayDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "GIORNI: " + dayDiff);

            controller.cercaContiPerDate(dataInizio, dataFine, VisualizzaStatisticheFragment.this, tipologiaStatisticaSpinner.getSelectedItem().toString());
        });


        return v;
    }

    public void sostituisciFragment(Fragment fragment){
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.graficoFragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setTextView(String valore){
        valoreTextView.setText("€" + valore);
    }

}