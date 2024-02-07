package com.live.worldsocialintegrationapp.Fragments.Profile.Settings.AccountAndSecurity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.SplashActivity;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class AccountAndSecurityFragment extends Fragment {
    private String phone, email, facebook;
    private TextView numberBoundTv, googleBoundTv, facebookBoundTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_and_security, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        findIds(view);
        clicks(view);

        phone = App.getSharedpref().getString("phone");
        email = App.getSharedpref().getString("email");
        facebook = App.getSharedpref().getString("facebook");

        if (phone.isEmpty()) {
            //numberBoundTv.setText("Unbound");
            numberBoundTv.setText("Add");
        } else {
            numberBoundTv.setText("Bound");
            numberBoundTv.setTextColor(getResources().getColor(R.color.green));
        }
        if (facebook.isEmpty()) {
            //facebookBoundTv.setText("Unbound");
            facebookBoundTv.setText("Add");
        } else {
            facebookBoundTv.setText("Bound");
            facebookBoundTv.setTextColor(getResources().getColor(R.color.green));
        }
        if (email.isEmpty()) {
            //googleBoundTv.setText("Unbound");
            googleBoundTv.setText("Add");
        } else {

            googleBoundTv.setText("Bound");
            googleBoundTv.setTextColor(getResources().getColor(R.color.green));
        }
    }

    private void findIds(View view) {
        numberBoundTv = view.findViewById(R.id.numberBondTv);
        googleBoundTv = view.findViewById(R.id.gamilBondTv);
        facebookBoundTv = view.findViewById(R.id.facebookBondTv);
    }

    private void clicks(View view) {

        view.findViewById(R.id.acBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        view.findViewById(R.id.acNumberRL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.numberFragment);
            }
        });


        view.findViewById(R.id.acAndSecurityDeleteAcRL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteAccountDialogBox();
            }
        });

    }

    private void deleteAccountDialogBox() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setMessage("Are you sure you want to delete your account ?");
        builder.setTitle("Delete Account");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            deleteAccountApi();
            dialog.dismiss();

        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteAccountApi() {
        String id = App.getSharedpref().getString("userId");

        if (id.length() == 0) {
            Toast.makeText(requireContext(), "user id null", Toast.LENGTH_SHORT).show();
        } else {

            new Mvvm().removeUserAccount(requireActivity(), id).observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {

                    if (sendOtpRoot.getSuccess().equalsIgnoreCase("1")) {
                        //                    Toast.makeText(requireContext(), "1 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        App.getSharedpref().clearPreferences();
                        AppConstants.USER_ID = "";
                        Intent intent = new Intent(requireContext(), SplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        requireActivity().finish();

                        //startActivity(new Intent(requireContext(), SplashActivity.class));
                    } else {
//                    Toast.makeText(requireContext(), "0 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}