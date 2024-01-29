package com.live.worldsocialintegrationapp.Fragments.Profile.Settings.Privacy;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.BlockUser.RootBlocked;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.Details;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.GetUserDetailRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class PrivacyFragment extends Fragment {


    private Switch hideCountrySwitch;
    private Details details;
    public String hideStatus = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CommonUtils.disableBottomNavigation(requireActivity());
        findIds(view);
        clicks(view);
        hideCountryStatus(view);
    }

    private void hideCountryStatus(View view) {

        new Mvvm().getUserDetail(requireActivity(), AppConstants.USER_ID, AppConstants.USER_ID,AppConstants.USER_ID).observe(requireActivity(),
                new Observer<GetUserDetailRoot>() {
                    @Override
                    public void onChanged(GetUserDetailRoot getUserDetailRoot) {

                        if (getUserDetailRoot.getStatus().equalsIgnoreCase("1")) {
                            details = new Details();
                            hideStatus   = getUserDetailRoot.getDetails().getCountry_showUnshow();


                            //        Toast.makeText(requireContext(), "0 " + getUserDetailRoot.getMessage(), Toast.LENGTH_SHORT).show();


                        } else {
//                            Toast.makeText(requireContext(), "0 " + getUserDetailRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void findIds(View view) {
        hideCountrySwitch=view.findViewById(R.id.hideCountrySwitch);
    }

    private void clicks(View view) {

        view.findViewById(R.id.privacyBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        hideCountrySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean True) {

                if(!hideStatus.equalsIgnoreCase("0"))
                {
                    hideCountrySwitch.setChecked(true);
                }else {
                    Toast.makeText(requireActivity(), ""+details.getCountry_showUnshow(), Toast.LENGTH_SHORT).show();
                }
                if(True){
                    DialogBox();
                    hideCountry();
//                    Toast.makeText(requireContext(), "Checked", Toast.LENGTH_SHORT).show();
                }else{
                    hideCountry();
//                    Toast.makeText(requireContext(), "Not checked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void hideCountry() {

        new Mvvm().HidCountry(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<RootBlocked>() {
            @Override
            public void onChanged(RootBlocked rootBlocked) {
                if (rootBlocked != null) {

                    if (rootBlocked.getSuccess().equalsIgnoreCase("1")) {
//                        Toast.makeText(requireActivity(), ""+rootBlocked.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
//                        Toast.makeText(requireActivity(), ""+rootBlocked.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
//                    Toast.makeText(requireActivity(), "Root is null", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void DialogBox() {

        Dialog dialog_share = new Dialog( getContext() );
        dialog_share.setContentView( R.layout.hide_country_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable( new ColorDrawable( Color.WHITE ));
        dialog_share.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside( true );
        Window window = dialog_share.getWindow();
        window.setGravity( Gravity.CENTER );
        dialog_share.show();

        AppCompatButton privacyDialogCancelBtn= dialog_share.findViewById(R.id.privacyDialogCancelBtn);
        privacyDialogCancelBtn.setOnClickListener(view -> {
            dialog_share.dismiss();
        });

        AppCompatButton privacyDialogActivateBtn= dialog_share.findViewById(R.id.privacyDialogActivateBtn);

        privacyDialogActivateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.action_privacyFragment_to_VIPCenterFragment);
                dialog_share.dismiss();
            }
        });
    }

}