package com.example.ratatouille23.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.UI.fragment.ModificaProdottoFragment;
import com.example.ratatouille23.R;
import com.example.ratatouille23.UI.fragment.PersonalizzaMenuFragment;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProdottiAdapter extends RecyclerView.Adapter<ProdottiAdapter.ViewHolder> {

    Activity activity;
    List<ProdottoMenu> prodottiMenu;


    public ProdottiAdapter(List<ProdottoMenu> prodottiMenu, Activity activity) {
        this.prodottiMenu = prodottiMenu;
        this.activity = activity;
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

        holder.nomeProdottoTextView.setText(prodottiMenu.get(position).getNome());
        holder.modificaProdottoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModificaProdottoFragment fragment = new ModificaProdottoFragment();
                Toast.makeText(activity,"Hai cliccato: " + prodottiMenu.get(holder.getAdapterPosition()).getNome(), Toast.LENGTH_SHORT).show();
            }
        });

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

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeProdottoTextView;
        FloatingActionButton modificaProdottoButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProdottoTextView = itemView.findViewById(R.id.titoloProdottoTextView);
            modificaProdottoButton = itemView.findViewById(R.id.opzioniButton);
        }
    }


}
