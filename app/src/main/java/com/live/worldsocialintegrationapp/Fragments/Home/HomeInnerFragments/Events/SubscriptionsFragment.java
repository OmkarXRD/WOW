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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.WhatsOnRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetEventsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


public class SubscriptionsFragment extends Fragment implements WhatsOnRVAdapter.Callback{

    private RecyclerView subscriptionRv;
    private List<GetEventsRoot.Detail> list;
    private WhatsOnRVAdapter whatsOnRVAdapter;
    private LinearLayout noSuscribtionLlayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscriptions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
        MySubscriptionApi();

    }

    private void findIds(View view) {
        subscriptionRv= view.findViewById(R.id.subscriptionRv);
        noSuscribtionLlayout= view.findViewById(R.id.noSuscribtionLlayout);
    }

    private  void MySubscriptionApi(){


        new Mvvm().getAllEvents(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetEventsRoot>() {
            @Override
            public void onChanged(GetEventsRoot getEventsRoot) {

                if (getEventsRoot != null) {
                    if (getEventsRoot.getSuccess().equalsIgnoreCase("1")) {
                        list = new ArrayList<>();
                        noSuscribtionLlayout.setVisibility(View.GONE);
                        for(int i=0;i<getEventsRoot.getDetails().size();i++){
                            if(getEventsRoot.getDetails().get(i).getEventCreaterId().equalsIgnoreCase(AppConstants.USER_ID)){
                                list.add(getEventsRoot.getDetails().get(i));
                            }else{
                            }
                        }

                        try {
                            whatsOnRVAdapter = new WhatsOnRVAdapter(SubscriptionsFragment.this, list, requireContext());
                            subscriptionRv.setAdapter(whatsOnRVAdapter);
                        }catch (Exception e){

                        }
                    } else {
                        noSuscribtionLlayout.setVisibility(View.VISIBLE);
                    }

                }
                else

                {
                    if(getContext() != null){
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
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


    //event detail
//    @Override
//    public void callback(GetEventsRoot.Detail detail) {
//        if (detail != null) {
//            Bundle bundle = new Bundle();
//            bundle.putString("eventId", detail.getId());
//            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventDetailsFragement, bundle);
//        } else {
//            Toast.makeText(requireContext(), "callback null", Toast.LENGTH_SHORT).show();
//        }
//    }

}