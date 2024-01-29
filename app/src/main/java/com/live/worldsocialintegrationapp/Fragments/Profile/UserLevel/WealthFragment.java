package com.live.worldsocialintegrationapp.Fragments.Profile.UserLevel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.WealthCarRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.WealthColorIdLevelRVAdapter;
import com.live.worldsocialintegrationapp.Adapters.WealthFrameLevelRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.SendingLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetCarUserLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetColorByLevel;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetFrameByLevel;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentWealthBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class WealthFragment extends Fragment implements WealthFrameLevelRVAdapter.Callback,WealthColorIdLevelRVAdapter.Callback,WealthCarRVAdapter.Callback {

    FragmentWealthBinding binding;
    WealthCarRVAdapter wealthCarRVAdapter;
    WealthFrameLevelRVAdapter wealthFrameLevelRVAdapter;
    WealthColorIdLevelRVAdapter wealthColorIdLevelRVAdapter;
    List<GetCarUserLevel.Detail> carslist;
    List<GetFrameByLevel.Detail> frameslist;
    List<GetColorByLevel.Detail> colorslist;
    String sendingExpCurrent,sendingEndExp,sendingStart,requiredExperience;
    String sendingLvl,sendingColor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWealthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clicks(view);
        getCarLevelsApi();
        getFrameLevelApi();
        getColorIdLevelApi();

        String currentLevelText = App.getSharedpref().getString("sendingLvl");
        Integer currentLevelInteger = Integer.parseInt(currentLevelText);
        binding.currentLevelLowerBoundTv.setText("LV." + currentLevelInteger);
        binding.currentLevelUpperBoundTv.setText("LV." + (currentLevelInteger + 1));


        binding.WealthprogressBar.setMax(100);

        sendingExpCurrent= App.getSharedpref().getString("sendingExpCurrent");
        sendingEndExp = App.getSharedpref().getString("sendingEndExp");
        requiredExperience = App.getSharedpref().getString("requiredExperience");
        sendingLvl = App.getSharedpref().getString("sendingLvl");
        sendingColor = App.getSharedpref().getString("sendingColor");
        Log.d("apiiii",App.getSharedpref().getAll());

        sendingStart= App.getSharedpref().getString("SendStart");

        //Toast.makeText(requireContext(), "lvl :-"+App.getSharedpref().getString("sendingLvl"), Toast.LENGTH_SHORT).show();

            binding.relativeLayout.setVisibility(View.VISIBLE);
           // binding.WLevelTv.setText(App.getSharedpref().getString("mylevel"));
            if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))==0){
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.dark_wallpaper_img);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=1 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=10) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_1);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=11 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=20) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_2);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=21 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=30) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_3);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=31 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=40) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_4);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=41 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=50) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_5);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=51 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=60) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_6);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=61 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=70) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_7);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=71 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=80) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_8);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=81 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=90) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_9);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=91 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=100) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_10);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=101 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=110) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_11);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=111 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=120) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_12);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=121 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=130) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_13);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=131 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=140) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_14);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>=141 && Integer.parseInt(App.getSharedpref().getString("sendingLvl"))<=150) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_15);
            } else if (Integer.parseInt(App.getSharedpref().getString("sendingLvl"))>150) {
                binding.relativeLayout.setVisibility(View.VISIBLE);
                binding.relativeLayout.setBackgroundResource(R.drawable.levell_16);
            }


       //binding.relativeLayout.setBackgroundColor(Color.parseColor(sendingColor));


            String currentScore = sendingExpCurrent;

            Integer currentScoreInt = Integer.parseInt(currentScore);
            Integer requiredExperienceInt = Integer.parseInt(requiredExperience);

            String currentLevel = sendingLvl;
            Integer currentLevelInt = Integer.parseInt(currentLevel);

            Integer currentLevelLowerBound = Integer.parseInt(sendingStart);
            Integer currentLevelUpperBound = Integer.parseInt(sendingEndExp);
            try {
                //int currentExp = currentScoreInt - currentLevelLowerBound;

                int currentExp = currentScoreInt - requiredExperienceInt;
                //int currentLevelProgress = (currentExp *100) / currentLevelUpperBound;
                Log.d("keyyy", "currentlevel: " + currentLevel);
                Log.d("keyyy","level startings :- "+sendingStart);
                Log.d("keyyy","level ending :- "+sendingEndExp);
                Log.d("keyyy","keyy :- "+sendingExpCurrent);
                Log.d("keyyy","currentLevelUpperBound :- "+currentLevelUpperBound);
                Log.d("keyyy","currentLevelLowerBound :- "+currentLevelLowerBound);
                Log.d("keyyy","currentScoreInt :- "+currentScoreInt);
                Log.d("keyyy","requiredExperience :- "+ requiredExperience);
                Log.d("keyyy","total requiredExperience :- "+ (requiredExperienceInt + currentLevelUpperBound) );

                int upgradeLevelExp = currentLevelUpperBound - currentScoreInt;
                Log.d("keyyy","upgradeLevelExp :- "+upgradeLevelExp);
                //Log.d("keyyy","currentLevelProgress :- "+currentLevelProgress+"%");

               // binding.WealthprogressBar.setProgress(currentLevelProgress);

                //binding.expUpgradeTv.setText( "Need " + (currentLevelUpperBound-currentExp) +"/"+currentLevelUpperBound + " Exp to upgrade");
                //binding.expUpgradeTv.setText(currentExp +"/"+currentLevelUpperBound);

                //remove the following if condition when further xp calculation is added in database
                if(currentLevelInt < 150){
                    int currentLevelProgress = (currentExp *100) / currentLevelUpperBound;
                    binding.WealthprogressBar.setProgress(currentLevelProgress);
                    binding.expUpgradeTv.setText(currentExp +"/"+currentLevelUpperBound);
                }
                else {
                    binding.WealthprogressBar.setProgress(100);
                    binding.expUpgradeTv.setText(currentLevelLowerBound +"/"+currentLevelLowerBound);
                    binding.currentLevelLowerBoundTv.setText("LV." +(currentLevelInteger - 1));
                    binding.currentLevelUpperBoundTv.setText("LV." + App.getSharedpref().getString("receiveLevel"));
                }
            }catch (Exception e){

            }

//            Toast.makeText(requireContext(), "myExp progress: " + App.getSharedpref().getString("myExp"), Toast.LENGTH_SHORT).show();
//            binding.WealthprogressBar.setProgress(Integer.parseInt(App.getSharedpref().getString("myExp")));

            //binding.WealthprogressBar.getProgressDrawable().setLevel(Integer.parseInt(App.getSharedpref().getString("mylevel")));



    }
    private void clicks(View view) {
        binding.howlevelUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.howLevelUpFragment);
            }
        });
        binding.wealthMoreCarLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.wealthCarFragment);
            }
        });
        binding.wealthMoreFrameLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.wealthFrameFragment);
            }
        });
        binding.wealthMoreColorIdLinarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.howLevelUpFragment);
            }
        });
    }

    private void getCarLevelsApi() {
        new Mvvm().getCarLevel(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetCarUserLevel>() {
            @Override
            public void onChanged(GetCarUserLevel getUserLevelRoot) {
                if(getUserLevelRoot != null) {
                    Log.i("In getCar","in if");
                    if (getUserLevelRoot.getStatus() == 1) {
                        carslist = new ArrayList<>();
                        carslist = getUserLevelRoot.getDetails();
                        Toast.makeText(requireContext(), "Carslist ", Toast.LENGTH_SHORT).show();
                        wealthCarRVAdapter = new WealthCarRVAdapter(carslist, requireContext(), WealthFragment.this);
                        binding.wealthCarRV.setAdapter(wealthCarRVAdapter);
                        Log.i("In getCar","in if if");
                    }
                    else {
                        Log.i("In getCar","in if else");
                        Toast.makeText(requireContext(), "Technical issue 0 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.i("In getCar","in else");
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getFrameLevelApi() {

        if (requireActivity() != null) {

            new Mvvm().getFrameLevel(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetFrameByLevel>() {
                @Override
                public void onChanged(GetFrameByLevel getFrameByLevel) {

                    if (getFrameByLevel != null) {
                        if (getFrameByLevel.getStatus() == 1) {
//                    Toast.makeText(requireContext(), "1 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();

                            frameslist = new ArrayList<>();
                            frameslist = getFrameByLevel.getDetails();

                            if (requireContext() != null) {
                                wealthFrameLevelRVAdapter = new WealthFrameLevelRVAdapter(frameslist, requireContext(), WealthFragment.this);
                                binding.wealthFrameRV.setAdapter(wealthFrameLevelRVAdapter);
                            } else {

                            }

                        } else {
//                    Toast.makeText(requireContext(), "0 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    private void getColorIdLevelApi() {

        new Mvvm().getColorIdLevel(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetColorByLevel>() {
            @Override
            public void onChanged(GetColorByLevel getUserLevelRoot) {

                if(getUserLevelRoot != null) {

                    if (getUserLevelRoot.getStatus() == 1) {
//                    Toast.makeText(requireContext(), "1 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        colorslist = new ArrayList<>();
                        colorslist = getUserLevelRoot.getDetails();

                        wealthColorIdLevelRVAdapter = new WealthColorIdLevelRVAdapter(colorslist, requireContext(), WealthFragment.this);
                        binding.wealthColorIdRV.setAdapter(wealthColorIdLevelRVAdapter);

                    } else {
//                    Toast.makeText(requireContext(), "0 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }



    @Override
    public void colorIdDetail(GetColorByLevel.Detail detail) {

    }

    @Override
    public void frameDetail(GetFrameByLevel.Detail detail) {

    }

    @Override
    public void carDetail(GetCarUserLevel.Detail detail) {

    }
}