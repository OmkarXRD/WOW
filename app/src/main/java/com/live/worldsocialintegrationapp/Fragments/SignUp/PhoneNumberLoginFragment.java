package com.live.worldsocialintegrationapp.Fragments.SignUp;

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

import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneNumberLoginBinding;


public class PhoneNumberLoginFragment extends Fragment {

  FragmentPhoneNumberLoginBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentPhoneNumberLoginBinding.inflate( inflater, container, false );
       return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClick();

        binding.phoneLoginNumberTV.setText(getArguments().getString("phone"));

    }

    private void onClick() {


        binding.LoginLinearlyut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.LoginLinearlyut.setClickable(false);
                if(getArguments()!= null){
                    sendOtpApi(getArguments().getString("phone"),getArguments().getString("countryCode"));
                }
                else{
//                    Toast.makeText(requireContext(), "arguments null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.backLoginOrRegister.setOnClickListener( view ->

                Navigation.findNavController( binding.getRoot() ).navigate(R.id.action_phoneNumberLoginFragment_to_enterPhoneFragment)

        );

        binding.countrySelect.setOnClickListener(view ->
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_countrySelectFragment));

    }

    private void sendOtpApi(String phone, String countryCode) {

//        Toast.makeText(requireContext(), "phone : "+phone, Toast.LENGTH_SHORT).show();
        Log.i("SendOTP","otp :"+countryCode+" " +phone  );
        Bundle bundle = new Bundle();
        bundle = getArguments();
        assert bundle != null;
        bundle.putString("phoneNo",getArguments().getString("phone"));
        bundle.putString("phoneOtp","1234");
        //Navigation.findNavController( binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_enterOtp,bundle);
        Navigation.findNavController( binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_otpFragment,bundle);
        //Api to get static otp
//        new Mvvm().sendOtp(requireActivity(),countryCode+phone).observe(requireActivity(), new Observer<SendOtpRoot>() {
//                @Override
//                public void onChanged(SendOtpRoot sendOtpRoot) {
//
//                    if (sendOtpRoot !=null){
//                        if(sendOtpRoot.getSuccess().equalsIgnoreCase("1")){
//
////                            Toast.makeText(requireContext(), "1 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            Toast.makeText(requireContext(), "Verification Code :  "+sendOtpRoot.getOtp(), Toast.LENGTH_SHORT).show();
//
//                            Bundle bundle=new Bundle();
//                            bundle = getArguments();
//                            bundle.putString("phoneOtp",sendOtpRoot.getOtp());
//
//                            Navigation.findNavController( binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_otpFragment,bundle);
//                        }
//                        else{
////                            Toast.makeText(requireContext(), "0 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        Toast.makeText(requireContext(), "Technical Issue...", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
       }



}