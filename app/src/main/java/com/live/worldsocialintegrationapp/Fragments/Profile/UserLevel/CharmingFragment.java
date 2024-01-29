package com.live.worldsocialintegrationapp.Fragments.Profile.UserLevel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.CharmingRVAdapter;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentCharmingBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.Objects;


public class CharmingFragment extends Fragment {

    com.live.worldsocialintegrationapp.databinding.FragmentCharmingBinding binding;

    String receivingExpCurrent,receivingEndExp,receivingStart;
    String receivingLvl,receivingColor,requiredExperience,receiveRequiredExperience;
    private Mvvm viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(Mvvm.class);
        binding = FragmentCharmingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hitApi();
        binding.charmingRV.setAdapter(new CharmingRVAdapter());
        // viewHolder.budgetProgress is a ProgressBar

// No matter what level I change, the drawable used is always the lowest one.
//        if (!App.getSharedpref().getString("recieveExp").equalsIgnoreCase("")) {
//            binding.progressBar.getProgressDrawable().setLevel(Integer.parseInt(App.getS
//            haredpref().getString("recieveExp")));
//        } else {
//            binding.progressBar.getProgressDrawable().setLevel(0);
//        }
//
//        binding.progressBar.setProgress(Integer.parseInt(App.getSharedpref().getString("recieveExp")));
//        binding.userExpLevel.setText("."+App.getSharedpref().getString("recieveExp"));


        receivingExpCurrent= App.getSharedpref().getString("receiveExpCurrent");
        receivingEndExp = App.getSharedpref().getString("receiveEnd");
        receivingLvl = App.getSharedpref().getString("receiveLevel");
        receivingStart = App.getSharedpref().getString("receiveStart");
        receivingColor = App.getSharedpref().getString("receivingColor");
        requiredExperience = App.getSharedpref().getString("requiredExperience");
        receiveRequiredExperience = App.getSharedpref().getString("receiveRequiredExperience");
//        binding.relativeLayout.setBackgroundColor(Color.parseColor(receivingColor));

        binding.currentLevelLowerBoundTv.setText("LV." + App.getSharedpref().getString("receiveLevel"));
        String currentLevelText = App.getSharedpref().getString("receiveLevel");
        int currentLevelInteger = Integer.parseInt(currentLevelText);
        binding.currentLevelUpperBoundTv.setText("LV." + (currentLevelInteger + 1));

        binding.progressBar.setMax(100);

        if(!App.getSharedpref().getString("receiveLevel").equalsIgnoreCase("")){

            String currentScore = App.getSharedpref().getString("receiveExpCurrent");
            int currentScoreInt = Integer.parseInt(currentScore);

            String currentLevel = App.getSharedpref().getString("receiveLevel");
            Integer currentLevelInt = Integer.parseInt(currentLevel);

            Integer currentLevelLowerBound = Integer.valueOf(receivingStart);
            int currentLevelUpperBound = Integer.parseInt(receivingEndExp);

//            Integer currentLevelProgress = (currentScoreInt - currentLevelLowerBound) * 100 / (currentLevelUpperBound - currentLevelLowerBound);
//            Integer upgradeLevelExp = (currentLevelUpperBound - currentScoreInt);
//
            Log.d("EXPERIENCE1", "myExp: " + currentScoreInt);
            Log.d("EXPERIENCE1", "receiveRequiredExperience: " + receiveRequiredExperience);
//            Log.d("EXPERIENCE", "myLevel: " + currentLevelInt);
//            Log.d("EXPERIENCE", "currentLevelLowerBound: " + currentLevelLowerBound);
//            Log.d("EXPERIENCE", "currentLevelUpperBound: " + currentLevelUpperBound);
//            Log.d("EXPERIENCE", "currentLevelProgress: " + currentLevelProgress);
//            Log.d("EXPERIENCE", "upgradeLevelExp: " + upgradeLevelExp);
//
//            binding.progressBar.setProgress(currentLevelProgress);
//            binding.textExp.setText("Need " + upgradeLevelExp + " Exp to upgrade");
            int receiveRequiredExperienceInt = Integer.parseInt(receiveRequiredExperience);
            int currentExp = currentScoreInt - receiveRequiredExperienceInt;


            //remove the following if condition when further xp calculation is added in database
            if(currentLevelInt < 150){

                int currentLevelProgress = (currentExp *100) / currentLevelUpperBound;
                binding.progressBar.setProgress(currentLevelProgress);
                binding.textExp.setText(currentExp +"/"+currentLevelUpperBound);
            }
           else {
                binding.progressBar.setProgress(100);
                binding.textExp.setText(currentLevelLowerBound +"/"+currentLevelLowerBound);
                binding.currentLevelLowerBoundTv.setText("LV." +(currentLevelInteger - 1));
                binding.currentLevelUpperBoundTv.setText("LV." + App.getSharedpref().getString("receiveLevel"));
            }

            //int upgradeLevelExp = currentLevelUpperBound - currentScoreInt;



            //binding.expUpgradeTv.setText( "Need " + (currentLevelUpperBound-currentExp) +"/"+currentLevelUpperBound + " Exp to upgrade");




//            Toast.makeText(requireContext(), "myExp progress: " + App.getSharedpref().getString("myExp"), Toast.LENGTH_SHORT).show();
//            binding.WealthprogressBar.setProgress(Integer.parseInt(App.getSharedpref().getString("myExp")));

            //binding.WealthprogressBar.getProgressDrawable().setLevel(Integer.parseInt(App.getSharedpref().getString("mylevel")));
        } else{
            Objects.requireNonNull(binding.progressBar.getProgressDrawable()).setLevel(0);
        }


    }

    private void hitApi() {


        viewModel.getSendingLevelData(requireActivity(), AppConstants.USER_ID).observe(requireActivity(),response ->{
            if (response !=null){
                if (response.getSuccess().equalsIgnoreCase("1")){
//                    binding.relativeLayout.setBackgroundColor(Color.parseColor(receivingColor));
                    Glide.with(requireContext()).load(response.getDetails().getReciveBgImage()).into(binding.relativeLayout);
                }
            }
        });
    }


}