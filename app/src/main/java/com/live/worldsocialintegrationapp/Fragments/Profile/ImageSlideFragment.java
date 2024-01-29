package com.live.worldsocialintegrationapp.Fragments.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.ImageRVAdpater;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.Details;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserImagesRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.smarteist.autoimageslider.IndicatorView.PageIndicatorView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ImageSlideFragment extends Fragment {

    List<GetUserImagesRoot.Detail> list= new ArrayList<>();
    ViewPager mViewPager;
    private String getImageUserId;
    ImageView demoImage;
    DotsIndicator dotsIndicator;
    Timer timer;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_slide, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.disableBottomNavigation(requireActivity());

        if (getArguments() != null && getArguments().containsKey("getImageUserId")){
            //Toast.makeText(requireContext(), "kjk "+getArguments().getString("getImageUserId"), Toast.LENGTH_SHORT).show();
            getImageUserId = getArguments().getString("getImageUserId");
        }

        findIds(view);
        getImagesApi();
        clicks(view);


//        Details details = new Details();
//        Glide.with(requireContext()).load(App.getSharedpref().getString("otherUserImg")).error(R.drawable.error).into(demoImage);
    }

    private void clicks(View view) {

        view.findViewById(R.id.imageSlideBackImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void findIds(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        demoImage = (ImageView) view.findViewById(R.id.demoImage);
        dotsIndicator = (DotsIndicator) view.findViewById(R.id.dots_indicator);

    }

    private void getImagesApi(){
        new Mvvm().getUserImages(requireActivity(), getImageUserId).observe(requireActivity(), new Observer<GetUserImagesRoot>() {
            @Override
            public void onChanged(GetUserImagesRoot getUserImagesRoot) {

                if (getUserImagesRoot != null) {
                    if (getUserImagesRoot.getStatus() == 1) {
                        list = getUserImagesRoot.getDetails();
                        if (list.isEmpty()) {
                            demoImage.setVisibility(View.VISIBLE);
                            mViewPager.setVisibility(View.GONE);
                        } else {
                            demoImage.setVisibility(View.GONE);
                            mViewPager.setVisibility(View.VISIBLE);
                            try {
                                ImageRVAdpater adapterView = new ImageRVAdpater(requireContext(), list);
                                mViewPager.setAdapter(adapterView);
                                dotsIndicator.attachTo(mViewPager);
                            }catch (Exception e){

                            }

                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    mViewPager.post(new Runnable(){

                                        @Override
                                        public void run() {
                                            mViewPager.setCurrentItem((mViewPager.getCurrentItem()+1)%list.size());
                                        }
                                    });
                                }
                            };
                            timer = new Timer();
                            timer.schedule(timerTask, 4000, 4000);
                        }

                    } else {
//                    Toast.makeText(requireContext(), "No Image", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (timer!=null){
            timer.cancel();
        }
        super.onDestroy();

    }
}