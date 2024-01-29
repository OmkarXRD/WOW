package com.live.worldsocialintegrationapp.Fragments.Profile.My_look;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.Adapters.GetPurChaseThemeAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetPurchaseGallery;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.opensource.svgaplayer.SVGAImageView;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Color_ID extends Fragment implements GetPurChaseThemeAdapter.Callback{

    private List<GetPurchaseGallery.Detail>list=new ArrayList<>();
    private RecyclerView recyclerView;
    private Mvvm viewModel;
    private GetPurChaseThemeAdapter getPurChaseThemeAdapter;
    private TextView themeTV;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference userLiveBackImgRef = firebaseDatabase.getReference().child("userLiveBackImgRef");
    private String themeID;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel=new ViewModelProvider(this).get(Mvvm.class);
        return inflater.inflate(R.layout.fragment__color__i_d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.framesRV);
        themeTV=view.findViewById(R.id.themeTV);
        getPurchaseFrame();
    }

    private void getPurchaseFrame() {
        viewModel.getPurchaseThemes(AppConstants.USER_ID).observe(requireActivity(),response->{
            if(response!=null){
                if(response.getStatus()==1){
                    Toast.makeText(requireContext(), "1"+response.getMessage(), Toast.LENGTH_SHORT).show();
                    recyclerView.setVisibility(View.VISIBLE);
                    themeTV.setVisibility(View.GONE);
                    list=response.getDetails();
                    getPurChaseThemeAdapter=new GetPurChaseThemeAdapter(list,requireContext(),Fragment_Color_ID.this);
                    recyclerView.setAdapter(getPurChaseThemeAdapter);
                }else{
                    recyclerView.setVisibility(View.GONE);
                    themeTV.setVisibility(View.VISIBLE);
                    Toast.makeText(requireContext(), "0txryugih"+response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(requireContext(), "data null", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void enableFrame(GetPurchaseGallery.Detail detail, TextView view) {
        if(detail!=null) {

            if (detail.getType().equalsIgnoreCase("2")){
                themeID  =  detail.getId();
            }else {
                themeID = detail.getThemeId();
            }

            viewModel.applyTheme(AppConstants.USER_ID, themeID,detail.getType()).observe(requireActivity(), response -> {
                if (response != null) {
                    if (response.getStatus() == 1) {
                        view.setText("Remove");
                        enableTheme(detail.getImage());
                        getPurchaseFrame();
                        setTheme();
                        getPurChaseThemeAdapter.notifyDataSetChanged();
                        Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show();
                        Alerter.create(requireActivity()).setTitle("Applied").setText("this frame is Applied").setBackgroundResource(R.drawable.wallet_background).show();
                    } else {
                        view.setText("Apply");
                        Toast.makeText(requireContext(), "0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (getContext() != null) {
                        Toast.makeText(requireContext(), "Technical issue..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }else {
            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
        }
        


    }

    @Override
    public void testCars(GetPurchaseGallery.Detail detail) {
        Dialog viewDetails_box = new Dialog(requireContext());
        viewDetails_box.setContentView(R.layout.image_dialog);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        viewDetails_box.show();
        viewDetails_box.setCanceledOnTouchOutside(true);
        ImageView imageView = viewDetails_box.findViewById(R.id.img_profile);
        imageView.setVisibility(View.GONE);
        SVGAImageView svgaImage = viewDetails_box.findViewById(R.id.frames);
        svgaImage.setVisibility(View.GONE);
        ImageView theamImage = viewDetails_box.findViewById(R.id.theamImage);
        theamImage.setVisibility(View.VISIBLE);
        Glide.with(requireContext()).load(detail.getImage()).error(R.drawable.wow_icon).into(theamImage);
        ImageView closeBtn = viewDetails_box.findViewById(R.id.close_BTN);
        closeBtn.setOnClickListener(view -> viewDetails_box.dismiss());
    }

    private void enableTheme(String image) {

        userLiveBackImgRef.child(AppConstants.USER_ID).setValue(image).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    private void setTheme() {
        userLiveBackImgRef.child(AppConstants.USER_ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
//                    GetPurChaseThemeAdapter.themeUrl= snapshot.getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
