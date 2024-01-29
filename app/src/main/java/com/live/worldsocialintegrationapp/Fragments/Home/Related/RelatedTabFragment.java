package com.live.worldsocialintegrationapp.Fragments.Home.Related;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentRelatedTabBinding;


public class RelatedTabFragment extends Fragment {

   FragmentRelatedTabBinding binding;
    private TabLayout tablayout_ExploreScreen;
    private ViewPager viewPager_ES;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentRelatedTabBinding.inflate( inflater, container, false );
        RelativeLayout relativeLayout = requireActivity().findViewById(R.id.rlHomeFragmentTop);
        relativeLayout.setVisibility(View.VISIBLE);
        disableBottomNavigation();

//        hitLiveUserApi();
        clicks();
        return binding.getRoot();
    }


    private void clicks() {
//
//
//        binding.relatedTabFamilyIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.fragment_Family);
//            }
//        });
        binding.momentRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.moment);
            }
        });

        binding.relatedLiveRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.relatedLiveFragment);
            }
        });
    }
//
//    private void hitLiveUserApi() {
//        new Mvvm().getLiveUserApi(requireActivity()).observe(requireActivity(), liveUserModel -> {
//            if (liveUserModel.getSuccess().equalsIgnoreCase("1")) {
//                binding.txtNoUserFound.setText("");
//                setAdapter(liveUserModel.getDetails());
//            } else {
//                binding.txtNoUserFound.setText("No Live Users");
//            }
//        });
//    }
//
//
//    private void setAdapter(List<LiveUserModel.Detail> details) {
//        PopularLiveAdapter popularLiveAdapter = new PopularLiveAdapter( details, detail -> {
//            App.getSingletone().setLiveType("");
//            Intent intent = new Intent(requireActivity(), CallActivity.class);
//            intent.putExtra("channelName", detail.getChannelName());
//            intent.putExtra("userId", detail.getUserId());
//            intent.putExtra("liveType", "multiLive");
//            intent.putExtra("liveStatus", "host");
//            intent.putExtra("token", detail.getToken());
//            intent.putExtra("name", detail.getName());
//            intent.putExtra("liveHostId", detail.getId());
//            intent.putExtra("image", detail.getImage());
//            intent.putExtra("status", "1");
//            startActivity(intent);
//        });
//        binding.recyclerViewPopularLive.setAdapter(popularLiveAdapter);
//
//    }
    private void disableBottomNavigation() {
        View view1 = requireActivity().findViewById( R.id.bottom_lay );
        view1.setVisibility( View.VISIBLE );

    }

    public void tabLayoutMethod() {

        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Daily Tasks"));
        tablayout_ExploreScreen.addTab(tablayout_ExploreScreen.newTab().setText("Family Tasks"));
        tablayout_ExploreScreen.setTabGravity(TabLayout.GRAVITY_FILL);

    }

}



