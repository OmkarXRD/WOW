package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.GetQuestionRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.room.CustomerChatTable;
import com.live.worldsocialintegrationapp.room.DiffaultQuestions;

import java.util.ArrayList;
import java.util.List;

public class OnlineServiceSubRVAdapter extends RecyclerView.Adapter<OnlineServiceSubRVAdapter.ViewHolder> {

    //    List<GetQuestionRoot.Detail> list;
    List<DiffaultQuestions> list=new ArrayList<>();
    Context context;
    Callback callback;
    int i = 0;

    public OnlineServiceSubRVAdapter(List<DiffaultQuestions> list, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public OnlineServiceSubRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.online_service_sub_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineServiceSubRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        i++;
        holder.questionTV.setText(String.valueOf(i) + " " + list.get(position).getMessage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.questionClick(list.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTV = itemView.findViewById(R.id.questionTV);
        }
    }

    public interface Callback {
        void questionClick(DiffaultQuestions detail);
    }
}
