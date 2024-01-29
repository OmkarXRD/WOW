package com.live.worldsocialintegrationapp.Fragments.Union;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.GamesFragment;
import com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.MeetFragment;
import com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.MoreFragment;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentSecondMainBinding;


public class UnionMainFragment extends Fragment {

    FragmentSecondMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondMainBinding.inflate(inflater, container, false);
        setStatusBarGradiant(requireActivity());
        setTabs();
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();

        binding.GamesText.setTypeface(null, Typeface.BOLD);
        binding.relatedView.setVisibility(View.VISIBLE);
        binding.popularView.setVisibility(View.GONE);
        binding.nearbyView.setVisibility(View.GONE);

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.secondFrameLayout, new GamesFragment()).commit();

        disableBottomNavigation();

        binding.trophyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.yohoBoard);
            }
        });

    }

    private void disableBottomNavigation() {

        View view1 = requireActivity().findViewById(R.id.bottom_lay);

        view1.setVisibility(View.VISIBLE);
    }


    private void setTabs() {

        binding.Games.setOnClickListener(view -> {

            binding.GamesText.setTypeface(null, Typeface.BOLD);
            binding.MeetText.setTypeface(null, Typeface.NORMAL);
            binding.MoreText.setTypeface(null, Typeface.NORMAL);


            binding.relatedView.setVisibility(View.VISIBLE);
            binding.popularView.setVisibility(View.GONE);
            binding.nearbyView.setVisibility(View.GONE);

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.secondFrameLayout, new GamesFragment()).commit();

        });

        binding.Meet.setOnClickListener(view -> {
            binding.GamesText.setTypeface(null, Typeface.NORMAL);
            binding.MeetText.setTypeface(null, Typeface.BOLD);
            binding.MoreText.setTypeface(null, Typeface.NORMAL);

            binding.relatedView.setVisibility(View.GONE);
            binding.popularView.setVisibility(View.VISIBLE);
            binding.nearbyView.setVisibility(View.GONE);

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.secondFrameLayout, new MeetFragment()).commit();
        });

        binding.More.setOnClickListener(view -> {
            binding.GamesText.setTypeface(null, Typeface.NORMAL);
            binding.MeetText.setTypeface(null, Typeface.NORMAL);
            binding.MoreText.setTypeface(null, Typeface.BOLD);

            binding.relatedView.setVisibility(View.GONE);
            binding.popularView.setVisibility(View.GONE);
            binding.nearbyView.setVisibility(View.VISIBLE);


            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.secondFrameLayout, new MoreFragment()).commit();
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);

        }
    }
}