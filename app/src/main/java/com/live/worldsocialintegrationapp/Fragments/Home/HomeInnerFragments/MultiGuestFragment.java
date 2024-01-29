package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.Adapters.MultiGuestAdapter;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentMultiGuestBinding;


public class MultiGuestFragment extends Fragment {

    FragmentMultiGuestBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMultiGuestBinding.inflate( inflater, container, false );

        setAdapter();

        disableBottomNavigation();

        return binding.getRoot();

    }

    private void setAdapter() {

        binding.chatMultiGuestRecyclerView.setAdapter( new MultiGuestAdapter() );


    }
    private void disableBottomNavigation() {

        View view1 = requireActivity().findViewById( R.id.bottom_lay );

        view1.setVisibility( View.VISIBLE );
    }
}