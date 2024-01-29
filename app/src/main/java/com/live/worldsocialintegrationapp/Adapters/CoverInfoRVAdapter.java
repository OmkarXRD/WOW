package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

public class CoverInfoRVAdapter extends RecyclerView.Adapter<CoverInfoRVAdapter.ViewHolder> {

    List<String> employees;
    private int checkedPosition = 0;
    Context context;

    public CoverInfoRVAdapter(Context context, ArrayList<String> employees) {
        this.context = context;
        this.employees = employees;
    }

    public void setEmployees(ArrayList<String> employees) {
        this.employees = new ArrayList<>();
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CoverInfoRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cover_info_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoverInfoRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView coverItemsTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coverItemsTV = itemView.findViewById(R.id.coverItemsTV);
        }

        void bind(final String employee) {
            if (checkedPosition == -1) {
                coverItemsTV.setTextColor(Color.GRAY);
                coverItemsTV.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.coverinfotext));
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    coverItemsTV.setTextColor(Color.WHITE);
                    coverItemsTV.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.cover_info_green_tv_bg));
                } else {
                    coverItemsTV.setTextColor(Color.GRAY);
                    coverItemsTV.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.coverinfotext));
                }
            }
            coverItemsTV.setText(employee);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    coverItemsTV.setTextColor(Color.WHITE);
                    coverItemsTV.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.cover_info_green_tv_bg));
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }

    public String getSelected() {
        if (checkedPosition != -1) {
            return employees.get(checkedPosition);
        }
        return null;
    }
}
