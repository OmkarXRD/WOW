package com.live.worldsocialintegrationapp.Fragments.Profile.Settings.About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class AboutFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        clicks(view);


    }

    private void clicks(View view) {

        view.findViewById(R.id.aboutBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        view.findViewById(R.id.privacyPolicyTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.privacyPolicyFragment);
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://omninos.life/Social_Integration/privecy.html")));
            }
        });
       view.findViewById(R.id.termsSericesTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.termsConditionsTV);
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://omninos.life/Social_Integration/termandcondition.html")));
            }
        });
    }
}