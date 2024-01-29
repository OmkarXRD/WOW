package com.live.worldsocialintegrationapp.Fragments.Profile.My_look;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.Adapters.BubbleRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Image;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseCarsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.VipImagesRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Fragment_Bubble extends Fragment implements BubbleRVAdapter.Callback {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference nameAniamtion = firebaseDatabase.getReference().child("nameAniamtion");
    private Mvvm mvvm;
    private RecyclerView MyLookBubbleRV;
    BubbleRVAdapter bubbleRVAdapter;
    private List<VipImagesRoot.Detail> list;
    private ImageView vipNoImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mvvm = new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment__bubble, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        getBubbleImageApi();
    }

    private void getBubbleImageApi() {

        mvvm.getVipImages(requireActivity(),AppConstants.USER_ID).observe(requireActivity(), vipImagesRoot -> {
            if (vipImagesRoot != null) {
                list = new ArrayList<>();
                if (vipImagesRoot.getSuccess().equalsIgnoreCase("1")) {
                    list = vipImagesRoot.getDetails();
                    if (isAdded() && getContext()!=null){
                        bubbleRVAdapter = new BubbleRVAdapter(list, getContext(), Fragment_Bubble.this);
                        MyLookBubbleRV.setAdapter(bubbleRVAdapter);
                    }

                } else {
                }
            }
        });
    }

    private void findIds(View view) {
        MyLookBubbleRV = view.findViewById(R.id.MyLookBubbleRV);
        vipNoImage = view.findViewById(R.id.vipNoImage);
        vipNoImage = view.findViewById(R.id.vipNoImage);
    }

    @Override
    public void callback(VipImagesRoot.Detail detail) {

        if (detail != null) {

            Dialog viewDetails_box = new Dialog(requireContext());
            viewDetails_box.setContentView(R.layout.bubble_test_wear);
            viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
            Window window = viewDetails_box.getWindow();
            viewDetails_box.setCanceledOnTouchOutside(true);
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.CENTER;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
            window.setAttributes(wlp);
            //   viewDetails_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            viewDetails_box.show();
            viewDetails_box.setCanceledOnTouchOutside(true);

            ImageView image = viewDetails_box.findViewById(R.id.image);
            ImageView close_BTN = viewDetails_box.findViewById(R.id.close_BTN);

            RelativeLayout bubbleAnimationRL = viewDetails_box.findViewById(R.id.bubbleAnimationRL);
            Glide.with(image.getContext()).load(detail.getVipImage()).into(image);

            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
            bubbleAnimationRL.startAnimation(animation);

            close_BTN.setOnClickListener(view -> viewDetails_box.dismiss());
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void enable(VipImagesRoot.Detail detail, TextView textView) {

        if (detail != null) {

            mvvm.applyVip(AppConstants.USER_ID,detail.getId()).observe(requireActivity(),respone ->{
                if (respone!=null){
                    if (respone.getArjun()==1){
                        textView.setText("Remove");
                        nameAniamtion.child(AppConstants.USER_ID).setValue(detail.getVipImage());
                    }else {
                        textView.setText("Apply");
                    }
                }
            });

        }

    }


}