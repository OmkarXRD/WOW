package com.live.worldsocialintegrationapp.Fragments.ChangeNumber;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Binder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CalendarContract;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentAddPhonenumberBinding;
import com.live.worldsocialintegrationapp.utils.App;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class AddPhonenumberFragment extends Fragment {

    FragmentAddPhonenumberBinding binding;

    String phoneNumber,upadtedPhoneNo,verificationCode,countryCode;
    String numberWithCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    boolean isResend = false;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Long timeOutSeconds = 60L;
    private static final int SALT_LENGTH = 16;
    boolean changePhoneNumber;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddPhonenumberBinding.inflate(inflater, container, false);
        timer();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClicks();
        Bundle args = getArguments();
        if (args != null) {
            changePhoneNumber = args.getBoolean("changePhoneNumber", false);
        }
    }


    private String onCreate(String phoneNum, String countryCode){

        binding.phoneNumber.setText(phoneNum);
        upadtedPhoneNo = "+"+countryCode+""+phoneNumber;
        return upadtedPhoneNo;
    }

    private void onClicks() {

        binding.enterPhoneEdtx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    binding.verifyButton.setBackgroundColor(getResources().getColor(R.color.greeni));
                }else{

                }
            }
        });

        binding.otpTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.otpTimer.getText().toString().equals("Resend")) {
                    timer();
                    sendOtp(upadtedPhoneNo,true);
                }
            }
        });

        //verify if number is registered or not
        binding.verifyButton.setOnClickListener(view -> {

            countryCode = binding.ccp.getSelectedCountryCode();
            String country = binding.ccp.getSelectedCountryName().toString();
            App.getSharedpref().saveString("countryCode",countryCode);

            ///OLD implementation #007
//            if(binding.enterPhoneEdtx.getText().toString().trim().length() == 0){
//                Toast.makeText(requireContext(), "Please enter the Phone no. ", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Bundle bundle=new Bundle();
//                bundle.putString("phone",binding.enterPhoneEdtx.getText().toString());
//                bundle.putString("countryCode",countryCode);
//                bundle.putString("country",country);
//                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addPhoneNumber_to_phoneCode,bundle);
//            }
            Log.i("ADDPHONEEEE","000000");
            ///NEW implementation #007
            if(binding.enterPhoneEdtx.getText().toString().trim().length() == 0){
                Toast.makeText(requireContext(), "Please enter the Phone no. ", Toast.LENGTH_SHORT).show();
            }
            else{
                phoneNumber = binding.enterPhoneEdtx.getText().toString();
                numberWithCode =  onCreate(phoneNumber,countryCode);
                Log.i("ADDPHONEEEE","1111");
                checkNumberExist(phoneNumber);

               }


        });

        binding.submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.enterPhoneEdtx.getText().toString().trim().length() == 0){
                    Log.i("ADDPHONEEEE","on submit");
                    Toast.makeText(requireContext(), "Please enter the OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.i("ADDPHONEEEE","on submit 1");
                    String enteredOtp = binding.otpText.getText().toString();
                    PhoneAuthCredential credential =  PhoneAuthProvider.getCredential(verificationCode,enteredOtp);
                    signIn(credential);
                }

            }
        });
        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        binding.submitPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.enterPhoneEdtx.getText().toString().trim().length() == 0){
                    Toast.makeText(requireContext(), "Please enter the Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    App.getSharedpref().saveString("password",binding.enterPasswordTxt.getText().toString());
                    updateNumber(countryCode+phoneNumber);

                }

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
                        Log.i("ADDPHONEEEE","1.1111");
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(requireContext(), "OTP Verification Failed", Toast.LENGTH_SHORT).show();
                        Log.i("OTPCheck","error "+ e);
                        setInProgress(false);
                        Log.i("ADDPHONEEEE","1.222222");
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;
                        Toast.makeText(requireContext(), "OTP Sent Successfully", Toast.LENGTH_SHORT).show();
                        Log.i("OTPCheck","error oncodeSent 1"+ s);
                        Log.i("OTPCheck","error oncodeSent 2"+ forceResendingToken);
                        setInProgress(false);
                        binding.verifyButton.setVisibility(View.GONE);
                        binding.countrycodeNumber.setVisibility(View.GONE);
                        binding.otpInput.setVisibility(View.VISIBLE);
                        binding.submitOtp.setVisibility(View.VISIBLE);
                        binding.infoTextView.setText("A verification code has been sent to you");
                        binding.phoneNumber.setVisibility(View.VISIBLE);
                        binding.phoneNumber.setText(upadtedPhoneNo);
                        Log.i("ADDPHONEEEE","1.33333");



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
//                        Toast.makeText(requireContext(), "Number Sucessfully Updated", Toast.LENGTH_SHORT).show();
//                        App.getSharedpref().saveString("phone",countryCode+""+phoneNumber);

                        Log.i("ADDPHONEEEE","on submit change phone");
                        App.getSharedpref().saveString("phone",countryCode+""+phoneNumber);
                        binding.infoTextView.setText("Enter Password");
                        binding.otpInput.setVisibility(View.GONE);
                        binding.submitOtp.setVisibility(View.GONE);
                        binding.submitPass.setVisibility(View.VISIBLE);
                        binding.passwordInput.setVisibility(View.VISIBLE);
                    }
                    else{
                        App.getSharedpref().saveString("phone",countryCode+""+phoneNumber);
                        binding.infoTextView.setText("Enter Password");
                        binding.otpInput.setVisibility(View.GONE);
                        binding.submitOtp.setVisibility(View.GONE);
                        binding.submitPass.setVisibility(View.VISIBLE);
                        binding.passwordInput.setVisibility(View.VISIBLE);
                        Log.i("ADDPHONEEEE","on submit change phone else " + countryCode+""+phoneNumber);
                    }


                }
                else{
                    Toast.makeText(requireContext(), "OTP Verification Failed : Wrong otp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void setInProgress(boolean inProgress){
        if(inProgress){
            binding.verifyBtnText.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.verifyBtnText.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }

    }

    public void checkNumberExist(String phone){


        new Mvvm().sendOtp(requireActivity(),countryCode+phone,"","","false","false","").observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                if (sendOtpRoot !=null){
//                    Bundle bundle = new Bundle();
//                    bundle = getArguments();
//                    assert bundle != null;
//                    bundle.putString("phoneNo",phone);
//                    bundle.putString("countryCode",countryCode);
                    if(sendOtpRoot.getSuccess().equalsIgnoreCase("1")){
                        Log.i("ADDPHONEEEE","22222");
                        Toast.makeText(requireContext(), "Number is already registered, User another number", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i("ADDPHONEEEE","33333");
                        sendOtp(numberWithCode,false);

                    }
                }else {
                    Log.i("ADDPHONEEEE","4444");
                    Toast.makeText(requireContext(), "Technical Issue...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void updateNumber(String phone){

        String password = binding.enterPasswordTxt.getText().toString();
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        String userName = App.getSharedpref().getString("username");
        new Mvvm().sendOtp(requireActivity(),phone,hashedPassword,salt,"true","true",userName).observe(requireActivity(), new Observer<SendOtpRoot>() {
            @Override
            public void onChanged(SendOtpRoot sendOtpRoot) {

                if (sendOtpRoot !=null){

                    if(sendOtpRoot.getSuccess().equalsIgnoreCase("1")){
                        Toast.makeText(requireContext(), "Number bound successful", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }
                    else{
                        Toast.makeText(requireContext(), "Failed to update Number", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(requireContext(), "Technical Issue...", Toast.LENGTH_SHORT).show();
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