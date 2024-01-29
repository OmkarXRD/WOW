package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.Events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.WhatsOnRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetEventsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.SuscribeUnscribeRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class WhatsOnFragment extends Fragment implements WhatsOnRVAdapter.Callback {

    private RecyclerView WhatOnRV;
    private List<GetEventsRoot.Detail> list;
    private WhatsOnRVAdapter whatsOnRVAdapter;
    private TextView noEventsFundTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_whats_on, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
        getAllEventsApi();
    }

    private void getAllEventsApi() {

        new Mvvm().getAllEvents(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetEventsRoot>() {
            @Override
            public void onChanged(GetEventsRoot getEventsRoot) {

                if (getEventsRoot != null) {
                    if (getEventsRoot != null) {

                        if (getEventsRoot.getSuccess().equalsIgnoreCase("1")) {
                            list = new ArrayList<>();
                            noEventsFundTv.setVisibility(View.GONE);
                            list = getEventsRoot.getDetails();
                            try {
                                whatsOnRVAdapter = new WhatsOnRVAdapter(WhatsOnFragment.this, list, requireContext());
                                WhatOnRV.setAdapter(whatsOnRVAdapter);
                            }catch (Exception e){

                            }
                        } else {
                            noEventsFundTv.setVisibility(View.VISIBLE);
                        }

                    } else {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                        } else {

                        }

                    }
                } else {
                    if (getContext() != null) {
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
    }

    private void findIds(View view) {
        WhatOnRV = view.findViewById(R.id.WhatOnRV);
        noEventsFundTv = view.findViewById(R.id.noEventsFundTv);
    }


    @Override
    public void callback(GetEventsRoot.Detail detail) {
        if (detail != null) {
            Bundle bundle = new Bundle();
            bundle.putString("eventId", detail.getId());
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventDetailsFragement, bundle);
        } else {
//            Toast.makeText(requireContext(), "callback null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void suscribeUnscribe(GetEventsRoot.Detail detail, AppCompatButton suscribeBtn) {

        new Mvvm().suscribeUnscribeEvent(requireActivity(), AppConstants.USER_ID, detail.getId()).observe(requireActivity(), new Observer<SuscribeUnscribeRoot>() {
            @Override
            public void onChanged(SuscribeUnscribeRoot suscribeUnscribeRoot) {

                if (suscribeUnscribeRoot.isSubscribe_status()) {
                    suscribeBtn.setText("Suscribed");
//                    Toast.makeText(requireContext(), "1 " + suscribeUnscribeRoot.getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    suscribeBtn.setText("Suscribe");
//                    Toast.makeText(requireContext(), "2 " + suscribeUnscribeRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void profileImgClick(GetEventsRoot.Detail detail) {

        if (detail != null) {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", detail.getEventCreaterId());
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
        } else {
            Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void shareBtn(GetEventsRoot.Detail detail) {

        Bundle bundle = new Bundle();
        FriendsFragment.check = 1;
        bundle.putString("eventId", detail.getId());
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.friendsFragment, bundle);
    }
}