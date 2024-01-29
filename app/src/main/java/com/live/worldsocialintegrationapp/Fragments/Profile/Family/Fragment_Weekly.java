package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.AdapterNew_family;
import com.live.worldsocialintegrationapp.ModelClasses.Family.GetFamilyTopGiftersRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Weekly extends Fragment implements AdapterNew_family.Callback {

    Context context;
    private RecyclerView recyclerView;
    private List<GetFamilyTopGiftersRoot.Detail> list;
    private AdapterNew_family adapter;
    private CircleImageView weeklyTop1FamilyCirImg, weeklyTop2FamilyCirImg, weeklyTop3FamilyCirImg;
    private TextView weeklyTop1FamilyNameTv, weeklyTop2FamilyNameTv, weeklyTop3FamilyNameTv,weeklyfamilyFire2Tv,weeklyfamilyFire1Tv,weeklyfamilyFire3Tv;
    private RelativeLayout weeklyFamilyTop1RL, weeklyFamilyTop2RL, weeklyFamilyTop3RL;
    private String familyClickTop1,familyClickTop2,familyClickTop3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__weekly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);
        getFamilyApi();
    }

    private void findIds(View view) {
        recyclerView = view.findViewById(R.id.recycler_weekly);
        weeklyTop1FamilyCirImg = view.findViewById(R.id.weeklyfamilyTop1CirImg);
        weeklyTop2FamilyCirImg = view.findViewById(R.id.weeklyfamilyTop2CirImg);
        weeklyTop3FamilyCirImg = view.findViewById(R.id.weeklyfamilyTop3CirImg);

        weeklyTop1FamilyNameTv = view.findViewById(R.id.weeklyFamilyTop1Name);
        weeklyTop2FamilyNameTv = view.findViewById(R.id.weeklyFamilyTop2Name);
        weeklyTop3FamilyNameTv = view.findViewById(R.id.weeklyFamilyTop3Name);

        weeklyFamilyTop1RL = view.findViewById(R.id.weeklyFamilyTop1RL);
        weeklyFamilyTop2RL = view.findViewById(R.id.weeklyFamilyTop2RL);
        weeklyFamilyTop3RL = view.findViewById(R.id.weeklyFamilyTop3RL);


        weeklyfamilyFire1Tv = view.findViewById(R.id.weeklyfamilyFire1Tv);
        weeklyfamilyFire2Tv = view.findViewById(R.id.weeklyfamilyFire2Tv);
        weeklyfamilyFire3Tv = view.findViewById(R.id.weeklyfamilyFire3Tv);
    }

    private void clicks(View view) {
        weeklyFamilyTop1RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop1);
            }
        });
        weeklyFamilyTop2RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop2);
            }
        });
        weeklyFamilyTop3RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop3);
            }
        });
    }

    private void getFamilyApi() {

        new Mvvm().getFamilyTopGifters(requireActivity(), "2").observe(requireActivity(), new Observer<GetFamilyTopGiftersRoot>() {
            @Override
            public void onChanged(GetFamilyTopGiftersRoot getFamilyTopGiftersRoot) {
                if (getFamilyTopGiftersRoot.getStatus().equalsIgnoreCase("1")) {
                    list = new ArrayList<>();

                    if (getFamilyTopGiftersRoot.getDetails().isEmpty()) {
                    }
                    else {
                        for (int i = 0; i < getFamilyTopGiftersRoot.getDetails().size(); i++) {
                            if (i == 0) {
                                weeklyFamilyTop1RL.setVisibility(View.VISIBLE);
                                familyClickTop1 = getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                weeklyfamilyFire1Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                weeklyTop1FamilyNameTv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(weeklyTop1FamilyCirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(weeklyTop1FamilyCirImg);
                            } else if (i == 1) {
                                weeklyFamilyTop2RL.setVisibility(View.VISIBLE);
                                familyClickTop2 = getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                weeklyfamilyFire2Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                weeklyTop2FamilyNameTv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(weeklyTop2FamilyCirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(weeklyTop2FamilyCirImg);
                            } else if (i == 2) {
                                weeklyFamilyTop3RL.setVisibility(View.VISIBLE);
                                familyClickTop3 = getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                weeklyfamilyFire3Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                weeklyTop3FamilyNameTv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(weeklyTop3FamilyCirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(weeklyTop3FamilyCirImg);
                            } else {
                                list.add(getFamilyTopGiftersRoot.getDetails().get(i));
                                if (requireContext() !=null){
                                    try {
                                        adapter = new AdapterNew_family(list, requireContext(), Fragment_Weekly.this);
                                        recyclerView.setAdapter(adapter);
                                    }catch (Exception e){

                                    }
                                }
                            }
                        }

                    }
                } else {
//                    Toast.makeText(requireContext(), "0 " + getFamilyTopGiftersRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void callback(GetFamilyTopGiftersRoot.Detail detail) {
        if (detail != null) {
            openFamily(detail.getId().toString());
        } else {
//            Toast.makeText(requireContext(), "list is empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFamily(String familyId) {
        Bundle bundle = new Bundle();
        bundle.putString("familyId", familyId);
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context= context;
    }
}