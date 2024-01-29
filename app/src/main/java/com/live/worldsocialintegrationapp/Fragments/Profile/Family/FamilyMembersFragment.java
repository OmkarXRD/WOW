package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.FamilyMembersAdapter;
import com.live.worldsocialintegrationapp.BottomFragment;
import com.live.worldsocialintegrationapp.ModelClasses.Family.Joiner;
import com.live.worldsocialintegrationapp.R;

import java.util.ArrayList;
import java.util.List;


public class FamilyMembersFragment extends Fragment implements FamilyMembersAdapter.Callback ,BottomFragment.OnBottomSheetDismissListener {


    private RecyclerView familyMembersRv;
    private ImageView familyMembersBackImg;
    private FamilyMembersAdapter familyMembersAdapter;
    private List<Joiner> list = new ArrayList<>();
    private TextView noMemberFoundTv;
    private boolean admin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_members, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);


        if(getArguments() != null){
            list= (List<Joiner>)  getArguments().getSerializable("joinerList");
        }else{
            //Toast.makeText(requireContext(), "get Argmts null", Toast.LENGTH_SHORT).show();
        }

        if(list.isEmpty()){
            noMemberFoundTv.setVisibility(View.VISIBLE);
        }else{
            noMemberFoundTv.setVisibility(View.GONE);
            setAdapter(list);

        }

        admin =  getArguments().getBoolean("admin");
    }

    private void setAdapter(List<Joiner> list) {
        familyMembersAdapter = new FamilyMembersAdapter(list, getContext(), FamilyMembersFragment.this);
        familyMembersRv.setAdapter(familyMembersAdapter);

    }

    private void clicks(View view) {

        view.findViewById(R.id.familyMembersBackImg).setOnClickListener(view1 -> requireActivity().onBackPressed());
    }

    private void findIds(View view) {
        familyMembersRv = view.findViewById(R.id.familyMembersRv);
        familyMembersBackImg = view.findViewById(R.id.familyMembersBackImg);
        noMemberFoundTv = view.findViewById(R.id.noMemberFoundTv);
    }

    @Override
    public void callback(Joiner detail) {
        BottomFragment bottomFragment = new BottomFragment(detail.getUserId(),detail.getStatus(),detail.familyId,admin,detail.getIs_admin());
        bottomFragment.setOnBottomSheetDismissListener(this);
        bottomFragment.show(requireActivity().getSupportFragmentManager(),bottomFragment.getTag());
    }

    @Override
    public void onBottomSheetDismissed() {
        Log.d("onBottomSheetDismissed", "onBottomSheetDismissed: wqhbweqkhfbeqwf");
        setAdapter(list);
    }
}