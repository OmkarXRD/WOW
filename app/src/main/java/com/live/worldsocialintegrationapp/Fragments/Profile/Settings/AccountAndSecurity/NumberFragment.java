package com.live.worldsocialintegrationapp.Fragments.Profile.Settings.AccountAndSecurity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class NumberFragment extends Fragment {


    private TextView numberTv;
    private String phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        numberTv = view.findViewById(R.id.numberTv);
        phone = App.getSharedpref().getString("phone");

        numberTv.setText(phone);

        if (phone.isEmpty()) {
            numberTv.setText("+91 XXXXXXXXX");
        } else {
//            String firstThree = String.valueOf(phone.charAt(0) + phone.charAt(1) + phone.charAt(2));
            numberTv.setText(phone);
        }

        clicks(view);
    }

    private void clicks(View view) {

        view.findViewById(R.id.resetPasswordRL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.resetPassword);
            }
        });

        view.findViewById(R.id.numberChangePhoneRL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VerifyPhoneNumberApi();
            }
        });

        view.findViewById(R.id.numberBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void VerifyPhoneNumberApi() {

        String phone = App.getSharedpref().getString("phone");
        String id = App.getSharedpref().getString("userId");
        if (id == null) {
//            Toast.makeText(requireContext(), "phone is null", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(requireContext(), "id : " + id, Toast.LENGTH_SHORT).show();
            new Mvvm().verifyPhoneNumber(requireActivity(), id, "", "").observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {

                    if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
//                        Toast.makeText(requireContext(), "1 " + sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        //bundle.putString("verifyOTP", sendOtpRoot.getOtp());
                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.changePhoneNumberFragment, bundle);
                    } else {
//                        Toast.makeText(requireContext(), "0 " + sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}