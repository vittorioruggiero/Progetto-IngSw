package com.example.ratatouille23.UI.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.activity.HomeAdminActivity;
import com.example.ratatouille23.entity.Conto;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraficoStatisticaFragment extends Fragment {

    private List<Conto> listaConti = new ArrayList<>();
    private static final String GRAFICO_CONTI = "graficoConti";

    public GraficoStatisticaFragment() {
        // Required empty public constructor
    }
    public static GraficoStatisticaFragment newInstance(List<Conto> conti) {
        GraficoStatisticaFragment fragment = new GraficoStatisticaFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        String myJson = gson.toJson(conti);
        args.putString(GRAFICO_CONTI, myJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Gson gson = new Gson();
            listaConti = gson.fromJson(getArguments().getString(GRAFICO_CONTI), new TypeToken<List<Conto>>(){}.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_grafico_statistica, container, false);

        LineChart lineChart = v.findViewById(R.id.LineChart);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.getDescription().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        for(int i = 0; i < listaConti.size(); i++){
            float y = Float.parseFloat(String.valueOf(listaConti.get(i).getImporto()));
            Logger.getLogger(HomeAdminActivity.class.getName()).log(Level.SEVERE, "VALORE Y: " + y + " " + i);
            yValues.add(new Entry(i, y));
        }

        LineDataSet set1 = (LineDataSet) new LineDataSet(yValues,"Data Set");

        set1.setFillAlpha(130);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);


        LineData data = (LineData) new LineData(dataSets);

        lineChart.setData(data);


        return v;
    }
}