package com.live.worldsocialintegrationapp.Fragments.SignUp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.messaging.FirebaseMessaging;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.IdBannedActivity;
import com.live.worldsocialintegrationapp.ModelClasses.Register.RegisterRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentPasswordscreenBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstant;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PasswordFragment extends Fragment {

    FragmentPasswordscreenBinding binding;
    String continentName,countryName, phoneNo,countryCode;

    String RegId="";
    PhoneAuthProvider.ForceResendingToken resendingToken;


    private static final int SALT_LENGTH = 16;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPasswordscreenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(requireActivity());
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("OTP", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
//                   token = task.getResult();
                    RegId = task.getResult();
                    Log.d("OTP","REGID "+RegId);

                });


        countryCode = getArguments().getString("countryCode");
        countryName = App.getSharedpref().getString("countryName");
        continentName=App.getSharedpref().getString("continentName");
        phoneNo = "+"+countryCode+""+getArguments().getString("phoneNo");
        onclicks();
        binding.phoneNumber.setText(phoneNo);

    }



    private void onclicks() {

        binding.otpBackImg.setOnClickListener(view -> {
            getActivity().onBackPressed();
        });



        //userd to check otp from api
        //binding.btnVerificationCode.setOnClickListener(this::registerUserApi);

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

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String password = binding.enterPasswordTxt.getText().toString();
              String salt = generateSalt();
              String hashedPassword = hashPassword(password, salt);
              Log.i("Issssssueeeeee","entered "+hashedPassword);

              //IF password is correct user will be redirected to HOME SCREEN
                new Mvvm().registerUser(requireActivity(),countryCode+getArguments().getString("phoneNo"),hashedPassword,salt,countryName,continentName,"false",RegId).observe(requireActivity(), new Observer<RegisterRoot>() {
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
                                App.getSharedpref().saveString("email",registerRoot.getDetails().getEmail());
                                App.getSharedpref().saveString("facebook",registerRoot.getDetails().getFacebookUserName());

                                AppConstants.USER_ID = registerRoot.getDetails().getId();

                                String storedPass = registerRoot.getDetails().getPassword();
                                String storedSalt = registerRoot.getDetails().getSalt();
                                Log.i("Issssssueeeeee","stored " +registerRoot.getDetails().getPassword());
                                if (String.valueOf(registerRoot.getDetails().getIdBannedStatus()).equals("true")){
                                    Toast.makeText(requireContext(), "ID banned", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(requireContext(), IdBannedActivity.class));
                                }else {
                                    String hashedEnteredPassword = hashPassword(password, storedSalt);
                                    if(Objects.equals(storedPass, hashedEnteredPassword)){
                                        Log.i("Issssssueeeeee", String.valueOf(Objects.equals(storedPass, hashedEnteredPassword)));
                                        startActivity(new Intent(requireContext(),HomeActivity.class));
                                    }
                                    else{
                                        Toast.makeText(requireContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
//                            Toast.makeText(requireContext(), "0 " + registerRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


            }
        });

        binding.forgotPassText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle = getArguments();
                assert bundle != null;
                bundle.putString("phoneNo",getArguments().getString("phoneNo"));
                bundle.putString("countryCode",countryCode);
                bundle.putBoolean("forgotPassword",true);
                Navigation.findNavController( binding.getRoot()).navigate(R.id.action_passwordFragment_to_otpFragment,bundle);
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