package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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

import com.live.worldsocialintegrationapp.Activites.SplashActivity;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.databinding.FragmentConnectedAccountsBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;


public class ConnectedAccountsFragment extends Fragment {

    FragmentConnectedAccountsBinding binding;
    private String phone, email, facebook,name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConnectedAccountsBinding.inflate(inflater, container, false);
        onClick();
        Log.i("ZZZZZZZZZZZZZZZZZZZZZZZZZZ","pass is "+ App.getSharedpref().getString("password"));
//
//        // This callback is only called when MyFragment is at least started
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//            }
//
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phone = App.getSharedpref().getString("phone");
        email = App.getSharedpref().getString("email");
        name = App.getSharedpref().getString("name");

        facebook = App.getSharedpref().getString("facebook");
        if (phone.isEmpty()) {
            binding.connectedPhoneTv.setText("Add");
            //binding.phoneArrow.setVisibility(View.GONE);
        }else{
            binding.connectedPhoneTv.setText(phone);
            //binding.phoneArrow.setVisibility(View.VISIBLE);
        }
        if (facebook.isEmpty()) {
            binding.connectFacebookTv.setText("Add");
            //binding.facebookArrow.setVisibility(View.GONE);
        }else{
            binding.connectFacebookTv.setText(facebook);
            //binding.facebookArrow.setVisibility(View.VISIBLE);
        }
        if (email.isEmpty()) {
            binding.connectGoogle.setText("Add");
            //binding.googleArrow.setVisibility(View.GONE);
        }else{
            binding.connectGoogle.setText(email);
            //binding.googleArrow.setVisibility(View.VISIBLE);
        }
    }

    private void onClick() {

        binding.phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.isEmpty()){
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_connectedAccountsFragment_to_addPhoneNumber);
                    //Toast.makeText(requireContext(), "You don't have any number linked", Toast.LENGTH_SHORT).show();
                }else{
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_connectedAccountsFragment_to_phoneVerificationFragment);
                }
            }
        });

        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                        //////////////////////////////////////////////////////
//                        App.getSharedpref().clearPreferences();
//                        AppConstants.USER_ID = "";
//                        startActivity(new Intent(requireContext(), SplashActivity.class));
                        //////////////////////////////////////////////////////
                        App.getSharedpref().clearPreferences();
                        AppConstants.USER_ID = "";
                        Intent intent = new Intent(requireContext(), SplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        requireActivity().finish();
                    } else {
//                    Toast.makeText(requireContext(), "0 "+sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


}