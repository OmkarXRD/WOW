package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentPhoneCodeBinding;

public class PhoneCodeFragment extends Fragment {

    FragmentPhoneCodeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneCodeBinding.inflate(inflater, container, false);

        onClick();

        return binding.getRoot();
    }

    private void onClick() {
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialogbox();
            }
        });
    }

    private void showdialogbox() {

        Dialog dialogChooseAlbum = new Dialog(requireContext());

        dialogChooseAlbum.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogChooseAlbum.setContentView(R.layout.show_later_dialog);

        dialogChooseAlbum.setCanceledOnTouchOutside(true);

        dialogChooseAlbum.setCancelable(true);

        dialogChooseAlbum.getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        dialogChooseAlbum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//        dialogChooseAlbum.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        dialogChooseAlbum.getWindow().setGravity(Gravity.BOTTOM);

        dialogChooseAlbum.show();


    }
}