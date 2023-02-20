package com.example.ratatouille23.UI.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ratatouille23.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VisualizzaStatisticheFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisualizzaStatisticheFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView dataInizio, dataFine;
    private Button bottoneInizio, bottoneFine;
    private Calendar calendario, calendarioFine;
    private DatePickerDialog InizioIntervallo, FineIntervallo;

    public VisualizzaStatisticheFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VisualizzaStatisticheFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VisualizzaStatisticheFragment newInstance(String param1, String param2) {
        VisualizzaStatisticheFragment fragment = new VisualizzaStatisticheFragment();
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
        View v = inflater.inflate(R.layout.fragment_visualizza_statistiche, container, false);

        bottoneInizio = (Button) v.findViewById(R.id.adminButtonDataInizioIntervallo);
        dataInizio = (TextView) v.findViewById(R.id.adminTestoDataInizioIntervallo);
        dataInizio.setVisibility(View.INVISIBLE);
        bottoneInizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendario= Calendar.getInstance();
                int giorno = calendario.get(Calendar.DAY_OF_MONTH);
                int mese = calendario.get(Calendar.MONTH);
                int anno = calendario.get(Calendar.YEAR);

                InizioIntervallo = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mAnno, int mMese, int mGiorno) {
                        dataInizio.setText(mGiorno + "/" + (mMese+1) + "/" + mAnno);
                        dataInizio.setVisibility(View.VISIBLE);
                    }
                }, giorno, mese, anno);
                InizioIntervallo.show();
            }
        });

        bottoneFine = (Button) v.findViewById(R.id.adminButtonDataFineIntervallo);
        dataFine = (TextView) v.findViewById(R.id.adminTestoDataFineIntervallo);
        dataFine.setVisibility(View.INVISIBLE);
        bottoneFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarioFine= Calendar.getInstance();
                int fingiorno = calendarioFine.get(Calendar.DAY_OF_MONTH);
                int finmese = calendarioFine.get(Calendar.MONTH);
                int finanno = calendarioFine.get(Calendar.YEAR);

                FineIntervallo = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nAnno, int nMese, int nGiorno) {
                        dataFine.setText(nGiorno + "/" + (nMese+1) + "/" + nAnno);
                        dataFine.setVisibility(View.VISIBLE);
                    }
                }, fingiorno, finmese, finanno);
                FineIntervallo.show();
            }
        });

        return v;
    }

}