package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.PopularTabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.Adapters.PopularExclusiveGuestAdapter;
import com.live.worldsocialintegrationapp.databinding.FragmentPopularExclusiveGuestBinding;


public class PopularEducationGuestFragment extends Fragment {

    FragmentPopularExclusiveGuestBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPopularExclusiveGuestBinding.inflate( inflater, container, false );

        setAdapter();

        return binding.getRoot();
    }

    private void setAdapter() {

        binding.popularExclusiveGuestRecyclerView.setAdapter( new PopularExclusiveGuestAdapter() );
    }
}