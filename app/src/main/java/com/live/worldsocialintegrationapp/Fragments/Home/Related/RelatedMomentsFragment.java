package com.live.worldsocialintegrationapp.Fragments.Home.Related;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.Adapters.MomentsNotificationAdapter;
import com.live.worldsocialintegrationapp.databinding.FragmentRelatedMomentsBinding;

public class RelatedMomentsFragment extends Fragment {


    FragmentRelatedMomentsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRelatedMomentsBinding.inflate(inflater, container, false);

        binding.rvMomentsNotification.setAdapter(new MomentsNotificationAdapter());

        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}