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

import com.live.worldsocialintegrationapp.Adapters.FamilyInvitationsRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetInvitationsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.Family.InvitationsResponseRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;


public class FamilyInvitationTab extends Fragment implements FamilyInvitationsRVAdapter.Callback {

    List<GetInvitationsRoot.Detail> list;
    TextView noInvitaionsTv;
    FamilyInvitationsRVAdapter familyInvitationsRVAdapter;
    RecyclerView familyInvitationRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_invitation_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        getInvitationsApi();
    }

    private void getInvitationsApi() {
        //AppConstants.USER_ID
        new Mvvm().getFamilyInvitation(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetInvitationsRoot>() {
            @Override
            public void onChanged(GetInvitationsRoot getInvitationsRoot) {

                if (getInvitationsRoot != null) {


                    if (getInvitationsRoot.getStatus() == 1) {
//                    Toast.makeText(requireContext(), "1 "+getInvitationsRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        list = new ArrayList<>();
                        list = getInvitationsRoot.getDetails();

                        if (list.isEmpty()) {
                            noInvitaionsTv.setVisibility(View.VISIBLE);
                        } else {
                            noInvitaionsTv.setVisibility(View.GONE);
                            familyInvitationsRVAdapter = new FamilyInvitationsRVAdapter(list, requireContext(), FamilyInvitationTab.this);
                            familyInvitationRV.setAdapter(familyInvitationsRVAdapter);
                        }
                    } else {

                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findIds(View view) {
        noInvitaionsTv = view.findViewById(R.id.noInvitaionsTv);
        familyInvitationRV = view.findViewById(R.id.familyInvitationRV);
    }

    @Override
    public void callback(GetInvitationsRoot.Detail detail) {
        if (detail != null) {
            Bundle bundle = new Bundle();
            bundle.putString("otherUserId", detail.getUserId());
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.otherUser, bundle);
        } else {

        }
    }

    @Override
    public void AcceptAndRejcetInvitation(GetInvitationsRoot.Detail detail, String status, AppCompatButton acceptOrRejectBtn) {

        if (detail != null) {
            new Mvvm().responseInvitation(requireActivity(), detail.getUserId(), detail.getId(), status).observe(requireActivity(), new Observer<InvitationsResponseRoot>() {
                @Override
                public void onChanged(InvitationsResponseRoot invitationsResponseRoot) {

                    if (invitationsResponseRoot.getStatus() == 2) {

                        acceptOrRejectBtn.setText("Accepted");
                        familyInvitationsRVAdapter.notifyDataSetChanged();

                    } else if (invitationsResponseRoot.getStatus() == 3) {

                        acceptOrRejectBtn.setText("Rejected");
                        familyInvitationsRVAdapter.notifyDataSetChanged();

                    } else {

                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "" + invitationsResponseRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "" + invitationsResponseRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            //Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }


}