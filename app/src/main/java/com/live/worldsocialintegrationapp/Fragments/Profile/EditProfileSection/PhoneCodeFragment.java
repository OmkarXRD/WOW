package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneCodeBinding;
import com.live.worldsocialintegrationapp.utils.App;

public class PhoneCodeFragment extends Fragment {

    FragmentPhoneCodeBinding binding;
    String phoneNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneCodeBinding.inflate(inflater, container, false);

        onClick();
        onCreate();
        timer();
        return binding.getRoot();
    }

    private void onCreate(){
        phoneNumber =  App.getSharedpref().getString("phone");
        binding.phoneNumber.setText(phoneNumber);
    }
    private void onClick() {
        //Toast.makeText(requireContext(), "Verification code is sent to "+getArguments().getString("phoneNo"), Toast.LENGTH_SHORT).show();


        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogbox();
            }
        });
        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.otpTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.otpTimer.getText().toString().equals("Resend")) {
                    timer();
                    Log.i("OTP","resend pressed");
                    //call function to send the otp again
                }
            }
        });
    }
    private void timer() {

        new CountDownTimer(59000, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                binding.otpTimer.setText("00:" + millisUntilFinished / 1000);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                binding.otpTimer.setText(R.string.resend);
            }
        }.start();
    }
    private void showdialogbox() {

        Dialog dialogChooseAlbum = new Dialog(requireContext());

        dialogChooseAlbum.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogChooseAlbum.setContentView(R.layout.show_later_dialog);

        dialogChooseAlbum.setCanceledOnTouchOutside(true);

        dialogChooseAlbum.setCancelable(true);

        dialogChooseAlbum.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        dialogChooseAlbum.getWindow().setGravity(Gravity.BOTTOM);

        dialogChooseAlbum.show();


    }





}