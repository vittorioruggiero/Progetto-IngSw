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
import com.example.ratatouille23.entity.ProdottoMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProdottiAdapter extends RecyclerView.Adapter<ProdottiAdapter.ViewHolder> {

    private Context context;
    private int posizioneSezione;
    private List<ProdottoMenu> prodottiMenu;
    private static Bundle myBundle = new Bundle();
    private ItemClickListener clickListener;



    public ProdottiAdapter(List<ProdottoMenu> prodottiMenu, Context context, ItemClickListener clickListener, int posizioneSezione) {
        this.prodottiMenu = prodottiMenu;
        this.context = context;
        this.clickListener = clickListener;
        this.posizioneSezione = posizioneSezione;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nomeProdottoTextView.setText(prodottiMenu.get(position).getNomeProdotto());
        holder.modificaProdottoButton.setOnClickListener(view ->
                clickListener.onItemClick(prodottiMenu.get(holder.getAdapterPosition()), holder.getAdapterPosition(), posizioneSezione)
        );

    }

    @Override
    public int getItemCount() {
        try{
            return prodottiMenu.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeProdottoTextView;
        FloatingActionButton modificaProdottoButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProdottoTextView = itemView.findViewById(R.id.titoloProdottoTextView);
            modificaProdottoButton = itemView.findViewById(R.id.opzioniButton);
        }
    }

    public static Bundle getBundle(){
        return myBundle;
    }

    public interface ItemClickListener {
        void onItemClick(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione);
    }

}
