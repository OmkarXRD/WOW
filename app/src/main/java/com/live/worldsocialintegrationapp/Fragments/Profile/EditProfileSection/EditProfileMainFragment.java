package com.live.worldsocialintegrationapp.Fragments.Profile.EditProfileSection;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentEditProfileMainBinding;


public class EditProfileMainFragment extends Fragment {

    FragmentEditProfileMainBinding binding;
    private int check = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEditProfileMainBinding.inflate(inflater, container, false);

        onClicks();

        //binding.userName.setText(App.getSharedpref().getModel("login", RegisterRoot.class).getDetails().getName());

        return binding.getRoot();
    }

    private void onClicks() {


        binding.llMoments.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_editProfileMainFragment_to_editProfileMomentsFragment);
        });
        binding.imgCirclePhoto.setOnClickListener(view -> {

//            check = 1;
//            showDailog();
        });
//        binding.bio.setOnClickListener(view -> {
//            check = 2;
//            showDailog();
//        });
//        binding.Editname.setOnClickListener(view -> {
//            check = 3;
//            showDailog();
//        });
    }

    private void showDailog() {

        if (check == 1) {
            Dialog viewDetails_box = new Dialog(getContext());
            viewDetails_box.setContentView(R.layout.choose_photo);
            viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = viewDetails_box.getWindow();
            viewDetails_box.setCanceledOnTouchOutside(false);
            window.setGravity(Gravity.CENTER);
            viewDetails_box.show();
        }
        if (check == 2) {
            Dialog viewDetails_box = new Dialog(getContext());
            viewDetails_box.setContentView(R.layout.input_bio);
            viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = viewDetails_box.getWindow();
            viewDetails_box.setCanceledOnTouchOutside(false);
            window.setGravity(Gravity.CENTER);
            viewDetails_box.show();
        }
        if (check == 3) {
            Dialog viewDetails_box = new Dialog(getContext());
            viewDetails_box.setContentView(R.layout.edit_delete_bio);
            viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = viewDetails_box.getWindow();
            viewDetails_box.setCanceledOnTouchOutside(false);
            window.setGravity(Gravity.CENTER);
            viewDetails_box.show();
        }

    }
}