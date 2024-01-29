package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments.Trophy.Diamond;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.Adapters.AdapterDimond;
import com.live.worldsocialintegrationapp.R;


public class Fragment_Diamod_Daily extends Fragment {


    RecyclerView rv_dimond;

    public Fragment_Diamod_Daily() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__diamod__daily, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_dimond = view.findViewById(R.id.recycler_Diamond);

        rv_dimond.setAdapter(new AdapterDimond());
    }
}