package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

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
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Monthly extends Fragment implements AdapterNew_family.Callback {
    private RecyclerView recyclerView;
    private List<GetFamilyTopGiftersRoot.Detail> list;
    private AdapterNew_family adapter;

    private TextView monthlyTop1FamilyNameTv, monthlyTop2FamilyNameTv, monthlyTop3FamilyNameTv,monthlyfamilyFire1Tv,monthlyfamilyFire2Tv,monthlyfamilyFire3Tv;
    private CircleImageView monthlyTop1FamilyCirImg, monthlyTop2FamilyCirImg, monthlyTop3FamilyCirImg;
    private RelativeLayout monthlyFamilyTop1RL, monthlyFamilyTop2RL, monthlyFamilyTop3RL;

    private String familyClickTop1,familyClickTop2,familyClickTop3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__monthly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clicks(view);
        getFamilyApi();
    }

    private void findIds(View view) {
        recyclerView = view.findViewById(R.id.recycler_monthly);

        monthlyTop1FamilyNameTv = view.findViewById(R.id.monthlyFamilyTop1Name);
        monthlyTop2FamilyNameTv = view.findViewById(R.id.monthlyFamilyTop2Name);
        monthlyTop3FamilyNameTv = view.findViewById(R.id.monthlyFamilyTop3Name);

        monthlyTop1FamilyCirImg = view.findViewById(R.id.monthlyfamilyTop1CirImg);
        monthlyTop2FamilyCirImg = view.findViewById(R.id.monthlyfamilyTop2CirImg);
        monthlyTop3FamilyCirImg = view.findViewById(R.id.monthlyfamilyTop3CirImg);

        monthlyFamilyTop1RL = view.findViewById(R.id.monthlyFamilyTop1RL);
        monthlyFamilyTop2RL = view.findViewById(R.id.monthlyFamilyTop2RL);
        monthlyFamilyTop3RL = view.findViewById(R.id.monthlyFamilyTop3RL);

        monthlyfamilyFire1Tv = view.findViewById(R.id.monthlyfamilyFire1Tv);
        monthlyfamilyFire2Tv = view.findViewById(R.id.monthlyfamilyFire2Tv);
        monthlyfamilyFire3Tv = view.findViewById(R.id.monthlyfamilyFire3Tv);
    }
    private void clicks(View view) {
        monthlyFamilyTop1RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop1);
            }
        });
        monthlyFamilyTop2RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop2);
            }
        });
        monthlyFamilyTop3RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop3);
            }
        });
    }


    private void getFamilyApi() {

        new Mvvm().getFamilyTopGifters(requireActivity(), "3").observe(requireActivity(), new Observer<GetFamilyTopGiftersRoot>() {
            @Override
            public void onChanged(GetFamilyTopGiftersRoot getFamilyTopGiftersRoot) {
                if (getFamilyTopGiftersRoot.getStatus().equalsIgnoreCase("1")) {
                    list = new ArrayList<>();

                    if (getFamilyTopGiftersRoot.getDetails().isEmpty()) {
                    } else {
                        for (int i = 0; i < getFamilyTopGiftersRoot.getDetails().size(); i++) {
                            if (i == 0) {
                                monthlyFamilyTop1RL.setVisibility(View.VISIBLE);
                                familyClickTop1= getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                monthlyfamilyFire1Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                monthlyTop1FamilyNameTv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(monthlyTop1FamilyCirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(monthlyTop1FamilyCirImg);
                            } else if (i == 1) {
                                monthlyFamilyTop2RL.setVisibility(View.VISIBLE);
                                familyClickTop2= getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                monthlyfamilyFire2Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                monthlyTop2FamilyNameTv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(monthlyTop2FamilyCirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(monthlyTop2FamilyCirImg);
                            } else if (i == 2) {
                                monthlyFamilyTop3RL.setVisibility(View.VISIBLE);
                                familyClickTop3= getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                monthlyfamilyFire3Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                monthlyTop3FamilyNameTv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(monthlyTop3FamilyCirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(monthlyTop3FamilyCirImg);
                            } else {
                                list.add(getFamilyTopGiftersRoot.getDetails().get(i));
                            }
                        }
                        try {
                            adapter = new AdapterNew_family(list, requireContext(), Fragment_Monthly.this);
                            recyclerView.setAdapter(adapter);
                        }catch (Exception e){

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

    private void openFamily(String familyId){
        Bundle bundle = new Bundle();
        bundle.putString("familyId", familyId);
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
    }
}