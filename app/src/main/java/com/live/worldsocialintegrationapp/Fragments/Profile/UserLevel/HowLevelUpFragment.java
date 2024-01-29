package com.live.worldsocialintegrationapp.Fragments.Profile.UserLevel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_cars;
import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_frames;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentProfileMainBinding;


public class HowLevelUpFragment extends Fragment {

    private FragmentProfileMainBinding binding;
    private ImageView howTolevelBackImg;
    private RelativeLayout howLevetCarstRL, wealthInsideFrameRL, howlevelBubbleRL, vipParentRL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileMainBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how_level_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
    }

    private void clicks(View view) {

        howTolevelBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        howLevetCarstRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("CarsCheck",1);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_cars,bundle);
            }
        });
        wealthInsideFrameRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Fragment_frames.check=1;
                Bundle bundle = new Bundle();
                bundle.putInt("frameCheck",1);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_frames,bundle);
            }
        });
        howlevelBubbleRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("CarsCheck",1);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_cars,bundle);
            }
        });

        //add listener when clicked on vipParentRL

    }

    private void findIds(View view) {
        howTolevelBackImg = view.findViewById(R.id.howTolevelBackImg);
        howLevetCarstRL = view.findViewById(R.id.howLevetCarstRL);
        wealthInsideFrameRL = view.findViewById(R.id.wealthInsideFrameRL);
        howlevelBubbleRL = view.findViewById(R.id.howlevelBubbleRL);
        vipParentRL = view.findViewById(R.id.buyVipParentRL);
    }
}