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

  boolean phoneNumberExist = true;
  String phoneNumber;

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
        phoneNumber = getArguments().getString("countryCode")+getArguments().getString("phone");

    }

    private void onClick() {


        binding.LoginLinearlyut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.LoginLinearlyut.setClickable(false);
                if(getArguments()!= null){
                    checkRegisteredNumber(getArguments().getString("phone"),getArguments().getString("countryCode"));
                }
                else{
                    Toast.makeText(requireContext(), "Technical Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.backLoginOrRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.countrySelect.setOnClickListener(view ->
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_countrySelectFragment));

    }

    private void checkRegisteredNumber(String phone, String countryCode) {

//        Toast.makeText(requireContext(), "phone : "+phone, Toast.LENGTH_SHORT).show();



        checkNumberExist(phone,countryCode);

//        //check if number is allready registered or not
//        if(phoneNumberExist){
//
//            Navigation.findNavController( binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_passwordFragment,bundle);
//        }
//        else{
//            //if new number goes to otp screen
//            bundle.putBoolean("newUser",true);
//            Navigation.findNavController( binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_otpFragment,bundle);
//        }






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



       public void checkNumberExist(String phone, String countryCode){
                   new Mvvm().sendOtp(requireActivity(),countryCode+phone,"","","false","false","").observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {

                    if (sendOtpRoot !=null){
                        Bundle bundle = new Bundle();
                        bundle = getArguments();
                        assert bundle != null;
                        bundle.putString("phoneNo",phone);
                        bundle.putString("countryCode",countryCode);
                        if(sendOtpRoot.getSuccess().equalsIgnoreCase("1")){
                            Navigation.findNavController( binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_passwordFragment,bundle);
                        }
                        else{

                            bundle.putBoolean("newUser",true);
                            Navigation.findNavController( binding.getRoot()).navigate(R.id.action_phoneNumberLoginFragment_to_otpFragment,bundle);
                        }
                    }else {
                        Toast.makeText(requireContext(), "Technical Issue...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
       }


}