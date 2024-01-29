package com.live.worldsocialintegrationapp.Fragments.Home.Related.ChiragFragment;

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

import com.live.worldsocialintegrationapp.Adapters.DailyAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.DailyTask.GetDailyTaskRoot;
import com.live.worldsocialintegrationapp.ModelClasses.DailyTask.SetDailyTaskRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.List;


public class DailyTaskTab extends Fragment implements DailyAdapter.Callback{

    RecyclerView rv_Daily;
    List<GetDailyTaskRoot.Detail> list;
    DailyAdapter dailyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_task_tab, container, false);
    }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            rv_Daily = view.findViewById(R.id.recycler_Daily);
            getDailyTaskApi();
        }

    private void getDailyTaskApi() {

        new Mvvm().getDailyTask(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetDailyTaskRoot>() {
            @Override
            public void onChanged(GetDailyTaskRoot getDailyTaskRoot) {

                if(getDailyTaskRoot.getStatus()==1){
                    list = getDailyTaskRoot.getDetails();
                    dailyAdapter= new DailyAdapter(DailyTaskTab.this,list, getActivity().getApplicationContext());
                    rv_Daily.setAdapter(dailyAdapter);
//                    Toast.makeText(requireContext(), "1 "+getDailyTaskRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    setDailyTaskApi(1,1);
//                    Toast.makeText(requireContext(), "0 "+getDailyTaskRoot.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void callback(int value) {
        setDailyTaskApi(value,2);
    }

    private void setDailyTaskApi(int value,int i) {

        String day= String.valueOf(value);

        new Mvvm().setDailyTask(requireActivity(),AppConstants.USER_ID,day).observe(requireActivity(), new Observer<SetDailyTaskRoot>() {
            @Override
            public void onChanged(SetDailyTaskRoot setDailyTaskRoot) {

                if(setDailyTaskRoot.getStatus()==1){
//                    if (getActivity() != null){
//                        if (requireContext() != null){
//                            Toast.makeText(requireContext(), "1 "+setDailyTaskRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }


                    if(i==2){
                        dailyAdapter.notifyDataSetChanged();
                    }else {

                    }

                }else{
//                    if (requireContext() != null){
//                        Toast.makeText(requireContext(), "0 "+setDailyTaskRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });
    }


}