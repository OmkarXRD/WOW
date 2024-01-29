package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.databinding.FragmentEducationBinding;

public class EducationFragment extends Fragment {

    FragmentEducationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEducationBinding.inflate( inflater, container, false );




        return  binding.getRoot();

    }

}