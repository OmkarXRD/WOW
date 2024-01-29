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
import com.live.worldsocialintegrationapp.utils.App;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Fragment_family_new extends Fragment implements AdapterNew_family.Callback {
    RecyclerView recyclerView;
    private List<GetFamilyTopGiftersRoot.Detail> list;
    private AdapterNew_family adapter;
    private CircleImageView newfamilyTop1CirImg, newfamilyTop2CirImg, newfamilyTop3CirImg;
    private TextView newFamilyTop1Name, newFamilyTop2Name, newFamilyTop3Name,familyFire2Tv,familyFire1Tv,familyFire3Tv;
    private RelativeLayout newFamilyTop1RL,newFamilyTop2RL,newFamilyTop3RL,topone,toptwo,top3;
    private String familyClickTop1,familyClickTop2,familyClickTop3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_family_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);
        getFamilyApi();
    }

    private void findIds(View view) {

        recyclerView = view.findViewById(R.id.recycler_new);

        newfamilyTop1CirImg = view.findViewById(R.id.newfamilyTop1CirImg);
        newfamilyTop2CirImg = view.findViewById(R.id.newfamilyTop2CirImg);
        newfamilyTop3CirImg = view.findViewById(R.id.newfamilyTop3CirImg);

        newFamilyTop1Name = view.findViewById(R.id.newFamilyTop1Name);
        newFamilyTop2Name = view.findViewById(R.id.newFamilyTop2Name);
        newFamilyTop3Name = view.findViewById(R.id.newFamilyTop3Name);

        newFamilyTop1RL = view.findViewById(R.id.top1Img);
        newFamilyTop2RL = view.findViewById(R.id.top2Img);
        newFamilyTop3RL = view.findViewById(R.id.top3Img);

        familyFire1Tv = view.findViewById(R.id.familyFire1Tv);
        familyFire2Tv = view.findViewById(R.id.familyFire2Tv);
        familyFire3Tv = view.findViewById(R.id.familyFire3Tv);

        topone = view.findViewById(R.id.newFamilyTop1RL);
        toptwo = view.findViewById(R.id.newFamilyTop2RL);
        top3 = view.findViewById(R.id.newFamilyTop3RL);
    }

    private void clicks(View view) {
        newFamilyTop1RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop1);
            }
        });
        newFamilyTop2RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop2);
            }
        });
        newFamilyTop3RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFamily(familyClickTop3);
            }
        });
    }

    private void getFamilyApi() {
        new Mvvm().getFamilyTopGifters(requireActivity(), "1").observe(requireActivity(), new Observer<GetFamilyTopGiftersRoot>() {
            @Override
            public void onChanged(GetFamilyTopGiftersRoot getFamilyTopGiftersRoot) {
                if (getFamilyTopGiftersRoot.getStatus().equalsIgnoreCase("1")) {
                    list = new ArrayList<>();

                    if (getFamilyTopGiftersRoot.getDetails().isEmpty()) {

                    } else {
                        App.getSharedpref().saveString("family",getFamilyTopGiftersRoot.getDetails().get(0).familyName);
                        for (int i = 0; i < getFamilyTopGiftersRoot.getDetails().size(); i++) {
                            if (i == 0) {
                                topone.setVisibility(View.VISIBLE);
                                familyClickTop1 = getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                familyFire1Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                newFamilyTop1Name.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(newfamilyTop1CirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(newfamilyTop1CirImg);
                            } else if (i == 1) {
                                toptwo.setVisibility(View.VISIBLE);
                                familyClickTop2 = getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                familyFire2Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                newFamilyTop2Name.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(newfamilyTop2CirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(newfamilyTop2CirImg);
                            } else if (i == 2) {
                                top3.setVisibility(View.VISIBLE);
                                familyFire3Tv.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getTotal()));
                                familyClickTop3 = getFamilyTopGiftersRoot.getDetails().get(i).getId();
                                newFamilyTop3Name.setText(String.valueOf(getFamilyTopGiftersRoot.getDetails().get(i).getFamilyName()));
                                Glide.with(newfamilyTop3CirImg.getContext()).load(getFamilyTopGiftersRoot.getDetails().get(i).getImage()).error(R.drawable.demo_user_profile_img).into(newfamilyTop3CirImg);
                            } else {
                                list.add(getFamilyTopGiftersRoot.getDetails().get(i));
                            }
                        }
                            try {
                                   adapter = new AdapterNew_family(list, requireContext(), Fragment_family_new.this);
                                recyclerView.setAdapter(adapter);
                            }catch (Exception e){

                            }

                    }
                } else {
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
        App.getSharedpref().saveString("familyId",familyId);
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
    }
}