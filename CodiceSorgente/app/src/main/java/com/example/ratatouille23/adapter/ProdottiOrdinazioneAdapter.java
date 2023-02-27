package com.example.ratatouille23.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.Ordinazione;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SingoloOrdine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProdottiOrdinazioneAdapter extends RecyclerView.Adapter<ProdottiOrdinazioneAdapter.ViewHolder> {

    private Context context;
    private List<SingoloOrdine> prodotti;
    private static Bundle myBundle = new Bundle();
    private ProdottiOrdinazioneAdapter.ItemClickListenerOrdinazione clickListener;



    public ProdottiOrdinazioneAdapter(List<SingoloOrdine> prodotti, Context context, ProdottiOrdinazioneAdapter.ItemClickListenerOrdinazione clickListener) {
        this.prodotti = prodotti;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProdottiOrdinazioneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_ordinazione, parent, false);
        return new ProdottiOrdinazioneAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdottiOrdinazioneAdapter.ViewHolder holder, int position) {

        ProdottoMenu prodotto = prodotti.get(position).getProdottoMenu();
        String nomeProdotto = prodotto.getNome();
        String quantita = String.valueOf(prodotti.get(position).getQuantitaProdotto());

        holder.nomeProdottoTextView.setText(nomeProdotto);
        holder.quantitaProdottoTextView.setText(quantita);
        holder.rimuoviProdottoButton.setOnClickListener(view ->
                clickListener.onItemClickOrdinazione(holder.getAdapterPosition())
        );

    }

    @Override
    public int getItemCount() {
        try{
            return prodotti.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeProdottoTextView, quantitaProdottoTextView;
        FloatingActionButton rimuoviProdottoButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProdottoTextView = itemView.findViewById(R.id.titoloProdottoOrdinazioneTextView);
            quantitaProdottoTextView = itemView.findViewById(R.id.quantitaProdottoOrdinazioneTextView);
            rimuoviProdottoButton = itemView.findViewById(R.id.eliminaProdottoOrdinazioneButton);
        }
    }

    public static Bundle getBundle(){
        return myBundle;
    }

    public interface ItemClickListenerOrdinazione {
        void onItemClickOrdinazione(int posizione);
    }
}
