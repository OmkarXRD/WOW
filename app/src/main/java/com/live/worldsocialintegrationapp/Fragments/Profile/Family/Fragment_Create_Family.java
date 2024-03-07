package com.live.worldsocialintegrationapp.Fragments.Profile.Family;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;


public class Fragment_Create_Family extends Fragment {
    //007
    private static final int MININUM_WEALTH_LEVEL_FOR_FAMILY_CREATION = 0;


    public Fragment_Create_Family() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__create__family, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());
        clicks(view);
        setStatusBarGradiant(requireActivity());
    }

    private void clicks(View view) {

        view.findViewById(R.id.createFamilyRL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wealthLevel =App.getSharedpref().getString("mylevel"); //thhis is the wealth level

                if(wealthLevel != null && !wealthLevel.isEmpty()){

                    if(Integer.parseInt(wealthLevel) >= MININUM_WEALTH_LEVEL_FOR_FAMILY_CREATION){
                        Bundle bundle = new Bundle();
                        bundle.putString("status","2");
                        Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.createFamilyFragment,bundle);
                    }else{
                        dialogBox();
                    }
                }
                else{
                    dialogBox();
                }
            }
        });

        view.findViewById(R.id.createfamilyBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }


    private void dialogBox(){
        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.create_family_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.setCanceledOnTouchOutside(true);
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.copyFrom(dialog_share.getWindow().getAttributes());
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialog_share.getWindow().setAttributes(layoutParams);
        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog_share.show();

        AppCompatButton createFamilyBtn= dialog_share.findViewById(R.id.createFamilyBtn);

        createFamilyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_share.dismiss();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.background_family);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }
}