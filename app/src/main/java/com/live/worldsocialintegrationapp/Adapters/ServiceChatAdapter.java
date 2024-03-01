package com.live.worldsocialintegrationapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.live.worldsocialintegrationapp.ModelClasses.SendMsgRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ServiceChatAdapter extends RecyclerView.Adapter<ServiceChatAdapter.holder> {

    private List<SendMsgRoot.Detail> list ;
    private Context context;

    public ServiceChatAdapter(List<SendMsgRoot.Detail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceChatAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_rv_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceChatAdapter.holder holder, int position) {
        holder.senderAudioLlayout.setVisibility(View.GONE);
        Log.d("Listsssssss",position + " = "+ list.get(position).getMsg());

        if (list.get(position).getSenderId().equalsIgnoreCase(AppConstants.USER_ID)){
            holder.senderLlayout.setVisibility(View.VISIBLE);
            holder.receiverLlayout.setVisibility(View.GONE);
            holder.sender_message_text.setText(list.get(position).getMsg());
            holder.senderMessageTimeTv.setText(list.get(position).getTime());

        }else {
            holder.receiverLlayout.setVisibility(View.VISIBLE);
            holder.senderLlayout.setVisibility(View.GONE);
            holder.receiver_message_text.setText(list.get(position).getMsg());
            holder.receiverMessageTimeTv.setText(list.get(position).getTime());

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        private RelativeLayout senderAudioLlayout,defaultMessageRL;
        private LinearLayout senderLlayout, receiverLlayout;
        private TextView sender_message_text,receiver_message_text,receiverMessageTimeTv,senderMessageTimeTv;
        public holder(@NonNull View itemView) {
            super(itemView);
            senderAudioLlayout = itemView.findViewById(R.id.senderAudioLlayout);
            senderLlayout = itemView.findViewById(R.id.senderLlayout);
            receiverLlayout  = itemView.findViewById(R.id.receiverLlayout);
            receiver_message_text  = itemView.findViewById(R.id.receiver_message_text);
            sender_message_text  = itemView.findViewById(R.id.sender_message_text);
            senderMessageTimeTv  = itemView.findViewById(R.id.senderMessageTimeTv);
            receiverMessageTimeTv  = itemView.findViewById(R.id.receiverMessageTimeTv);
        }
    }
}
