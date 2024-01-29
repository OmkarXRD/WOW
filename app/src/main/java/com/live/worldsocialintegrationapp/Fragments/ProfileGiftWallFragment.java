package com.live.worldsocialintegrationapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.Adapters.ProfileGiftWallAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWallReceiverModelClass;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentProfileGiftWallBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class ProfileGiftWallFragment extends Fragment {

   List<GiftWallReceiverModelClass.Detail> list=new ArrayList<>();
    FragmentProfileGiftWallBinding binding;
    String userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileGiftWallBinding.inflate(LayoutInflater.from(getContext()),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.text.setText( App.getSharedpref().getString("name"));

        Bundle bundle = getArguments();

        userId = bundle.getString("userId");

        new Mvvm().getGiftWallRecordViewModel(requireActivity(), userId).observe(requireActivity(), new Observer<GiftWallReceiverModelClass>() {
            @Override
            public void onChanged(GiftWallReceiverModelClass giftWallReceiverModelClass) {
                 if (giftWallReceiverModelClass.getSuccess().equalsIgnoreCase("1")){
                     list=giftWallReceiverModelClass.getDetails();
                     if (isAdded() && getContext()!=null){
                         ProfileGiftWallAdapter profileGiftWallAdapter = new ProfileGiftWallAdapter(requireContext(),list);
                         binding.giftWallRecyclerView.setAdapter(profileGiftWallAdapter);
                     }
                 }
            }
        });
    }
}