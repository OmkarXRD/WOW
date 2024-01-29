package com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage.BillingRecord;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.worldsocialintegrationapp.Adapters.BillingSilverCoinsRVAdapter;
import com.live.worldsocialintegrationapp.R;


public class BillingSilverCoinsTab extends Fragment {

    private RecyclerView billingSilverCoinsRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_billing_silver_coins_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        billingSilverCoinsRV.setAdapter(new BillingSilverCoinsRVAdapter());
    }

    private void findIds(View view) {

        billingSilverCoinsRV=view.findViewById(R.id.billingSilverCoinsRV);
    }
}