package com.live.worldsocialintegrationapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.Fragments.Profile.OnlineServices.OnlineServiceFragment;
import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.GetQuestionRoot;
import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.MessageModal;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.room.CustomerChatTable;
import com.live.worldsocialintegrationapp.room.DiffaultQuestions;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class OnlineServiceRVAdapter extends RecyclerView.Adapter<OnlineServiceRVAdapter.ViewHolder> {

    private List<DiffaultQuestions> questionList = new ArrayList<>();
    private List<CustomerChatTable> list = new ArrayList<>();
    private Context context;
    Callback callback;
    private String isOperator="0"; //0 means not operator and 1 means operator


    public OnlineServiceRVAdapter(List<CustomerChatTable> list, List<DiffaultQuestions> questionList, Context context, Callback callback) {
        this.list = list;
        this.context = context;
        this.questionList = questionList;
        this.callback = callback;
    }


    @NonNull
    @Override
    public OnlineServiceRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.online_services_rv_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineServiceRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.customerRL.setVisibility(View.GONE);
        holder.userRL.setVisibility(View.GONE);
        holder.defaultMessageRL.setVisibility(View.GONE);


        if (holder.getAbsoluteAdapterPosition() == 0) {
            holder.defaultMessageRL.setVisibility(View.VISIBLE);
            if (!questionList.isEmpty()) {
                List<DiffaultQuestions> listDetails = new ArrayList<>();
                listDetails = questionList;
                holder.defaultQuestionRV.setVisibility(View.VISIBLE);
                OnlineServiceSubRVAdapter onlineServiceSubRVAdapter = new OnlineServiceSubRVAdapter(listDetails, context, new OnlineServiceSubRVAdapter.Callback() {
                    @Override
                    public void questionClick(DiffaultQuestions detail) {
                        //Toast.makeText(context, "rec", Toast.LENGTH_SHORT).show();
                        callback.questionClick(detail);
                    }
                });
                holder.defaultQuestionRV.setAdapter(onlineServiceSubRVAdapter);

            } else {
            }
        } else {

            if (list.get(position).getSender().equalsIgnoreCase(AppConstants.USER_ID)) {
                holder.userRL.setVisibility(View.VISIBLE);
                holder.clientMsgTv.setText(list.get(position).getMessage());

                Log.d("ONLINE", "msg" + list.get(position).getMessage() + " ueser");
                String str = App.getSharedpref().getString("name");
                char first = str.charAt(0);
                holder.sendNameTv.setText(String.valueOf(first));
            } else {

                if (isOperator.equalsIgnoreCase("0")){
                    holder.customerRL.setVisibility(View.VISIBLE);
                    holder.customerMsgTv.setText(list.get(position).getMessage());
                }else{
                    //operator
                    //Toast.makeText(context, "Operator", Toast.LENGTH_SHORT).show();

                    holder.wowsCustomerServiceTv.setText("Operator");

                    holder.customerRL.setVisibility(View.VISIBLE);
                    holder.customerMsgTv.setText(list.get(position).getMessage());
                }


            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.deleteMsg(list.get(position));
                }
            });
        }


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
        TextView clientMsgTv, customerMsgTv, sendNameTv,wowsCustomerServiceTv;
        RelativeLayout userRL, customerRL, defaultMessageRL;
        RecyclerView defaultQuestionRV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clientMsgTv = itemView.findViewById(R.id.clientMsgTv);
            customerMsgTv = itemView.findViewById(R.id.customerMsgTv);
            sendNameTv = itemView.findViewById(R.id.sendNameTv);
            userRL = itemView.findViewById(R.id.userRL);
            customerRL = itemView.findViewById(R.id.customerRL);
            defaultMessageRL = itemView.findViewById(R.id.defaultMessageRL);
            defaultQuestionRV = itemView.findViewById(R.id.defaultQuestionRV);
            wowsCustomerServiceTv = itemView.findViewById(R.id.wowsCustomerServiceTv);

        }
    }

    public interface Callback {

        void questionClick(DiffaultQuestions detail);

        void deleteMsg(CustomerChatTable detail);
    }

    public void loadData(List<CustomerChatTable> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}
