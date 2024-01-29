package com.live.worldsocialintegrationapp.Fragments.Profile.Settings;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.AdapterUSerBlocked;

import com.live.worldsocialintegrationapp.ModelClasses.BlockUser.RootBlocked;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;


public class Fragment_BlockedUser extends Fragment implements AdapterUSerBlocked.Callback {

    View view;
    RecyclerView rvBlock;
    TextView noBlockedUsedFound ;

    AdapterUSerBlocked adapterUSerBlocked;
    private ArrayList<RootBlocked.Detail> detailArrayList = new ArrayList<>();


    public Fragment_BlockedUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__blocked_user, container, false);
        findId(view);

        setOnClicks(view);
        apiGetBlocked(view);
        return view;

    }

    private void setOnClicks(View view) {
        view.findViewById(R.id.blockedBackImg).setOnClickListener(view1 -> getActivity().onBackPressed());
    }

    private void apiGetBlocked(View view) {

        new Mvvm().getBlockedUser(requireActivity(),"1").observe(requireActivity(), rootBlocked -> {

            if(rootBlocked.getSuccess() != null){

                if(rootBlocked.getSuccess().equalsIgnoreCase("1")) {

                    detailArrayList = (ArrayList<RootBlocked.Detail>) rootBlocked.getDetails();
                    adapterUSerBlocked = new AdapterUSerBlocked(detailArrayList, requireActivity(), Fragment_BlockedUser.this);
                    rvBlock.setAdapter(adapterUSerBlocked);
                    noBlockedUsedFound.setVisibility(View.GONE);
                }else {

                    noBlockedUsedFound.setVisibility(View.VISIBLE);
                }
            }
            else{
                noBlockedUsedFound.setVisibility(View.VISIBLE);
            }
        });
    }

    private void findId(View view) {
        rvBlock = view.findViewById(R.id.blockRV);
        noBlockedUsedFound = view.findViewById(R.id.noUserBlocked);
    }


    @Override
    public void openUserProfile(RootBlocked.Detail detail) {

        if(detail != null){
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", detail.getBlockUserId());
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
        }

    }
}