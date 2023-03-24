package com.example.ratatouille23.UI.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ratatouille23.Controller.Controller;
import com.example.ratatouille23.R;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class VisualizzaStatisticheFragment extends Fragment {

    private Button dataInizioButton, dataFineButton, cercaButton;
    private java.sql.Date dataInizio, dataFine;
    private TextView incassoTotaleTextView, incassoMedioTextView;
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
        incassoTotaleTextView = v.findViewById(R.id.valoreIncassoTotaleTextView);
        incassoMedioTextView = v.findViewById(R.id.valoreIncassoMedioTextView);
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

        cercaButton.setOnClickListener(view -> controller.cercaContiPerDate(dataInizio, dataFine, VisualizzaStatisticheFragment.this));


        return v;
    }

    public void sostituisciFragment(Fragment fragment){
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.graficoFragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setTextView(String valoreTotale, String valoreMedio){
        incassoTotaleTextView.setText(valoreTotale);
        incassoMedioTextView.setText(valoreMedio);
    }

}