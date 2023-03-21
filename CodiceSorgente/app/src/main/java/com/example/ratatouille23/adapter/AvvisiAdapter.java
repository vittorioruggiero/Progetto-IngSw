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
import com.example.ratatouille23.entity.Avviso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AvvisiAdapter extends RecyclerView.Adapter<AvvisiAdapter.ViewHolder> {

    private Context context;
    private List<Avviso> avvisi;
    private static Bundle myBundle = new Bundle();



    public AvvisiAdapter(List<Avviso> avvisi, Context context) {
        this.avvisi = avvisi;
        this.context = context;
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

        String avviso = avvisi.get(position).getAvviso();

        holder.avvisoTextView.setText(avviso);

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avvisoTextView = itemView.findViewById(R.id.avvisoTextView);
        }
    }

    public static Bundle getBundle(){
        return myBundle;
    }
}

