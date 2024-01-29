package com.live.worldsocialintegrationapp.Fragments.Live;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentGoLiveBinding;


public class GoLiveFragment extends Fragment {

    FragmentGoLiveBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGoLiveBinding.inflate(inflater, container, false);

        binding.btnGoLive.setOnClickListener(view -> {
            Navigation.findNavController( binding.getRoot() ).navigate( R.id.liveQualityFragment );
        });

        return binding.getRoot();
    }
}