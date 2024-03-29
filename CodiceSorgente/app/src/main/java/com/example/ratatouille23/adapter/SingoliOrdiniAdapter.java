package com.example.ratatouille23.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.SingoloOrdine;

import java.util.List;

public class SingoliOrdiniAdapter extends RecyclerView.Adapter<SingoliOrdiniAdapter.ViewHolder> {

    List<SingoloOrdine> listaSingoliOrdini;

    public SingoliOrdiniAdapter(List<SingoloOrdine> listaSingoliOrdini) {
        this.listaSingoliOrdini = listaSingoliOrdini;
    }

    @NonNull
    @Override
    public SingoliOrdiniAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_conti_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingoliOrdiniAdapter.ViewHolder holder, int position) {

        if(listaSingoliOrdini.get(position).getProdottoMenu() != null){
            holder.holderProdottoSelezionatoItemTextView.setText(listaSingoliOrdini.get(position).getProdottoMenu().getNomeProdotto());
        }
        holder.holderCifraQuantitaProdottoTextView.setText(String.valueOf(listaSingoliOrdini.get(position).getQuantita()));

    }

    @Override
    public int getItemCount() {
        try{
            return listaSingoliOrdini.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    public SingoloOrdine getItem(int posizione) {
        return listaSingoliOrdini.get(posizione);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView holderProdottoSelezionatoItemTextView, holderCifraQuantitaProdottoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            holderProdottoSelezionatoItemTextView = itemView.findViewById(R.id.prodottoSelezionatoItemTextView);
            holderCifraQuantitaProdottoTextView = itemView.findViewById(R.id.cifraQuantitàProdottoTextView);
        }
    }
}
