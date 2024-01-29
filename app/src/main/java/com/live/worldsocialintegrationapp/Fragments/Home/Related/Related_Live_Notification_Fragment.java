package com.live.worldsocialintegrationapp.Fragments.Home.Related;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.Adapters.LiveNotificationAdapter;
import com.live.worldsocialintegrationapp.databinding.FragmentRelatedLiveNotificationBinding;


public class Related_Live_Notification_Fragment extends Fragment {


    FragmentRelatedLiveNotificationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRelatedLiveNotificationBinding.inflate(inflater, container, false);


        binding.rvLiveNotificationRelated.setAdapter(new LiveNotificationAdapter());

        return binding.getRoot();
    }
}