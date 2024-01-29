package com.live.worldsocialintegrationapp.Fragments;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.live.worldsocialintegrationapp.agora.firebase.CommentAdapter.liveHostBackImg;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.live.worldsocialintegrationapp.Adapters.AdapterLiveThemes;
import com.live.worldsocialintegrationapp.ModelClasses.GetLiveBackgroundImagesRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;


public class LiveFreeThemeFragment extends Fragment {

    ArrayList<GetLiveBackgroundImagesRoot.Detail> liveBackgroundImagesList;
    AdapterLiveThemes adapterLiveThemes;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference userLiveBackImgRef = firebaseDatabase.getReference().child("userLiveBackImgRef");
    public static String liveHostBackImg ="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_free_theme, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//                new Mvvm().getLiveBackgroundImages(requireContext()).observe(requireActivity(), new Observer<GetLiveBackgroundImagesRoot>() {
//            @Override
//            public void onChanged(GetLiveBackgroundImagesRoot getLiveBackgroundImagesRoot) {
//                liveBackgroundImagesList = getLiveBackgroundImagesRoot.getDetails();
//
//                RecyclerView rvThemes = dialog_share.findViewById(R.id.recycler_viewThems);
//
//                adapterLiveThemes = new AdapterLiveThemes(liveBackgroundImagesList, new AdapterLiveThemes.Callback() {
//                    @Override
//                    public void setBackground(GetLiveBackgroundImagesRoot.Detail detail) {
//
//                        userLiveBackImgRef.child(liveHostBackImg).setValue(detail.getImages()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
//                                    Glide.with(getApplicationContext()).load(detail.getImages())
//                                            .error(R.drawable.night).into(binding.imgBackground);
//                                }
//                                dialog_share.dismiss();
//                                dialog.dismiss();
//                            }
//                        });
//                        adapterLiveThemes.notifyDataSetChanged();
//                        dialog_share.dismiss();
//                        dialog.dismiss();
//                    }
//                });
//                rvThemes.setAdapter(adapterLiveThemes);
//            }
//        });

        new Mvvm().getLiveBackgroundImages(requireActivity()).observe(requireActivity(), new Observer<GetLiveBackgroundImagesRoot>() {
            @Override
            public void onChanged(GetLiveBackgroundImagesRoot getLiveBackgroundImagesRoot) {

                if(getLiveBackgroundImagesRoot != null){
                    liveBackgroundImagesList = getLiveBackgroundImagesRoot.getDetails();
//
                RecyclerView rvThemes = view.findViewById(R.id.recycler_viewThems);

                adapterLiveThemes = new AdapterLiveThemes(liveBackgroundImagesList, new AdapterLiveThemes.Callback() {
                    @Override
                    public void setBackground(GetLiveBackgroundImagesRoot.Detail detail) {

                        userLiveBackImgRef.child(liveHostBackImg).setValue(detail.getImages()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (liveHostBackImg.equalsIgnoreCase(AppConstants.USER_ID)) {
//                                    Glide.with(getApplicationContext()).load(detail.getImages())
//                                            .error(R.drawable.night).into(binding.imgBackground);
                                }
                            }
                        });
                        adapterLiveThemes.notifyDataSetChanged();

                    }
                });
                rvThemes.setAdapter(adapterLiveThemes);
                }

            }
        });




    }
}