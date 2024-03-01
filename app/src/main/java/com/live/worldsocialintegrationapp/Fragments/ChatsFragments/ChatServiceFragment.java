package com.live.worldsocialintegrationapp.Fragments.ChatsFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.ServiceChatAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.SendMsgRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import java.util.List;

public class ChatServiceFragment extends Fragment {

    private RecyclerView recycler,recycler2;
    private RelativeLayout msgSendRL;
    private EditText chatMsgEdtx;
    private Mvvm mvvm;
    private List<SendMsgRoot.Detail> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mvvm = new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment_chat_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        find(view);
        clicks(view);
        new Handler().postDelayed(() -> {
            getMsgApi();
        }, 200);
    }

    private void clicks(View view) {

        msgSendRL.setOnClickListener(view1 -> hitApi());
    }

    private void hitApi() {

        mvvm.sendMsg(AppConstants.USER_ID,chatMsgEdtx.getText().toString()).observe(requireActivity(),response ->{
            if (response!=null){
                if (response.getSuccess().equalsIgnoreCase("1")){
                    Log.d("Listsssssss","in Hit APi " + chatMsgEdtx.getText().toString() );
                    getMsgApi();
                    chatMsgEdtx.setText(null);
                }else {
                    //Toast.makeText(requireContext(), "0 "+response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(requireContext(), "Technical issue...", Toast.LENGTH_SHORT).show();
        }

        });
    }

    private void getMsgApi() {

        mvvm.getMsg(AppConstants.USER_ID).observe(requireActivity(),response ->{

            if (response !=null){
                if (response.getSuccess().equalsIgnoreCase("1")){
                    list = response.getDetail();
                    Log.d("Listsssssss", list.get(0).toString());
                    Log.d("Listsssssss","111 "+response.getDetail());
                    Log.d("Listsssssss","222 "+response.getMessage());
                    ServiceChatAdapter adapter = new ServiceChatAdapter(list,requireContext());
                    recycler.setAdapter(adapter);
                }else {
                    //Toast.makeText(requireContext(), "0 "+response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(requireContext(), "Technical issue...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getMsgApi();
        requireActivity().findViewById(R.id.bottom_lay).setVisibility(View.GONE);
    }

    private void find(View view) {

        recycler = view.findViewById(R.id.recycler);
        chatMsgEdtx = view.findViewById(R.id.chatMsgEdtx);
        msgSendRL = view.findViewById(R.id.msgSendRL);
    }
}