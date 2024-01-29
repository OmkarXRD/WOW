package com.live.worldsocialintegrationapp.Fragments.Profile.Settings.AccountAndSecurity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;

import in.aabhasjindal.otptextview.OtpTextView;


public class ChangePhoneNumberFragment extends Fragment {

    private OtpTextView otp_view;
    String otp;
    String phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_phone_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        if (getArguments() != null) {
            otp = getArguments().getString("verifyOTP").toString();
            Toast.makeText(requireContext(), "otp " + otp, Toast.LENGTH_SHORT).show();

        } else {
//            Toast.makeText(requireContext(), "Argmts null", Toast.LENGTH_SHORT).show();
        }

        findIds(view);
        clicks(view);
    }

    private void PhoneEnterDialogbox() {

        Dialog dialog_share = new Dialog(getContext());
        dialog_share.setContentView(R.layout.edit_phone_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(false);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        AppCompatButton enterPhoneCancelBtn = dialog_share.findViewById(R.id.enterPhoneCancelBtn);
        EditText enterPhoneEdtx = dialog_share.findViewById(R.id.enterPhoneEdtx);
        AppCompatButton enterPhoneSaveBtn = dialog_share.findViewById(R.id.enterPhoneSaveBtn);

        enterPhoneCancelBtn.setOnClickListener(view -> {
            dialog_share.dismiss();
        });

        enterPhoneSaveBtn.setOnClickListener(view -> {
            this.phone = enterPhoneEdtx.getText().toString();
            if(phone != null){
                editPhoneApi(dialog_share);
//                dialog_share.dismiss();
            }else{
                Toast.makeText(requireContext(), "enter phone", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findIds(View view) {
        otp_view = view.findViewById(R.id.otp_view);
    }

    private void clicks(View view) {

        view.findViewById(R.id.changePhoneBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        view.findViewById(R.id.changePhoneNextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (otp.equalsIgnoreCase(otp_view.getOTP())) {
                    PhoneEnterDialogbox();
                } else {
                    Toast.makeText(requireContext(), "Invalid otp", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void editPhoneApi(Dialog dialog_share) {

        String id = App.getSharedpref().getString("userId");
        if (id == null && phone != null) {
            Toast.makeText(requireContext(), "phone is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "id : " + id, Toast.LENGTH_SHORT).show();
            new Mvvm().verifyPhoneNumber(requireActivity(), id, phone, otp).observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {

                    if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
//                        Toast.makeText(requireContext(), "1 " + sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        App.getSharedpref().saveString("phone",phone);
                        dialog_share.dismiss();
                    } else {
//                        Toast.makeText(requireContext(), "0 " + sendOtpRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}