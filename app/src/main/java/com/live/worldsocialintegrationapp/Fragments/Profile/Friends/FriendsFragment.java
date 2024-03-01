package com.live.worldsocialintegrationapp.Fragments.Profile.Friends;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.worldsocialintegrationapp.Adapters.FriendRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.BuyVipRoot;
import com.live.worldsocialintegrationapp.ModelClasses.ChatModel;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetEventsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.GetFriends.GetFriendRoot;
import com.live.worldsocialintegrationapp.ModelClasses.GetLuckIdRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendEventInviationRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class FriendsFragment extends Fragment implements FriendRVAdapter.Callback {

    private RecyclerView friendsRV;
    private List<Detail> friendList;
    private AppCompatButton liveShareNextBtn;
    public static int check = 0, getFrameCheck = 1;
    int poistion = 0, liveShareCheck;
    String frameId, getLuckId, vipId, sendEventUserId, eventId;
    private List<Detail> sendLiveList = new ArrayList<>();
    String link = "" , dynmicKey="";
    DatabaseReference chatRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        sendLiveList.clear();

        chatRef = FirebaseDatabase.getInstance().getReference("ChatMessages");

        App.getSharedpref().saveString("liveShareFriends", "");
        App.getSharedpref().saveString("liveShareCheckAdpter", "");


        if (getArguments() != null && getArguments().containsKey("liveShareCheck") && getArguments().containsKey("liveShareLink")) {
            liveShareCheck = getArguments().getInt("liveShareCheck");
            link = getArguments().getString("liveShareLink");
            dynmicKey = getArguments().getString("dynmicKey");
        }

        if (check == 1) {
            view.findViewById(R.id.editProfileFriendBakImg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });

            view.findViewById(R.id.editFriendsLinearLayout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.editFriendsLine).setVisibility(View.VISIBLE);
            FriendRVAdapter.sendCheck = 1;
            if(getFrameCheck == 2){
                FriendRVAdapter.sendEventInvitationCheck = 2;
            }else{
                FriendRVAdapter.sendEventInvitationCheck = 1;
            }

        } else if (liveShareCheck == 1) {

            view.findViewById(R.id.editProfileFriendBakImg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
            liveShareNextBtn.setVisibility(View.VISIBLE);
            view.findViewById(R.id.editFriendsLinearLayout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.editFriendsLine).setVisibility(View.VISIBLE);
            App.getSharedpref().saveString("liveShareCheckAdpter", "1");
        } else {
            TextView topTX = (TextView) requireActivity().findViewById(R.id.friendProfileTV);
            topTX.setText("Friends");
            view.findViewById(R.id.editFriendsLinearLayout).setVisibility(View.GONE);
            view.findViewById(R.id.editFriendsLine).setVisibility(View.GONE);
        }

        getEventDataFromBundle();
        getFriendListApi();
        clicks(view);

        //Toast.makeText(requireContext(), "userId :- "+AppConstants.USER_ID, Toast.LENGTH_SHORT).show();

    }

    private void clicks(View view) {

        liveShareNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sendLiveList.isEmpty()) {
                    //Toast.makeText(requireContext(), "Please Select", Toast.LENGTH_SHORT).show();
                } else {

                    Dialog dialog_share = new Dialog(requireContext());
                    dialog_share.setContentView(R.layout.share_live_link_dialog_box);
                    dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                    dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog_share.setCanceledOnTouchOutside(true);
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

                    dialog_share.getWindow().setAttributes(layoutParams);
                    Window window = dialog_share.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog_share.show();

                    AppCompatButton cancelliveShareBtn = (AppCompatButton) dialog_share.findViewById(R.id.cancelliveShareBtn);
                    AppCompatButton confirmLiveShareBtn = (AppCompatButton) dialog_share.findViewById(R.id.confirmLiveShareBtn);
                    EditText liveShareEdTx = (EditText) dialog_share.findViewById(R.id.liveShareEdTx);
                    TextView liveShareDesTv = (TextView) dialog_share.findViewById(R.id.liveShareDesTv);
                    TextView liveShareTopTv = (TextView) dialog_share.findViewById(R.id.liveShareTopTv);
                    CircleImageView shareLinkCirImg = (CircleImageView) dialog_share.findViewById(R.id.shareLinkCirImg);

                    Glide.with(shareLinkCirImg.getContext()).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img)
                            .into(shareLinkCirImg);
                    liveShareDesTv.setText("I'm in " + App.getSharedpref().getString("name") + "'s room. You are welcome to join me!");
                    liveShareTopTv.setText("Share it with " + sendLiveList.size() + " friends");

                    confirmLiveShareBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_share.dismiss();
                            shareLiveLink(liveShareEdTx.getText().toString().trim());
                        }
                    });

                    cancelliveShareBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_share.dismiss();
                        }
                    });
                }
            }
        });


    }

    private void shareLiveLink(String chatTxt) {

        String userId = App.getSharedpref().getString("userId");


//            if (isFriendOrNOt) {
//                if (chatMsgEdtx.getText().toString().trim().length() == 0 && videoOrImgStatus.equalsIgnoreCase("3")) {
//                    Toast.makeText(requireContext(), "enter the msg", Toast.LENGTH_SHORT).show();
//                } else {
        for (int i = 0; i < sendLiveList.size(); i++) {
            //Toast.makeText(requireContext(), "send share link" + sendLiveList.get(i).getId(), Toast.LENGTH_SHORT).show();
            String otherUserId = sendLiveList.get(i).getId();

            String msgSendRef = "Messages/" + userId + "/" + otherUserId;
            String msgRecRef = "Messages/" + otherUserId + "/" + userId;

            DatabaseReference userMsgKeyRef = chatRef.child("Messages").child(userId).child(otherUserId).push();

            String msgPushId = userMsgKeyRef.getKey();  // msg key for msg key

            ChatModel messages = new ChatModel();
            messages.setMsgType("5"); //1 image and 2 video and 3 message and 4 audio and 5 liveLink

            messages.setMessage(AppConstants.USER_ID);
            messages.setFrom(userId);
            messages.setTo(otherUserId);
            messages.setMsgId(msgPushId);
            messages.setMessageUrl(link);
            messages.setLiveKey(dynmicKey);
            messages.setTime(getCurrentTime());
            messages.setDate(getDate());

            if (chatTxt != null && !chatTxt.isEmpty()){
                messages.setLiveExtraMsg(chatTxt);
            }

            Map messageBodyDetails = new HashMap();
            messageBodyDetails.put(msgSendRef + "/" + msgPushId, messages);

            messageBodyDetails.put(msgRecRef + "/" + msgPushId, messages);

            chatRef.updateChildren(messageBodyDetails).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    //Toast.makeText(requireContext(), "link shard Successfully", Toast.LENGTH_SHORT).show();
//                    if (task.isSuccessful()) {
//                        if (!isOnline) {
//                            sendMessageNotificationApi();
//                        }
//                        lastMessage(chatMsgEdtx.getText().toString());
//                    } else {
//                    }
//                    chatMsgEdtx.setText("");
                }
            });
//                }
//            } else {
//                if (!unFriendId.equalsIgnoreCase(AppConstants.USER_ID)) {
//                    Toast.makeText(requireContext(), "You can't send the message because you are unfriend by user", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(requireContext(), "First make friend", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//                    }

        }
    }

    private void getEventDataFromBundle() {

        if (getArguments() != null) {
            eventId = getArguments().getString("eventId");
        } else {

        }
    }

    private void getFriendListApi() {

        new Mvvm().getFriends(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetFriendRoot>() {
            @Override
            public void onChanged(GetFriendRoot getFriendRoot) {

                if (getFriendRoot != null) {
                    if (getFriendRoot.getStatus().equalsIgnoreCase("1")) {
                        //Toast.makeText(requireContext(), "1 " + getFriendRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        friendList = new ArrayList<>();
                        friendList = getFriendRoot.getDetails();
                        if (isAdded() && getContext()!=null){
                            FriendRVAdapter friendRVAdapter = new FriendRVAdapter(friendList, getContext(), FriendsFragment.this);
                            friendsRV.setAdapter(friendRVAdapter);
                        }
                    } else {

                    }
                } else {

                }
            }
        });
    }

    private void findIds(View view) {
        friendsRV = view.findViewById(R.id.friendsRV);
        liveShareNextBtn = view.findViewById(R.id.liveShareNextBtn);
    }

    private void sendFrameDialogBox() {

        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.send_frame_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        //initlize views
        TextView sendFrameTv1 = dialog_share.findViewById(R.id.sendFrameTv1);
        TextView sendFrameCoinsTV = dialog_share.findViewById(R.id.sendFrameCoinsTV);
        TextView sendFrameDialogValidityTv = dialog_share.findViewById(R.id.sendFrameDialogValidityTv);
        RelativeLayout sendFrameRL = dialog_share.findViewById(R.id.sendFrameRL);
        RelativeLayout cancelFrameRL = dialog_share.findViewById(R.id.cancelFrameRL);
        ImageView dialogFrameImage = dialog_share.findViewById(R.id.dialogFrameImage);

        //set Data on Dialogbox

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            String validity = bundle.getString("validity");
            String img = bundle.getString("img");
            String price = bundle.getString("price");
            String giftType = bundle.getString("giftType");
            frameId = bundle.getString("frameId");
            getLuckId = bundle.getString("getLuckId");
            vipId = bundle.getString("vipId");

            sendFrameTv1.setText("Are you sure you want to send this " + giftType + " to your friend" + " " + friendList.get(poistion).getName() + "?");
            sendFrameCoinsTV.setText(price);
            sendFrameDialogValidityTv.setText(validity);
            Glide.with(dialogFrameImage.getContext()).load(img).error(R.drawable.demo_user_profile_img).into(dialogFrameImage);
        } else {
//            Toast.makeText(requireContext(), "argments is null", Toast.LENGTH_SHORT).show();
        }


        cancelFrameRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                dialog_share.dismiss();

            }
        });
        sendFrameRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1 means cars fragemnt get and 2 frame fragment
                if (getFrameCheck == 1) {

                    sendLuckId();
                } else if (getFrameCheck == 2) {

                    sendVip();
                } else {

                    sendFrame();
                }
                dialog_share.dismiss();
            }
        });

    }


    private void sendLuckId() {

        new Mvvm().sendLuckId(requireActivity(), AppConstants.USER_ID, friendList.get(poistion).getId(), getLuckId).observe(requireActivity(), new Observer<GetLuckIdRoot>() {
            @Override
            public void onChanged(GetLuckIdRoot getLuckIdRoot) {
                if (getLuckIdRoot.getStatus() == 1) {
                    //Toast.makeText(requireContext(), "1 " + getLuckIdRoot.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(requireContext(), "0 " + getLuckIdRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    notEnoughCoins();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (check == 2) {
            check = 1;
        } else {
            check = 0;
        }

    }

    private void notEnoughCoins() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Not enough coins, want to recharge?");
        builder.setTitle("Tips");
        builder.setCancelable(false);
        builder.setPositiveButton("Recharge", (DialogInterface.OnClickListener) (dialog, which) -> {
            check = 2;
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.rechargeCointsFragment);
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void sendFrame() {
        new Mvvm().sendFrame(requireActivity(), AppConstants.USER_ID, friendList.get(poistion).getId(), frameId).observe(requireActivity(), new Observer<GetFramesRoot>() {
            @Override
            public void onChanged(GetFramesRoot getFramesRoot) {
                if (getFramesRoot != null) {
                    if (getFramesRoot.getStatus() == 1) {
//                    Toast.makeText(requireContext(), "1 "+getFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
//                    Toast.makeText(requireContext(), "0 "+getFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        notEnoughCoins();
                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendVip() {

        new Mvvm().buyVip(requireActivity(), friendList.get(poistion).getId(), frameId).observe(requireActivity(), new Observer<BuyVipRoot>() {
            @Override
            public void onChanged(BuyVipRoot buyVipRoot) {
                if (buyVipRoot != null) {
                    if (buyVipRoot.getStatus() == 1) {
                   Toast.makeText(requireContext(), "1 " + buyVipRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        notEnoughCoins();
                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void callback(String otherUserId, int poistion) {
        if (!otherUserId.equalsIgnoreCase("001")) {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", otherUserId);
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
        } else {
            sendFrameDialogBox();
            poistion = poistion;
        }
    }

    @Override
    public void sendEventInvitation(String userId, TextView friendInviteTv) {
        if (!userId.isEmpty()) {
            if (check == 1) {
                new Mvvm().sendEventInvitation(requireActivity(), userId, eventId).observe(requireActivity(), new Observer<SendEventInviationRoot>() {
                    @Override
                    public void onChanged(SendEventInviationRoot getEventsRoot) {
                        if (getEventsRoot != null) {
                            if (getEventsRoot.getSuccess() == 1) {
                                friendInviteTv.setText("Invited");
//                        Toast.makeText(requireContext(), "1 "+getEventsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                friendInviteTv.setText("Invite");
//                        Toast.makeText(requireContext(), "0 "+getEventsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
//            Toast.makeText(requireContext(), "callback null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void checkBoxChecked(Detail detail) {
        if (detail != null) {
            sendLiveList.add(detail);
            if (!sendLiveList.isEmpty()) {
                liveShareNextBtn.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
    }

    @Override
    public void checkBoxUnchecked(Detail details) {
        if (details != null) {
            sendLiveList.remove(details);
            //Toast.makeText(requireContext(), "size " + sendLiveList.size(), Toast.LENGTH_SHORT).show();

            if (!sendLiveList.isEmpty()) {
                liveShareNextBtn.setBackgroundColor(getResources().getColor(R.color.green));
            }else {
                liveShareNextBtn.setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
    }

    private  String getCurrentTime() {
        //date output format
        DateFormat dateFormat = new SimpleDateFormat("HH:mm aa");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    private  String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return  dateFormat.format(c.getTime());
    }

}