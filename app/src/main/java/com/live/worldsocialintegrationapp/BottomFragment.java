package com.live.worldsocialintegrationapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

public class BottomFragment extends BottomSheetDialogFragment {

    private TextView profile, remove, kick_out;
    private String otherUserId,familyID, statuss, type="0", isAdmin,adminType;
    private boolean leader;
    private OnBottomSheetDismissListener onBottomSheetDismissListener;

    public BottomFragment(String userId, String status,String familyId, boolean admin,String isadmin) {
        otherUserId = userId;
        statuss = status;
        familyID = familyId;
        leader = admin;
        isAdmin = isadmin;
    }
    public void setOnBottomSheetDismissListener(OnBottomSheetDismissListener listener) {
        this.onBottomSheetDismissListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        if (leader==true) {
            profile.setVisibility(View.VISIBLE);
            remove.setVisibility(View.VISIBLE);
            kick_out.setVisibility(View.VISIBLE);
        } else {
            profile.setVisibility(View.VISIBLE);
            remove.setVisibility(View.GONE);
            kick_out.setVisibility(View.GONE);
        }

        if (isAdmin.equalsIgnoreCase("1")){
            remove.setText("Remove Admin");
        }else {
            remove.setText("Set Admin");
        }
        clicks();
    }

    private void clicks() {
        profile.setOnClickListener(v -> {
            if (otherUserId != null) {
                Bundle bundle = new Bundle();
                bundle.putString("otherUserId", otherUserId);
                bundle.putString("bottmheet", otherUserId);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
                this.dismiss();
            } else {

            }
        });
        kick_out.setOnClickListener(v -> {
            type="1";
            adminType = "";
            hitApi(type,adminType);
        });

        remove.setOnClickListener(v -> {
            type="2";
            adminType =  isAdmin;
            hitApi(type,adminType);
        });
    }

    private void hitApi(String type,String adminType) {
        Log.d("hitApi", "hitApi: "+type);
        Log.d("hitApi", "hitApi: "+familyID);
        Log.d("hitApi", "hitApi: "+otherUserId);
        Log.d("hitApi", "hitApi: "+AppConstants.USER_ID);
        new Mvvm().family_admin_remove(type,familyID,otherUserId, AppConstants.USER_ID,adminType).observe(requireActivity(), response ->{
            if(response!=null){
                if (response.success){
                    this.dismiss();
                }
                else {
                    this.dismiss();
                }
            }
            else {
                //Toast.makeText(requireContext(), "Technical issue..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findIds(View view) {
            profile = view.findViewById(R.id.profile);
        remove = view.findViewById(R.id.remove);
        kick_out = view.findViewById(R.id.kick_out);

    }
    public interface OnBottomSheetDismissListener {
        void onBottomSheetDismissed();
    }
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        // Call the listener when the BottomSheetDialogFragment is dismissed
        if (onBottomSheetDismissListener != null) {
            onBottomSheetDismissListener.onBottomSheetDismissed();
        }
    }

}