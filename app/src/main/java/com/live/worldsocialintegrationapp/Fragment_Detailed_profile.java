package com.live.worldsocialintegrationapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.GiftWallAdapter;
import com.live.worldsocialintegrationapp.Adapters.NewGfitWallAdapter;
import com.live.worldsocialintegrationapp.Adapters.ProfileGiftWallAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection.Comments.DummyFragment;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWallReceiverModelClass;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import java.util.List;


public class Fragment_Detailed_profile extends Fragment {
    private RelativeLayout rlGfitWallRL,tags_layRL;
    private ImageView giftIMg1,giftIMg2,giftIMg3;
    private RecyclerView giftWallRecyclerView;

    private static final String ARG_PARAM1 = "userId";


    private String userId;


    public static Fragment_Detailed_profile newInstance(String userId) {
        Fragment_Detailed_profile fragment = new Fragment_Detailed_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userId);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null) {
            userId = getArguments().getString(ARG_PARAM1);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__detailed_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);
        hitApi();
    }

    private void hitApi() {

        new Mvvm().getGiftWallRecordViewModel(requireActivity(), userId).observe(requireActivity(), new Observer<GiftWallReceiverModelClass>() {
            @Override
            public void onChanged(GiftWallReceiverModelClass giftWallReceiverModelClass) {
                if (giftWallReceiverModelClass.getSuccess().equalsIgnoreCase("1")){
                    setGift(giftWallReceiverModelClass.getDetails());
                }
            }
        });
    }

    private void setGift(List<GiftWallReceiverModelClass.Detail> details) {

        NewGfitWallAdapter adapter = new NewGfitWallAdapter(details,requireContext());
        giftWallRecyclerView.setAdapter(adapter);
    }

    private void detailedItemDialogBox() {
        Dialog dialog_share = new Dialog( getContext() );
        dialog_share.setContentView( R.layout.item_detaild_dialog);
        dialog_share.getWindow().setBackgroundDrawable( new ColorDrawable( Color.WHITE ));
        dialog_share.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside( true );
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity( Gravity.BOTTOM );
        dialog_share.show();

    }

    private void clicks(View view) {

        rlGfitWallRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Navigation.findNavController(getActivity().findViewById(R.id.nav_home)).navigate(R.id.giftWallFragment);
                Bundle bundle = new Bundle();
                bundle.putString("userId",userId);
                Navigation.findNavController(getActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_profileGiftWall,bundle);
            }
        });
        view.findViewById(R.id.tags_layRL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailedItemDialogBox();
            }
        });
    }

    private void findIds(View view) {
        rlGfitWallRL = view.findViewById(R.id.rlGfitWallRL);
        tags_layRL = view.findViewById(R.id.tags_layRL);
        giftWallRecyclerView = view.findViewById(R.id.giftWallRecyclerView);

    }
}