package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.Events;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.EventsInvitationsRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Events.GetAllEventInvitationsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Events.ResponseEventInvitationRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class EventsInvitationsFragment extends Fragment implements EventsInvitationsRVAdapter.Callback {

    private TextView noEventsInvitaionsTv;
    private RecyclerView eventsInvitationRV;
    List<GetAllEventInvitationsRoot.Detail> list;
    private EventsInvitationsRVAdapter eventsInvitationsRVAdapter;
    private ImageView eventsInvitationBackImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events_invitations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
        getEventInivationsApi(view);
    }

    private void clicks(View view) {

        eventsInvitationBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void getEventInivationsApi(View view) {

        new Mvvm().getAllEventsInvitations(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetAllEventInvitationsRoot>() {
            @Override
            public void onChanged(GetAllEventInvitationsRoot getAllEventInvitationsRoot) {

                if(getAllEventInvitationsRoot.getSuccess()==1){
                  list = new ArrayList<>();

                  list = getAllEventInvitationsRoot.getDetails();
                  if(list.isEmpty()){
                      noEventsInvitaionsTv.setVisibility(View.VISIBLE);
                  }else{
                      noEventsInvitaionsTv.setVisibility(View.GONE);
                      eventsInvitationsRVAdapter=new EventsInvitationsRVAdapter(list,EventsInvitationsFragment.this,requireContext());
                      eventsInvitationRV.setAdapter(eventsInvitationsRVAdapter);
                  }
                }

                }

        });
    }

    private void findIds(View view) {
        noEventsInvitaionsTv=view.findViewById(R.id.noEventsInvitaionsTv);
        eventsInvitationRV=view.findViewById(R.id.eventsInvitationRV);
        eventsInvitationBackImg=view.findViewById(R.id.eventsInvitationBackImg);
    }

    @Override
    public void acceptRejectInvitation(GetAllEventInvitationsRoot.Detail detail, String status) {

        if(detail != null){
            new Mvvm().responseEventInvitation(requireActivity(),AppConstants.USER_ID,detail.getId(),status).observe(requireActivity(), new Observer<ResponseEventInvitationRoot>() {
                @Override
                public void onChanged(ResponseEventInvitationRoot responseEventInvitationRoot) {

                    if(responseEventInvitationRoot.getSuccess()==1){
//                        Toast.makeText(requireContext(), "1 "+responseEventInvitationRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
//                        Toast.makeText(requireContext(), "0 "+responseEventInvitationRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{

        }
    }


}