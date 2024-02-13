package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.IdBannedActivity;
import com.live.worldsocialintegrationapp.ModelClasses.Register.RegisterRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneCodeBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstant;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PhoneCodeFragment extends Fragment {

    FragmentPhoneCodeBinding binding;
    String phoneNumber,verificationCode,countryCode,upadtedPhoneNo;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    boolean isResend = false;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Long timeOutSeconds = 60L;

    boolean changePhoneNumber,resetPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneCodeBinding.inflate(inflater, container, false);

        onClick();
        onCreate();
        sendOtp(upadtedPhoneNo,isResend);
        timer();
        return binding.getRoot();
    }

    private void onCreate(){

        changePhoneNumber = getArguments().getBoolean("changePhoneNumber",false);
        resetPassword = getArguments().getBoolean("resetPassword",false);

        phoneNumber =  App.getSharedpref().getString("phone");
        if(Objects.equals(phoneNumber, "")){
            phoneNumber = getArguments().getString("phone");
        }

        //countryCode = getArguments().getString("countryCode");
        upadtedPhoneNo = "+"+phoneNumber;
        binding.phoneNumber.setText(upadtedPhoneNo);
        Log.i("OTPCheck","error "+upadtedPhoneNo);
    }
    private void onClick() {
        //Toast.makeText(requireContext(), "Verification code is sent to "+getArguments().getString("phoneNo"), Toast.LENGTH_SHORT).show();


        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showdialogbox();

                if(binding.otpText.getText().toString().trim().length() == 0){
                    Toast.makeText(requireContext(), "Please enter the OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    String enteredOtp = binding.otpText.getText().toString();
                    PhoneAuthCredential credential =  PhoneAuthProvider.getCredential(verificationCode,enteredOtp);
                    signIn(credential);
                }

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
                   sendOtp(upadtedPhoneNo,true);
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

    void sendOtp(String phoneNumber,boolean isResend){
        setInProgress(true);

        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(timeOutSeconds, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signIn(phoneAuthCredential);
                        setInProgress(false);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(requireContext(), "OTP Verification Failed", Toast.LENGTH_SHORT).show();

                        setInProgress(false);
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;
                        if (getContext() != null) {
                            // Access the context and proceed with your code
                            Toast.makeText(requireContext(), "OTP Sent Successfully", Toast.LENGTH_SHORT).show();
                            setInProgress(false);
                            // You can also access other UI elements or perform other actions here
                        }


                    }
                });
        if(isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        }else{
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }

    }

    void signIn(PhoneAuthCredential phoneAuthCredential){

        setInProgress(true);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    if(changePhoneNumber){
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("changePhoneNumber",true);
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_phoneCodeFragment_to_addPhoneNumber,bundle);
                        Log.i("OTPCheck","Change Phone NUmber");
                    }
                    else if(resetPassword){
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_phoneCodeFragment_to_addPasswordFragment);
                    }


                }
                else{
                    Toast.makeText(requireContext(), "OTP Verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    void setInProgress(boolean inProgress){
        if(inProgress){
            binding.nextButton.setVisibility(View.GONE);
        }
        else {
           binding.nextButton.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }

    }



}