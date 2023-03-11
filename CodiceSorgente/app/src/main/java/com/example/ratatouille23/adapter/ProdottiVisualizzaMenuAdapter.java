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

import java.util.List;

public class ProdottiVisualizzaMenuAdapter extends RecyclerView.Adapter<ProdottiVisualizzaMenuAdapter.ViewHolder> {

    private Context context;
    private int posizioneSezione;
    private List<ProdottoMenu> prodottiVisualizzaMenu;
    private static Bundle myBundle = new Bundle();
    private ProdottiVisualizzaMenuAdapter.ItemClickListener clickListener;



    public ProdottiVisualizzaMenuAdapter(List<ProdottoMenu> prodottiMenu, Context context, ProdottiVisualizzaMenuAdapter.ItemClickListener clickListener, int posizioneSezione) {
        this.prodottiVisualizzaMenu = prodottiMenu;
        this.context = context;
        this.clickListener = clickListener;
        this.posizioneSezione = posizioneSezione;
    }

    @NonNull
    @Override
    public ProdottiVisualizzaMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_visual, parent, false);
        return new ProdottiVisualizzaMenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdottiVisualizzaMenuAdapter.ViewHolder holder, int position) {

        holder.nomeProdottoTextView.setText(prodottiVisualizzaMenu.get(position).getNomeProdotto());
        if(prodottiVisualizzaMenu.get(position).getNomeSecondaLingua() != null){
            holder.nomeProdottoSecondaLinguaTextView.setText(prodottiVisualizzaMenu.get(position).getNomeSecondaLingua());
        }else{
            holder.nomeProdottoSecondaLinguaTextView.setText("");
        }

        holder.nomeProdottoTextView.setOnClickListener(view ->
                clickListener.onItemClickVisual(prodottiVisualizzaMenu.get(holder.getAdapterPosition()), holder.getAdapterPosition(), posizioneSezione)
        );

    }

    @Override
    public int getItemCount() {
        try{
            return prodottiVisualizzaMenu.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeProdottoTextView, nomeProdottoSecondaLinguaTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProdottoTextView = itemView.findViewById(R.id.titoloProdottoVisualizzaTextView);
            nomeProdottoSecondaLinguaTextView = itemView.findViewById(R.id.nomeSecondaLinguaTextView);
        }
    }

    public static Bundle getBundle(){
        return myBundle;
    }

    public interface ItemClickListener {
        void onItemClickVisual(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione);
    }
}
