package com.live.worldsocialintegrationapp.Fragments.ChangeNumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.MainActivity;
import com.live.worldsocialintegrationapp.Activites.SplashActivity;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentAddPasswordBinding;
import com.live.worldsocialintegrationapp.utils.App;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class AddPasswordFragment extends Fragment {

    FragmentAddPasswordBinding binding;
    String phoneNumber,countryCode;
    private static final int SALT_LENGTH = 16;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddPasswordBinding.inflate(inflater, container, false);
        phoneNumber = App.getSharedpref().getString("phone");
        //countryCode = App.getSharedpref().getString("countryCode");
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClicks();

        binding.phoneNumber.setText("+"+phoneNumber);
    }


    private void onClicks() {

//        binding.saveButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    binding.saveButton.setBackgroundColor(getResources().getColor(R.color.greeni));
//                }else{
//
//                }
//            }
//        });
        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
           }

        });

        binding.passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                boolean isVisible =binding.enterPasswordTxt.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                binding.enterPasswordTxt.setInputType(isVisible ?
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                // Move cursor to the end of the text
                binding.enterPasswordTxt.setSelection(binding.enterPasswordTxt.getText().length());

                // Change the icon based on visibility state
                binding.passwordVisibilityToggle.setImageResource(isVisible ?
                        R.drawable.password_visible : R.drawable.password_hide);
            }
        });

        binding.updatepassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.enterPasswordTxt.getText().toString().trim().length() == 0){
                    Toast.makeText(requireContext(), "Please, enter the password", Toast.LENGTH_SHORT).show();
                }
                else{

                    String password = binding.enterPasswordTxt.getText().toString();
                    String salt = generateSalt();
                    String hashedPassword = hashPassword(password, salt);
                    //App.getSharedpref().saveString("password",password);
                    new Mvvm().sendOtp(requireActivity(),phoneNumber,hashedPassword,salt,"true","false","").observe(requireActivity(), new Observer<SendOtpRoot>() {
                        @Override
                        public void onChanged(SendOtpRoot sendOtpRoot) {

                            if (sendOtpRoot !=null){

                                if(sendOtpRoot.getSuccess().equalsIgnoreCase("1")){
                                    Toast.makeText(requireContext(), "Password Updated", Toast.LENGTH_SHORT).show();
                                    getActivity().onBackPressed();
                                    Log.i("CheckResetPass",sendOtpRoot.getMessage());
                                }
                                else{
                                    Toast.makeText(requireContext(), "Password Updated Failed", Toast.LENGTH_SHORT).show();
                                    Log.i("CheckResetPass",sendOtpRoot.getMessage());
                                }
                            }else {
                                Toast.makeText(requireContext(), "Technical Issue...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

            }
        });

    }
    public static String hashPassword(String password, String salt) {
        String passwordWithSalt = password + salt;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordWithSalt.getBytes());
            return bytesToHex(hash); // Convert byte array to hexadecimal string
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        random.nextBytes(saltBytes);
        return bytesToHex(saltBytes);
    }


}