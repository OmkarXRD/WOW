package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.Events;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.EventDetailRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Events.EventsDetailsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.SuscribeUnscribeRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentEventDetailsFragementBinding;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsFragement extends Fragment implements EventDetailRVAdapter.Callback {

    View view;
    RecyclerView rvDetails;
    String eventId;
    List<EventsDetailsRoot.EventSubscriber> list;
    EventDetailRVAdapter eventDetailRVAdapter;


    FragmentEventDetailsFragementBinding binding;

    public EventDetailsFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEventDetailsFragementBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
        getDataFromBundle(view);
        setOnClicks(view);
        getEventDetailApi();
    }

    private void getEventDetailApi() {

        new Mvvm().getEventDetail(requireActivity(), eventId, AppConstants.USER_ID).observe(requireActivity(), new Observer<EventsDetailsRoot>() {
            @Override
            public void onChanged(EventsDetailsRoot eventsDetailsRoot) {

                if (eventsDetailsRoot.getSuccess().equalsIgnoreCase("1")) {

                    binding.eventDetailDesTv.setText(eventsDetailsRoot.getDetails().getDescription());
                    binding.eventDetailTopicTv.setText(eventsDetailsRoot.getDetails().getEvent_topic());
                    binding.eventDetailUserNameTv.setText(eventsDetailsRoot.getDetails().getName());
                    binding.eventDetailStartTimeTv.setText(eventsDetailsRoot.getDetails().getEvent_startTime());
                    binding.eventDetailMemberCountTv.setText(eventsDetailsRoot.getDetails().getEventSubscriber_counts());
                    Glide.with(binding.eventImgbg.getContext()).load(eventsDetailsRoot.getDetails().getEvent_image()).error(R.drawable.birthday_image_11).into(binding.eventImgbg);
                    Glide.with(requireContext()).load(eventsDetailsRoot.getDetails().getImageDp()).error(R.drawable.birthday_image_11).into(binding.eventDetailUserProfileImg);
                    eventId = eventsDetailsRoot.getDetails().getId();

                    if(eventsDetailsRoot.getDetails().isSubscriberStatus()){
                        binding.sub.setText("Suscribed");
                        binding.sub.setTextColor(Color.WHITE);
                    }else{
                        binding.sub.setText("Suscribe");
                        binding.sub.setTextColor(Color.WHITE);
                    }

                    list = new ArrayList<>();
                    list = eventsDetailsRoot.getDetails().getEventSubscribers();

                    if (eventsDetailsRoot.getDetails().getEventCreaterId().equalsIgnoreCase(AppConstants.USER_ID)) {
                        binding.sub.setVisibility(View.GONE);
                    } else {
                        binding.sub.setVisibility(View.VISIBLE);
                    }
                    if (list.isEmpty()) {
                        binding.noSuscriberTv.setVisibility(View.VISIBLE);
                    } else {
                        binding.noSuscriberTv.setVisibility(View.GONE);
                        eventDetailRVAdapter = new EventDetailRVAdapter(list, requireContext(), EventDetailsFragement.this);
                        rvDetails.setAdapter(eventDetailRVAdapter);
                    }
                } else {
                    if (getContext() != null){
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void getDataFromBundle(View view) {

        if (getArguments() != null) {
            eventId = getArguments().getString("eventId");
//            Toast.makeText(requireContext(), "eventId    " + eventId, Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(requireContext(), "Argmts null", Toast.LENGTH_SHORT).show();
        }
    }


    private void findIds(View view) {
        rvDetails = view.findViewById(R.id.detailRv);
    }

    private void setOnClicks(View view) {
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                FriendsFragment.check = 1;
                bundle.putString("eventId", eventId);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.friendsFragment, bundle);
            }
        });
        binding.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventsFriendFragment);
                suscribeUnscribeApi();
            }
        });
    }


    @Override
    public void callback(EventsDetailsRoot.EventSubscriber details) {

        if (details != null) {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", details.getUserId());
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
        } else {

        }
    }


    private void suscribeUnscribeApi() {

        new Mvvm().suscribeUnscribeEvent(requireActivity(), AppConstants.USER_ID, eventId).observe(requireActivity(), new Observer<SuscribeUnscribeRoot>() {
            @Override
            public void onChanged(SuscribeUnscribeRoot suscribeUnscribeRoot) {

                if (suscribeUnscribeRoot.isSubscribe_status()) {
                    binding.sub.setText("Suscribed");
                    binding.sub.setTextColor(Color.WHITE);
//                    getEventDetailApi();
                } else  {
                    binding.sub.setText("Suscribe");
                    binding.sub.setTextColor(Color.WHITE);
//                    getEventDetailApi();

                }
            }
        });

    }
}