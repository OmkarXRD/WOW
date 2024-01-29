package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.Trophy;

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

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.AdapterRooms;
import com.live.worldsocialintegrationapp.ModelClasses.wowsBoard.Datum;
import com.live.worldsocialintegrationapp.ModelClasses.wowsBoard.WowsBoardRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;

import java.util.ArrayList;
import java.util.List;


public class BoardWeeklyFragment extends Fragment implements AdapterRooms.Callback {

    private RecyclerView rv_daily;
    private List<Datum> list;
    private AdapterRooms adapterRooms;
    private ImageView profile_photo2, profile_photo3, profile_photo1;
    private TextView dailyTop1DiamondTv, dailyTop3DiamondTv, dailyTop2DiamondTv;
    private List<Datum> firstThreeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board_weekly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        boardApi();

    }

    private void findIds(View view) {

        rv_daily = view.findViewById(R.id.weeklyrecycler_Board);

        dailyTop1DiamondTv = view.findViewById(R.id.weeklyTop1DiamondTv);
        dailyTop3DiamondTv = view.findViewById(R.id.weeklyTop2DiamondTv);
        dailyTop2DiamondTv = view.findViewById(R.id.weeklyTop3DiamondTv);

        profile_photo2 = view.findViewById(R.id.weekly1Profile_photo2);
        profile_photo3 = view.findViewById(R.id.weekly1profile_photo3);
        profile_photo1 = view.findViewById(R.id.weekly1profile_photo1);

    }

    private void boardApi() {

        new Mvvm().getWowsBoard(requireActivity(), "reciever", "2").observe(requireActivity(), new Observer<WowsBoardRoot>() {
            @Override
            public void onChanged(WowsBoardRoot wowsBoardRoot) {

                if (wowsBoardRoot != null) {
                    if (wowsBoardRoot.getSuccess().equalsIgnoreCase("1")) {
                        list = new ArrayList<>();
                        firstThreeList = new ArrayList<>();


                        for (int i = 0; i < wowsBoardRoot.getData().size(); i++) {
                            if (i == 0 || i < 3) {
                                firstThreeList.add(wowsBoardRoot.getData().get(i));
                            } else {
                                list.add(wowsBoardRoot.getData().get(i));
                            }
                        }
                        setData();
                        if (list.isEmpty()) {

                        } else {

                            try {
                                adapterRooms = new AdapterRooms(list, requireContext(), BoardWeeklyFragment.this);
                                rv_daily.setAdapter(adapterRooms);
                            }catch (Exception e){

                            }

                        }
                    } else {

                    }
                } else {
                    Toast.makeText(requireContext(), "Technical  issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void callback(Datum datum) {

    }

    private void setData() {

        try {
            if (firstThreeList != null) {

                for (int i = 0; i < firstThreeList.size(); i++) {
                    if (i == 0) {
                        if (firstThreeList.get(i).getUserDetails().getProfileImage() != null) {
                            Glide.with(getContext()).load(firstThreeList.get(i).getUserDetails().getProfileImage().getImage()).error(R.drawable.demo_user_profile_img).into(profile_photo1);
                        }
                        dailyTop1DiamondTv.setText(firstThreeList.get(i).getDiamond());
                    } else if (i == 1) {
                        if (firstThreeList.get(i).getUserDetails().getProfileImage() != null) {
                            Glide.with(getContext()).load(firstThreeList.get(i).getUserDetails().getProfileImage().getImage()).error(R.drawable.demo_user_profile_img).into(profile_photo2);
                        }
                        dailyTop2DiamondTv.setText(firstThreeList.get(i).getDiamond());
                    } else if (i == 2) {
                        if (firstThreeList.get(i).getUserDetails().getProfileImage() != null) {
                            Glide.with(getContext()).load(firstThreeList.get(i).getUserDetails().getProfileImage().getImage()).error(R.drawable.demo_user_profile_img).into(profile_photo3);
                        }
                        dailyTop3DiamondTv.setText(firstThreeList.get(i).getDiamond());
                    }
                }
            } else {

            }
        }catch (Exception e){

        }
    }

}