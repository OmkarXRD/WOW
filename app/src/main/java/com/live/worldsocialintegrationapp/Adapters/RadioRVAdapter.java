package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.Fragments.Profile.OtherUserTabs.ItemClickListenter;
import com.live.worldsocialintegrationapp.ModelClasses.UserReportCategoryRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;

public class RadioRVAdapter extends RecyclerView.Adapter<RadioRVAdapter.ViewHolder> {
    List<UserReportCategoryRoot.Detail> list= new ArrayList<>();
    ItemClickListenter itemClickListenter;
    public  static  int i;
    int selectedPosition=-1;

    public RadioRVAdapter(List<UserReportCategoryRoot.Detail> list, ItemClickListenter itemClickListenter) {
        this.list = list;
        this.itemClickListenter = itemClickListenter;
    }

    @NonNull
    @Override
    public RadioRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.users_report_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RadioRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(i==0){
            holder.radioButtonId.setText(list.get(position).getCategory_name());
        }else{
            holder.radioButtonId.setText(list.get(position).getType_name());
        }

        holder.radioButtonId.setChecked(position == selectedPosition);

        holder.radioButtonId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    selectedPosition=holder.getAdapterPosition();
                    itemClickListenter.onClick(list.get(position).getId());
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButtonId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButtonId=itemView.findViewById(R.id.radioButtonId);

        }
    }

}
