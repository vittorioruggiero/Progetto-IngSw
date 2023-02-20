package com.example.ratatouille23.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouille23.UI.fragment.ModificaProdottoFragment;
import com.example.ratatouille23.R;
import com.example.ratatouille23.entity.ProdottoMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProdottiAdapter extends RecyclerView.Adapter<ProdottiAdapter.ViewHolder> {

    List<ProdottoMenu> prodottiMenu;


    public ProdottiAdapter(List<ProdottoMenu> prodottiMenu) {
        this.prodottiMenu = prodottiMenu;
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
                sostituisciFragment(fragment);

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

    public void sostituisciFragment(Fragment fragment){
        /*FragmentManager fragmentManager = fragment.getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.adminFragmentContainerView, fragment);
        transaction.commit();*/
    }


}
