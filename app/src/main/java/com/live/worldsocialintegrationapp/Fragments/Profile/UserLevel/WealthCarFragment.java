package com.live.worldsocialintegrationapp.Fragments.Profile.UserLevel;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.Adapters.WealthInsideCarRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.UserLevel.GetCarUserLevel;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class WealthCarFragment extends Fragment implements WealthInsideCarRVAdapter.Callback {

    private ImageView wealthCarBackImg;
    private RecyclerView wealthInsideCarRV;
    WealthInsideCarRVAdapter wealthInsideCarRVAdapter;
    private AppCompatButton wealthInsideCarObtainLvBtn;
    List<GetCarUserLevel.Detail> list;
    SVGAParser parser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wealth_car, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
        getCarLevelsApi();


    }

    private void clicks(View view) {
        wealthCarBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        wealthInsideCarObtainLvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.howLevelUpFragment);
            }
        });
    }

    private void findIds(View view) {
        wealthCarBackImg = view.findViewById(R.id.wealthCarBackImg);
        wealthInsideCarRV = view.findViewById(R.id.wealthInsideCarRV);
        wealthInsideCarObtainLvBtn = view.findViewById(R.id.wealthInsideCarObtainLvBtn);
    }

    private void getCarLevelsApi() {

        new Mvvm().getCarLevel(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetCarUserLevel>() {
            @Override
            public void onChanged(GetCarUserLevel getUserLevelRoot) {

                if (getUserLevelRoot != null) {
                    if (getUserLevelRoot.getStatus() == 1) {
//                    Toast.makeText(requireContext(), "1 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();

                        list = new ArrayList<>();
                        list = getUserLevelRoot.getDetails();

                        wealthInsideCarRVAdapter = new WealthInsideCarRVAdapter(list, requireContext(), WealthCarFragment.this);
                        wealthInsideCarRV.setAdapter(wealthInsideCarRVAdapter);

                    } else {
//                    Toast.makeText(requireContext(), "0 " + getUserLevelRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }


        });


    }

    @Override
    public void previewCallback(GetCarUserLevel.Detail detail) {


            Dialog dialog = new Dialog(requireContext());
            dialog.setContentView(R.layout.wealth_car_preview_dialog);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ImageView wealthCarPreviewImg = (ImageView) dialog.findViewById(R.id.wealthCarPreviewImg);
            SVGAImageView userLevelCarsRVImagePreview = (SVGAImageView) dialog.findViewById(R.id.userLevelCarsRVImagePreview);

        if(detail.isAvailable()){

            Log.d("WEALTH","level "+detail.getLuckyId().get(0).getLuckyIdImage());

            wealthCarPreviewImg.setVisibility(View.GONE);
            userLevelCarsRVImagePreview.setVisibility(View.VISIBLE);
            parser = new SVGAParser(requireContext());

            try {
                parser.decodeFromURL(new URL(detail.getLuckyId().get(0).getLuckyIdImage()), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                        SVGADrawable drawable = new SVGADrawable(videoItem);

                        userLevelCarsRVImagePreview.setImageDrawable(drawable);
                        userLevelCarsRVImagePreview.startAnimation();
                    }

                    @Override
                    public void onError() {

                    }
                }, new SVGAParser.PlayCallback() {
                    @Override
                    public void onPlay(@NonNull List<? extends File> list) {

                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{

            wealthCarPreviewImg.setVisibility(View.VISIBLE);
            userLevelCarsRVImagePreview.setVisibility(View.GONE);
            Glide.with(wealthCarPreviewImg.getContext()).load(detail.getImage()).error(R.drawable.demo_user_profile_img).into(wealthCarPreviewImg);

        }
            dialog.show();

    }

}