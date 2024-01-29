package com.live.worldsocialintegrationapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.live.worldsocialintegrationapp.R;


public class LuckyBagRulesFragment extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lucky_bag_rules, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.luckyRulesBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LuckyBagRulesFragment.this.dismiss();
            }
        });

    }
}