package com.live.worldsocialintegrationapp.Fragments.Profile.Badges;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.live.worldsocialintegrationapp.Adapters.InsideBadgeRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserTalentLevelRoot;
import com.live.worldsocialintegrationapp.R;

import java.util.List;


public class BadgeDetailFragment extends Fragment {

    public static List<GetUserTalentLevelRoot.Child> list;
    private ImageView bageImg;
    private RecyclerView insideBadgeRv;
    public static GetUserTalentLevelRoot.Child childDetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_badge_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        findIds(view);
//        list.add(childDetail);
        InsideBadgeRVAdapter insideBadgeRVAdapter = new InsideBadgeRVAdapter(list,requireContext());
        insideBadgeRv.setAdapter(insideBadgeRVAdapter);



    }

    private void findIds(View view) {
        bageImg = view.findViewById(R.id.bageImg);
        insideBadgeRv = view.findViewById(R.id.insideBadgeRv);

    }

//    private void getTopGifterBadges() {
//
//        new Mvvm().getTopGifterBadge(requireActivity()).observe(requireActivity(), new Observer<GetTopGifterRoot>() {
//            @Override
//            public void onChanged(GetTopGifterRoot getTopGifterRoot) {
//
//                if (getTopGifterRoot.getStatus() == 1) {
//                    list = new ArrayList<>();
//                    list = getTopGifterRoot.getDetails();
//                    Toast.makeText(requireContext(), "1 " + getTopGifterRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(requireContext(), "1 " + getTopGifterRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}