package com.live.worldsocialintegrationapp.Fragments.Profile.RechargePackage.BillingRecord;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.BillingRecordGoldCoinsRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseHistory.PurchaseHistoryRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseHistory.PurchaseLuckyIdHistory;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.model.BillingRecordModelClass;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class BillingCoinsTab extends Fragment {

    private RecyclerView billingGoldCoinsRV;
    List<BillingRecordModelClass.Detail> purchaseLuckyIdHistoriesList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_billing_coins_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        getPurchaseHistoryApi();
    }

    private void findIds(View view) {

        billingGoldCoinsRV = view.findViewById(R.id.billingGoldCoinsRV);
    }

    private void getPurchaseHistoryApi(){

        Toast.makeText(requireContext(), "userId"+AppConstants.USER_ID, Toast.LENGTH_SHORT).show();

        new Mvvm().getUserBillingRecordViewModel(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<BillingRecordModelClass>() {
            @Override
            public void onChanged(BillingRecordModelClass billingRecordModelClass) {
                if (billingRecordModelClass.getSuccess().equalsIgnoreCase("1")){
                    purchaseLuckyIdHistoriesList = new ArrayList<>();
                    purchaseLuckyIdHistoriesList = billingRecordModelClass.getDetails();
                    if (isAdded() && getContext()!=null){
                        BillingRecordGoldCoinsRVAdapter billingRecordGoldCoinsRVAdapter = new BillingRecordGoldCoinsRVAdapter(purchaseLuckyIdHistoriesList,requireContext());
                        billingGoldCoinsRV.setAdapter(billingRecordGoldCoinsRVAdapter);
                    }
                }
            }
        });
//        new Mvvm().getPurchaseHistory(requireActivity(), App.getSharedpref().getString("userId")).observe(requireActivity(), new Observer<PurchaseHistoryRoot>() {
//            @Override
//            public void onChanged(PurchaseHistoryRoot purchaseHistoryRoot) {
//
//                if(purchaseHistoryRoot.getSuccess().equalsIgnoreCase("1")){
//
//                    purchaseLuckyIdHistoriesList = new ArrayList<>();
//                    purchaseLuckyIdHistoriesList = purchaseHistoryRoot.getDetails().getPurchase_luckyId_history();
//                    BillingRecordGoldCoinsRVAdapter billingRecordGoldCoinsRVAdapter = new BillingRecordGoldCoinsRVAdapter(purchaseLuckyIdHistoriesList,requireContext());
//
//                    billingGoldCoinsRV.setAdapter(billingRecordGoldCoinsRVAdapter);
////                    Toast.makeText(requireContext(), "1 "+purchaseHistoryRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                }else{
////                    Toast.makeText(requireContext(), "0 "+purchaseHistoryRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}