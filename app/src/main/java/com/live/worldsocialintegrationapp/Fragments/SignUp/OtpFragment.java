package com.live.worldsocialintegrationapp.Fragments.SignUp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.IdBannedActivity;
import com.live.worldsocialintegrationapp.ModelClasses.Register.RegisterRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentOtpBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstant;
import com.live.worldsocialintegrationapp.utils.AppConstants;

public class OtpFragment extends Fragment {

    FragmentOtpBinding binding;
    String RegId="";
    String continentName,countryName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOtpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(requireActivity());
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("OP", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
//                   token = task.getResult();
                    RegId = task.getResult();
                    Log.d("OTP","REGID "+RegId);

                });

        onclicks();
        timer();
    }

    private void timer() {

        new CountDownTimer(59000, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setText("00:" + millisUntilFinished / 1000);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                binding.timer.setText(R.string.resend);
            }
        }.start();
    }

    private void onclicks() {

        binding.otpBackImg.setOnClickListener(view -> {
            getActivity().onBackPressed();
        });

        binding.timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.timer.getText().toString().equals("Resend")) {
                timer();
                }
            }
        });

        binding.btnVerificationCode.setOnClickListener(this::registerUserApi);
    }

    private void registerUserApi(View view) {

        String otp="";
        String phone="";
        String country="";

        if (getArguments() != null) {

            Bundle bundle = getArguments();
            otp = bundle.getString("phoneOtp");
            phone = bundle.getString("phone");
            country = bundle.getString("countryCode");
        }
        else {
//            Toast.makeText(requireContext(), "arguments null", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(requireContext(), "regiser otp  : " + otp, Toast.LENGTH_SHORT).show();
//        Toast.makeText(requireContext(), "register phone : " + phone, Toast.LENGTH_SHORT).show();

        if (App.getSharedpref().getString("continentName")==null){
            //Toast.makeText(requireContext(), "continent Name null", Toast.LENGTH_SHORT).show();
        }else if (App.getSharedpref().getString("countryName")==null){
            //Toast.makeText(requireContext(), "country Name null", Toast.LENGTH_SHORT).show();
        }else{
            continentName=App.getSharedpref().getString("continentName");
            countryName = App.getSharedpref().getString("countryName");
            if(otp.equalsIgnoreCase(binding.otpView.getOTP())){
                new Mvvm().registerUser(requireActivity(),country+phone,otp,App.getSharedpref().getString("countryName"),continentName,RegId).observe(requireActivity(), new Observer<RegisterRoot>() {
                    @Override
                    public void onChanged(RegisterRoot registerRoot) {
                        if (registerRoot.getSuccess().equalsIgnoreCase("1")) {
                        //    Toast.makeText(requireContext(), "getIdBannedStatus"+registerRoot.getDetails().getIdBannedStatus().toString(), Toast.LENGTH_SHORT).show();
                       //     Toast.makeText(requireContext(), "id : " + registerRoot.getDetails().getId(), Toast.LENGTH_SHORT).show();
                            App.getSharedpref().saveString(AppConstant.SESSION, "1");
                            App.getSharedpref().saveModel("RegisterRoot",registerRoot.getDetails());
                            //Toast.makeText(requireContext(), "image :-", Toast.LENGTH_SHORT).show();
                            App.getSharedpref().saveString("username",registerRoot.getDetails().getUsername());
                            App.getSharedpref().saveString("image",registerRoot.getDetails().getImage());
                            App.getSharedpref().saveString("name",registerRoot.getDetails().getName());
                            App.getSharedpref().saveString("country",registerRoot.getDetails().getCountry());
                            App.getSharedpref().saveString("phone",registerRoot.getDetails().getPhone());
                            App.getSharedpref().saveString("userId",registerRoot.getDetails().getId());
                            App.getSharedpref().saveString("dob",registerRoot.getDetails().getDob());
                            App.getSharedpref().saveString("gender",registerRoot.getDetails().getGender());
                            App.getSharedpref().saveString("vipLevel",registerRoot.getDetails().getVipLevel());
                            App.getSharedpref().saveString("mylevel",registerRoot.getDetails().getMyLevel());
                            App.getSharedpref().saveString("country_showUnshow",registerRoot.getDetails().getCountryShowUnshow());
                            App.getSharedpref().saveString("familyId",registerRoot.getDetails().getFamilyId());
                            App.getSharedpref().saveString("eventId",registerRoot.getDetails().getEventId());
                            AppConstants.USER_ID = registerRoot.getDetails().getId();
//                            Log.i("IDDDDDD",registerRoot.getDetails().getId());
//                            Log.i("IDDDDDD",registerRoot.getDetails().getUsername());

                            if (String.valueOf(registerRoot.getDetails().getIdBannedStatus()).equals("true")){
                              //  Toast.makeText(requireContext(), "id banned", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(requireContext(), IdBannedActivity.class));
                            }else {
                                startActivity(new Intent(requireContext(),HomeActivity.class));
                            }
                        } else {
//                            Toast.makeText(requireContext(), "0 " + registerRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                Toast.makeText(requireContext(), "Wrong OTP", Toast.LENGTH_SHORT).show();
            }
        }
    }




}