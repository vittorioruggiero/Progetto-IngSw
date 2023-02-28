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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AvvisiAdapter extends RecyclerView.Adapter<AvvisiAdapter.ViewHolder> {

    private Context context;
    private List<String> avvisi;
    private static Bundle myBundle = new Bundle();
    private AvvisiAdapter.ItemClickListenerAvvisi clickListener;



    public AvvisiAdapter(List<String> avvisi, Context context, AvvisiAdapter.ItemClickListenerAvvisi clickListener) {
        this.avvisi = avvisi;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AvvisiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_avvisi, parent, false);
        return new AvvisiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvvisiAdapter.ViewHolder holder, int position) {

        String avviso = avvisi.get(position).toString();

        holder.avvisoTextView.setText(avviso);
        holder.avvisoVisualizzatoButton.setOnClickListener(view ->
                clickListener.onItemClickAvviso(holder.getAdapterPosition())
        );

    }

    @Override
    public int getItemCount() {
        try{
            return avvisi.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView avvisoTextView;
        FloatingActionButton avvisoVisualizzatoButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avvisoTextView = itemView.findViewById(R.id.avvisoTextView);
            avvisoVisualizzatoButton = itemView.findViewById(R.id.visualizzatoButton);
        }
    }

    public static Bundle getBundle(){
        return myBundle;
    }

    public interface ItemClickListenerAvvisi {
        void onItemClickAvviso(int posizione);
    }
}

