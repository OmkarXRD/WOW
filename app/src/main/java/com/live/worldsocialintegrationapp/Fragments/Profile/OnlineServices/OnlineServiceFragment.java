package com.live.worldsocialintegrationapp.Fragments.Profile.OnlineServices;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.worldsocialintegrationapp.Adapters.OnlineServiceRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.AdminPanelStatusRoot;
import com.live.worldsocialintegrationapp.ModelClasses.ChatModel;
import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.GetAnswerRoot;
import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.GetQuestionRoot;
import com.live.worldsocialintegrationapp.ModelClasses.onlineServices.MessageModal;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import com.live.worldsocialintegrationapp.room.AppDatabase;
import com.live.worldsocialintegrationapp.room.CustomerChatTable;
import com.live.worldsocialintegrationapp.room.DiffaultQuestions;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OnlineServiceFragment extends Fragment implements OnlineServiceRVAdapter.Callback {

    private EditText onlineServiceEdtx;
    private AppCompatButton humanServiceBtn;
    private RelativeLayout onlineServicePlusRL;
    private ImageView onlineServiceBackImg;
    private RecyclerView onlineServiceRV;
    private TextView adminOnlineTv;
    private OnlineServiceRVAdapter onlineServiceRVAdapter;
    private List<GetQuestionRoot.Detail> questionList = new ArrayList<>();
    private List<CustomerChatTable> list = new ArrayList<>();
    private List<DiffaultQuestions> diffList = new ArrayList<>();
    private CustomerChatTable customerChatTable;
    private AppDatabase appDatabase;

    private String userId,otherUserId="0";
    private DatabaseReference chatRef;

    private boolean isOperator=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        chatRef = FirebaseDatabase.getInstance().getReference("OnlineServices");

        appDatabase = AppDatabase.getInstance(requireContext());
        userId = AppConstants.USER_ID;

        findIds(view);
        clicks(view);
        getAdminStatusApi();
//        getQuestionApi();
        getDataFromDatabase();
        getDiffaultQuestions();
        receiveData();

        humanServiceBtn.setVisibility(View.GONE);
        onlineServiceRVAdapter = new OnlineServiceRVAdapter(list, diffList, requireContext(), OnlineServiceFragment.this);
        onlineServiceRV.setAdapter(onlineServiceRVAdapter);
        onlineServiceRV.smoothScrollToPosition(onlineServiceRV.getAdapter().getItemCount());


    }

    private void getDiffaultQuestions() {
        diffList = appDatabase.userDao().getDiffaultQuestions();
    }

    private void clicks(View view) {

        onlineServiceBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        onlineServicePlusRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!onlineServiceEdtx.getText().toString().trim().equalsIgnoreCase("")) {

                    if (isOperator){
                        SendMessage();
                    }

//                    CustomerChatTable customerChatTable2 = new CustomerChatTable(onlineServiceEdtx.getText().toString().trim(), AppConstants.USER_ID,
//                            "00");
//                    appDatabase.userDao().insert(customerChatTable2);
//
//                    list = appDatabase.userDao().getChat();
//                    onlineServiceRVAdapter.loadData(list);
//                    onlineServiceRVAdapter.notifyDataSetChanged();
//                    onlineServiceRV.smoothScrollToPosition(onlineServiceRV.getAdapter().getItemCount());
//                    onlineServiceEdtx.setText("");
//
                }else{
                    Toast.makeText(requireContext(), "Type your message", Toast.LENGTH_SHORT).show();
                }
            }
        });


        humanServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isOperator=true;
                App.getSharedpref().saveString("operator","1");  //1 means operator is online
            }
        });

    }

    private void findIds(View view) {

        onlineServiceEdtx = view.findViewById(R.id.onlineServiceEdtx);
        onlineServicePlusRL = view.findViewById(R.id.onlineServicePlusRL);
        onlineServiceBackImg = view.findViewById(R.id.onlineServiceBackImg);
        onlineServiceRV = view.findViewById(R.id.onlineServiceRV);
        adminOnlineTv = view.findViewById(R.id.adminOnlineTv);
        humanServiceBtn = view.findViewById(R.id.humanServiceBtn);
    }

    private void getAdminStatusApi() {

        new Mvvm().getAdminStatus(requireActivity()).observe(requireActivity(), new Observer<AdminPanelStatusRoot>() {
            @Override
            public void onChanged(AdminPanelStatusRoot adminPanelStatusRoot) {
                if (adminPanelStatusRoot != null) {
                    if (adminPanelStatusRoot.getSuccess().equalsIgnoreCase("1")) {

                        //0 -> online   1 -> offline
                        if (adminPanelStatusRoot.getDetails().getStatus().equalsIgnoreCase("0")) {
                            adminOnlineTv.setText("Online");
                        } else {
                            adminOnlineTv.setText("Offline");
                        }
                    } else {

                    }
                } else {

                }
            }
        });
    }

    private void getDataFromDatabase() {
        list = appDatabase.userDao().getChat();
    }


    @Override
    public void questionClick(DiffaultQuestions detail) {

        customerChatTable = new CustomerChatTable(detail.getMessage(), AppConstants.USER_ID, detail.getQuestion_id());
        appDatabase.userDao().insert(customerChatTable);

        new Mvvm().getAnwser(requireActivity(), detail.getQuestion_id(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetAnswerRoot>() {
            @Override
            public void onChanged(GetAnswerRoot getAnswerRoot) {
                if (getAnswerRoot != null) {
                    if (getAnswerRoot.getSuccess().equalsIgnoreCase("1")) {

                        if (getAnswerRoot.getDetails().getAnswers() != null) {
                            CustomerChatTable customerChatTable2 = new CustomerChatTable(getAnswerRoot.getDetails().getAnswers().getAnswer(), "0",
                                    getAnswerRoot.getDetails().getAnswers().getId());
                            appDatabase.userDao().insert(customerChatTable2);
                        } else {
                            CustomerChatTable customerChatTable2 = new CustomerChatTable("Transfer to human service", "0",
                                    "10");
                            appDatabase.userDao().insert(customerChatTable2);
                            humanServiceBtn.setVisibility(View.VISIBLE);
                        }

                        list = appDatabase.userDao().getChat();
                        onlineServiceRVAdapter.loadData(list);
                        onlineServiceRVAdapter.notifyDataSetChanged();
                        onlineServiceRV.smoothScrollToPosition(onlineServiceRV.getAdapter().getItemCount());
                    } else {

                    }
                } else {
                }
            }
        });
    }

    @Override
    public void deleteMsg(CustomerChatTable detail) {
        appDatabase.userDao().deleteChatMsg(detail.getId());
    }

    private void receiveData() {

        Toast.makeText(requireContext(), "userId "+userId, Toast.LENGTH_SHORT).show();
        Toast.makeText(requireContext(), "otherUserId "+otherUserId, Toast.LENGTH_SHORT).show();
        if (userId != null && otherUserId != null){


        chatRef.child(userId).child(otherUserId)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        if (snapshot.exists()) {

                            CustomerChatTable messages = snapshot.getValue(CustomerChatTable.class);
                            list.add(messages);

                            CustomerChatTable customerChatTable2 = new CustomerChatTable(onlineServiceEdtx.getText().toString().trim(), AppConstants.USER_ID,
                                    "00");
                            appDatabase.userDao().insert(customerChatTable2);

                            list = appDatabase.userDao().getChat();
                            onlineServiceRVAdapter.loadData(list);
                            onlineServiceRVAdapter.notifyDataSetChanged();
                            onlineServiceRV.smoothScrollToPosition(onlineServiceRV.getAdapter().getItemCount());
                            onlineServiceEdtx.setText("");

                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        }

    }

    private void SendMessage() {

        String otherUserId="0";

            if (onlineServiceEdtx.getText().toString().trim().length() == 0 ) {
                Toast.makeText(requireContext(), "enter the msg", Toast.LENGTH_SHORT).show();
            } else {

                String msgSendRef = "Messages/" + userId + "/" + otherUserId;
                String msgRecRef = "Messages/" + otherUserId + "/" + userId;

                DatabaseReference userMsgKeyRef = chatRef.child("OnlineServices").child(userId).child(otherUserId).push();

                String msgPushId = userMsgKeyRef.getKey();  // msg key for msg key
                CustomerChatTable messages = new CustomerChatTable(onlineServiceEdtx.getText().toString().trim(), userId, "0");


                Map messageBodyDetails = new HashMap();
                messageBodyDetails.put(msgSendRef + "/" + msgPushId, messages);

                messageBodyDetails.put(msgRecRef + "/" + msgPushId, messages);

                chatRef.updateChildren(messageBodyDetails).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if (task.isSuccessful()) {

                        } else {
                        }
                        onlineServiceEdtx.setText("");
                    }
                });
            }
    }
}