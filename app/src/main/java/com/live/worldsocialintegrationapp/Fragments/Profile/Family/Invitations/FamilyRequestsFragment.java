package com.live.worldsocialintegrationapp.Fragments.Profile.Family.Invitations;

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

import com.live.worldsocialintegrationapp.Adapters.FamilyRequestRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetJoinRequestRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.ResponseJoinRequestRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class FamilyRequestsFragment extends Fragment implements FamilyRequestRVAdapter.Callback {

    List<GetJoinRequestRoot.Detail> list;
    RecyclerView familyRequestRV;
    TextView noRequestTv;
    FamilyRequestRVAdapter familyRequestRVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_requests, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);

        getJoinRequestApi();

    }

    private void findIds(View view) {

        familyRequestRV = view.findViewById(R.id.familyRequestRV);
        noRequestTv = view.findViewById(R.id.noRequestTv);
    }

    private void getJoinRequestApi() {

        // AppConstants.USER_ID
        new Mvvm().getJoinRequest(requireActivity(),AppConstants.USER_ID).observe(requireActivity(), new Observer<GetJoinRequestRoot>() {
            @Override
            public void onChanged(GetJoinRequestRoot getJoinRequestRoot) {

                if (getJoinRequestRoot.getSuccess().equalsIgnoreCase("1")) {
                    list = new ArrayList<>();
                    list = getJoinRequestRoot.getDetails();

                    if (list.isEmpty()) {
                        noRequestTv.setVisibility(View.VISIBLE);
                    } else {
                        noRequestTv.setVisibility(View.GONE);

                        familyRequestRVAdapter = new FamilyRequestRVAdapter(list, FamilyRequestsFragment.this, requireContext());
                        familyRequestRV.setAdapter(familyRequestRVAdapter);
                    }

                } else {

                }
            }
        });
    }


    @Override
    public void acceptOrRejectRequest(GetJoinRequestRoot.Detail detail, String status, AppCompatButton textView) {

        if (detail != null) {

            new Mvvm().responseJoinRequest(requireActivity(), detail.getId(),detail.getUserId().toString(), status).observe(requireActivity(), new Observer<ResponseJoinRequestRoot>() {
                @Override
                public void onChanged(ResponseJoinRequestRoot responseJoinRequestRoot) {


                    if (responseJoinRequestRoot.getStatus() == 2) {
                        textView.setText("Accepted");
                        App.getSharedpref().saveString("statusFamilyjoin",String.valueOf(responseJoinRequestRoot.getStatus()));
                    }else if(responseJoinRequestRoot.getStatus()==3) {
                        textView.setText("Rejected");
                    }
                    else {
//                        Toast.makeText(requireContext(), "0 " + responseJoinRequestRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
//            Toast.makeText(requireContext(), "callback null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void callback(GetJoinRequestRoot.Detail detail) {

        if (detail != null) {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", detail.getUserId());
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
        } else {

        }
    }
}