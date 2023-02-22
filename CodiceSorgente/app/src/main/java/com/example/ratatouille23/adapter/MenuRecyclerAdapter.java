package com.example.ratatouille23.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.example.ratatouille23.entity.SezioneMenu;

import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder> {

    Activity activity;
    List<SezioneMenu> sezioniMenu;

    public MenuRecyclerAdapter(List<SezioneMenu> sezioniMenu, Activity activity) {
        this.sezioniMenu = sezioniMenu;
        this.activity = activity;
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

        SezioneMenu sezione = sezioniMenu.get(position);
        String nomeSezione = sezione.getTitolo();
        List<ProdottoMenu> prodottiMenu = sezione.getProdottiMenu();

        holder.nomeSezioneTextView.setText(nomeSezione);

        ProdottiAdapter prodottiAdapter = new ProdottiAdapter(prodottiMenu, activity);
        holder.sezioniRecyclerView.setAdapter(prodottiAdapter);


    }

    @Override
    public int getItemCount() {
        return sezioniMenu.size();
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


}
