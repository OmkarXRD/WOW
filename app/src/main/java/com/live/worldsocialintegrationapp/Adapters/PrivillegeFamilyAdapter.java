package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.GetFamilyDetails;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

public class PrivillegeFamilyAdapter extends RecyclerView.Adapter<PrivillegeFamilyAdapter.holder> {

    Context context;
    public List<GetFamilyDetails.Detail> detailsList = new ArrayList<>();

    public PrivillegeFamilyAdapter(Context context, List<GetFamilyDetails.Detail> detailsList) {
        this.context = context;
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public PrivillegeFamilyAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.priviliagelayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrivillegeFamilyAdapter.holder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView Renkmadel ;

        public holder(@NonNull View itemView) {
            super(itemView);

            Renkmadel = itemView.findViewById(R.id.Renkmadel);
        }
    }
}
