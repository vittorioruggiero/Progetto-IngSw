package com.example.ratatouille23;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ProdottiAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Prodotto> item;
    private ArrayList<Prodotto> originalItem;

    public ProdottiAdapter() {
        super();
    }

    public ProdottiAdapter(Context context, ArrayList<Prodotto> item) {
        this.context = context;
        this.item = item;
        //this.originalItem = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (item.get(position).isSection()) {
            // if section header
            convertView = inflater.inflate(R.layout.layout_sections, parent, false);
            TextView titoloSezioneTextView = (TextView) convertView.findViewById(R.id.titoloSezioneTextView);
            titoloSezioneTextView.setText(((SezioneMenu) item.get(position)).getTitle());
        } else {
            // if item
            convertView = inflater.inflate(R.layout.layout_item, parent, false);
            TextView titoloProdottoTextView = (TextView) convertView.findViewById(R.id.titoloProdottoTextView);
            titoloProdottoTextView.setText(((ProdottoMenu) item.get(position)).getTitle());
        }

        return convertView;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                item = (ArrayList<Prodotto>) results.values;
                notifyDataSetChanged();
            }

            @SuppressWarnings("null")
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Prodotto> filteredArrayList = new ArrayList<Prodotto>();


                if (originalItem == null || originalItem.size() == 0) {
                    originalItem = new ArrayList<Prodotto>(item);
                }

                /*
                 * if constraint is null then return original value
                 * else return filtered value
                 */
                if (constraint == null && constraint.length() == 0) {
                    results.count = originalItem.size();
                    results.values = originalItem;
                } else {
                    constraint = constraint.toString().toLowerCase(Locale.ENGLISH);
                    for (int i = 0; i < originalItem.size(); i++) {
                        String title = originalItem.get(i).getTitle().toLowerCase(Locale.ENGLISH);
                        if (title.startsWith(constraint.toString())) {
                            filteredArrayList.add(originalItem.get(i));
                        }
                    }
                    results.count = filteredArrayList.size();
                    results.values = filteredArrayList;
                }

                return results;
            }
        };

        return filter;
    }

}