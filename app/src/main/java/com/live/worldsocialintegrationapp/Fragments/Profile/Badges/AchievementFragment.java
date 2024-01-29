package com.live.worldsocialintegrationapp.Fragments.Profile.Badges;

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

import com.live.worldsocialintegrationapp.Adapters.AchiementRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserTalentLevelRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import java.util.ArrayList;
import java.util.List;

public class AchievementFragment extends Fragment {

    RecyclerView achieivementRv;
    List<GetUserTalentLevelRoot.Detail> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findIds(view);



        getUserTalentLevelsApi();
    }

    private void findIds(View view) {
        achieivementRv=view.findViewById(R.id.achieivementRv);


    }

    private void getUserTalentLevelsApi(){

        new Mvvm().getUserTalentLevel(requireActivity()).observe(requireActivity(), new Observer<GetUserTalentLevelRoot>() {
            @Override
            public void onChanged(GetUserTalentLevelRoot getUserTalentLevelRoot) {

                if(getUserTalentLevelRoot != null) {

                    if (getUserTalentLevelRoot.getStatus() == 1) {
//                    Toast.makeText(requireContext(), "1 "+getUserTalentLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        list = new ArrayList<>();
                        list = getUserTalentLevelRoot.getDetails();

                        if (isAdded() && getContext()!=null){
                            AchiementRVAdapter achiementRVAdapter = new AchiementRVAdapter(requireContext(), list, new AchiementRVAdapter.Callback() {
                                @Override
                                public void callback(List<GetUserTalentLevelRoot.Child> childDetail) {

                                    if (childDetail != null) {

                                        BadgeDetailFragment.list = childDetail;
                                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.badgeDetailFragment);
                                    } else {
//                                Toast.makeText(requireContext(), "Callback is null", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            achieivementRv.setAdapter(achiementRVAdapter);
                        }


                    } else {
//                        Toast.makeText(requireContext(), "0 " + getUserTalentLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}