package com.live.worldsocialintegrationapp.Fragments.ChangeNumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.live.worldsocialintegrationapp.Activites.MainActivity;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.databinding.FragmentAddPasswordBinding;
import com.live.worldsocialintegrationapp.utils.App;


public class AddPasswordFragment extends Fragment {

    FragmentAddPasswordBinding binding;
    String phoneNumber,countryCode;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddPasswordBinding.inflate(inflater, container, false);
        phoneNumber = App.getSharedpref().getString("phone");
        //countryCode = App.getSharedpref().getString("countryCode");
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClicks();

        binding.phoneNumber.setText("+"+phoneNumber);
    }


    private void onClicks() {

        binding.save.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    binding.save.setBackgroundColor(getResources().getColor(R.color.greeni));
                }else{

                }
            }
        });

        binding.save.setOnClickListener(view -> {

               String password = binding.enterPasswordTxt.getText().toString();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addPhoneNumber_to_phoneCode);

        });

        binding.backEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        binding.passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                boolean isVisible =binding.enterPasswordTxt.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                binding.enterPasswordTxt.setInputType(isVisible ?
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                        InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                // Move cursor to the end of the text
                binding.enterPasswordTxt.setSelection(binding.enterPasswordTxt.getText().length());

                // Change the icon based on visibility state
                binding.passwordVisibilityToggle.setImageResource(isVisible ?
                        R.drawable.password_visible : R.drawable.password_hide);
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Done! Please login again", Toast.LENGTH_SHORT).show();

            }
        });

    }



}