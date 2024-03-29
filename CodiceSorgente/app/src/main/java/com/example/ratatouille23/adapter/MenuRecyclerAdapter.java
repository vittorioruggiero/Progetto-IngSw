package com.example.ratatouille23.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> implements ProdottiAdapter.ItemClickListener {

    private Context context;
    private List<SezioneMenu> sezioniMenu;
    private List<SezioneMenu> sezioniMenuFull;
    private ProdottiAdapter prodottiAdapter;

    private ProdottiAdapter.ItemClickListener clickListener;

    public MenuRecyclerAdapter(List<SezioneMenu> sezioniMenu, Context context, ProdottiAdapter.ItemClickListener clickListener) {
        this.sezioniMenu = sezioniMenu;
        this.context = context;
        this.clickListener = clickListener;
        this.sezioniMenuFull = new ArrayList<>(sezioniMenu);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_sections, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SezioneMenu sezione = sezioniMenuFull.get(position);
        String nomeSezione = sezione.getNome();
        List<ProdottoMenu> prodottiMenu = sezione.getProdottiMenu();

        holder.nomeSezioneTextView.setText(nomeSezione);

        prodottiAdapter = new ProdottiAdapter(prodottiMenu, context, this.clickListener, position);
        holder.sezioniRecyclerView.setAdapter(prodottiAdapter);


    }

    @Override
    public int getItemCount() {
        return sezioniMenuFull.size();
    }

    @Override
    public void onItemClick(ProdottoMenu prodottoMenu, int posizione, int posizioneSezione) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomeSezioneTextView;
        RecyclerView sezioniRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeSezioneTextView = itemView.findViewById(R.id.titoloSezioneTextView);
            sezioniRecyclerView = itemView.findViewById(R.id.recyclerViewSezione);

        }
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();
                List<SezioneMenu> filteredList = new ArrayList<>();
                if(query.isEmpty()){
                    filteredList.addAll(sezioniMenu);
                }else{
                    for(SezioneMenu sezione : sezioniMenu){
                        List<ProdottoMenu> prodottiFiltrati = new ArrayList<>();
                        for(ProdottoMenu prodotto : sezione.getProdottiMenu()){
                            if(prodotto.getNomeProdotto().toLowerCase().contains(query.toLowerCase())){
                                prodottiFiltrati.add(prodotto);
                            }
                        }
                        if(!prodottiFiltrati.isEmpty()){
                            filteredList.add(new SezioneMenu(sezione.getNome(), prodottiFiltrati));
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                sezioniMenuFull.clear();
                sezioniMenuFull.addAll((List<SezioneMenu>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

}
