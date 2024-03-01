package com.live.worldsocialintegrationapp.Fragments.SignUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneNumberLoginBinding;
import com.live.worldsocialintegrationapp.utils.App;


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

        binding.countrySelect.setText(App.getSharedpref().getString("countryName"));
        binding.phoneLoginNumberTV.setText(getArguments().getString("phone"));
        phoneNumber = getArguments().getString("countryCode")+getArguments().getString("phone");

    }

    private void onClick() {


        binding.LoginLinearlyut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.LoginLinearlyut.setClickable(false);
                if(getArguments()!= null){
                    Log.e("Issssssueeeeee","555555555zzz");
                    checkRegisteredNumber(getArguments().getString("phone"),getArguments().getString("countryCode"));
                }
                else{
                    Log.e("Issssssueeeeee","6666666zzz");
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
           if (binding == null) {
               Log.e("PhoneNumberLoginFragment", "Binding is null");
               return;
           }
                   new Mvvm().sendOtp(requireActivity(),countryCode+phone,"","","false","false","").observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {

                    if (sendOtpRoot !=null){
                        Log.e("Issssssueeeeee","111111111111");
                        Bundle bundle=new Bundle();
                            bundle.putString("phoneNo", phone);
                            bundle.putString("countryCode", countryCode);
                            if (sendOtpRoot.getSuccess().equalsIgnoreCase("1")) {
                                Log.e("Issssssueeeeee", "22222222");
                                Navigation.findNavController(binding.getRoot())
                                        .navigate(R.id.action_phoneNumberLoginFragment_to_passwordFragment, bundle);
                            } else {
                                Log.e("Issssssueeeeee", "33333333");
                                bundle.putBoolean("newUser", true);
                                Navigation.findNavController(binding.getRoot())
                                        .navigate(R.id.action_phoneNumberLoginFragment_to_otpFragment, bundle);
                            }
                    }
                    else {
                        Log.e("Issssssueeeeee","44444444444");
                        Toast.makeText(requireContext(), "Technical Issue...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
       }


}