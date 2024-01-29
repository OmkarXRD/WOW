package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Root.CountryRootDetails;

import java.util.ArrayList;
import java.util.List;

public class MoreCountriesAdapter extends RecyclerView.Adapter<MoreCountriesAdapter.ViewHolder> {
    List<CountryRootDetails> details;
    Callback callback;


    public MoreCountriesAdapter(List<CountryRootDetails> details, Callback callback) {
        this.details = details;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_flags, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.countryName.setText(details.get(position).getName());
        int flagOffset = 0x1F1E6;
        int asciiOffset = 0x41;

        String country = details.get(position).getIso2();

        int firstChar = Character.codePointAt(country, 0) - asciiOffset + flagOffset;
        int secondChar = Character.codePointAt(country, 1) - asciiOffset + flagOffset;

        String flag = new String(Character.toChars(firstChar))
                + new String(Character.toChars(secondChar));

        holder.countryEmoji.setText(flag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callback(details.get(position).getName());
            }
        });


    }

    public void loadData(List<CountryRootDetails> list) {

        this.details = list;
        notifyDataSetChanged();

    }
    public void filterList(ArrayList<CountryRootDetails> list1) {
        details = list1;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return details.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryEmoji, countryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryEmoji = itemView.findViewById(R.id.countryEmoji);
            countryName = itemView.findViewById(R.id.countryName);
        }
    }

    public interface Callback{

        void callback(String countryName);
    }
}