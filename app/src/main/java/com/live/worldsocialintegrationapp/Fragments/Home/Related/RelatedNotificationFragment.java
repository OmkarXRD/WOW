package com.live.worldsocialintegrationapp.Fragments.Home.Related;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentRelatedNotificationBinding;


public class RelatedNotificationFragment extends Fragment {

    FragmentRelatedNotificationBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRelatedNotificationBinding.inflate(inflater, container, false);

        onClicks();
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.notificationFrameLayout, new Related_Live_Notification_Fragment()).addToBackStack(null).commit();
        binding.liveNotification.setBackgroundResource(R.drawable.pink_button);
        binding.liveNotification.setTextColor(getResources().getColor(R.color.white));
        binding.momentsNotification.setBackgroundResource(R.drawable.white_background);
        binding.momentsNotification.setTextColor(getResources().getColor(R.color.pink));

        return binding.getRoot();
    }

    private void onClicks() {

        binding.liveNotification.setOnClickListener(view -> {
            binding.liveNotification.setBackgroundResource(R.drawable.pink_button);
            binding.liveNotification.setTextColor(getResources().getColor(R.color.white));
            binding.momentsNotification.setBackgroundResource(R.drawable.white_background);
            binding.momentsNotification.setTextColor(getResources().getColor(R.color.pink));

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.notificationFrameLayout, new Related_Live_Notification_Fragment()).addToBackStack(null).commit();

        });

        binding.momentsNotification.setOnClickListener(view -> {

            binding.momentsNotification.setBackgroundResource(R.drawable.pink_button);
            binding.momentsNotification.setTextColor(getResources().getColor(R.color.white));
            binding.liveNotification.setBackgroundResource(R.drawable.white_background);
            binding.liveNotification.setTextColor(getResources().getColor(R.color.pink));

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.notificationFrameLayout, new RelatedMomentsFragment()).addToBackStack(null).commit();

        });
    }


}