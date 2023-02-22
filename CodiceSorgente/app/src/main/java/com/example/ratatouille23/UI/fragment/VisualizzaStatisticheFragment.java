package com.example.ratatouille23.UI.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ratatouille23.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;

public class VisualizzaStatisticheFragment extends Fragment {

    private Button bottoneIntervallo;

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

        bottoneIntervallo = (Button) v.findViewById(R.id.adminButtonIntervallo);

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().
                setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())).build();

        bottoneIntervallo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(),getTag());
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        bottoneIntervallo.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });
        return v;
    }

}