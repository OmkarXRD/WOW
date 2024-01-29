package com.live.worldsocialintegrationapp.Fragments.Profile.My_look;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.Adapters.MyLookCarsRVAdpter;
import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_cars;
import com.live.worldsocialintegrationapp.ModelClasses.GetLuckIdRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseCarsRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Fragment_My_Cars extends Fragment implements MyLookCarsRVAdpter.Callback {

    private RecyclerView carsRV;
    private MyLookCarsRVAdpter myLookCarsRVAdpter;
    private List<PurchaseCarsRoot.Detail> list = new ArrayList<>();
    private LinearLayout mylookCarsLinearLayout;
    private RelativeLayout goBuyRL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__my__cars, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        clicks(view);
        getPurchaseCars();

//        myLookCarsRVAdpter=new MyLookCarsRVAdpter(new ArrayList<>(),requireContext(),Fragment_My_Cars.this);
//        carsRV.setAdapter(myLookCarsRVAdpter);


    }

    private void clicks(View view) {

        goBuyRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("CarsCheck",1);
                Navigation.findNavController(view).navigate(R.id.fragment_cars,bundle);
            }
        });

    }

    private void findIds(View view) {
        carsRV = view.findViewById(R.id.carsRV);
        mylookCarsLinearLayout = view.findViewById(R.id.mylookCarsLinearLayout);
        goBuyRL = view.findViewById(R.id.goBuyRL);
    }


    private void getPurchaseCars() {

        new Mvvm().getPurchaseCar(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<PurchaseCarsRoot>() {
            @Override
            public void onChanged(PurchaseCarsRoot carsRoot) {
                if (carsRoot != null) {
                    if (carsRoot.getStatus() == 1) {
                        list = carsRoot.getDetails();

                        if (!list.isEmpty()) {
                            mylookCarsLinearLayout.setVisibility(View.GONE);
                            carsRV.setVisibility(View.VISIBLE);

                            if (isAdded() && getContext()!=null){
                                myLookCarsRVAdpter = new MyLookCarsRVAdpter(list, getContext(), Fragment_My_Cars.this);
                                carsRV.setAdapter(myLookCarsRVAdpter);
                                myLookCarsRVAdpter.notifyDataSetChanged();
                            }
                        } else {
                            carsRV.setVisibility(View.GONE);
                            mylookCarsLinearLayout.setVisibility(View.VISIBLE);
                        }

//                    myLookCarsRVAdpter.loadData(list);
//                    myLookCarsRVAdpter.notifyDataSetChanged();

                    } else {
                    }
                } else {
                    if (getContext() != null) {
                        Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void enableCarFrames(PurchaseCarsRoot.Detail detail, int position, TextView textView) {

        if (detail != null) {
            new Mvvm().applyLuckId(requireActivity(), AppConstants.USER_ID, detail.getLuckyId()).observe(requireActivity(), new Observer<GetLuckIdRoot>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onChanged(GetLuckIdRoot getLuckIdRoot) {
                    if (getLuckIdRoot != null) {
                        if (getLuckIdRoot.getStatus() == 1) {
                            textView.setText("Remove");
                            getPurchaseCars();
                        } else {
                            textView.setText("Apply");
                        }
                    } else {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
//            Toast.makeText(requireContext(), "detail is null", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(requireContext(), "entry frame : "+detail.getFrameIMage(), Toast.LENGTH_SHORT).show();
        App.getSharedpref().saveString("entryFrame",detail.getFrameIMage());


    }

    @Override
    public void testCars(PurchaseCarsRoot.Detail Cars_Detail) {

        Dialog viewDetails_box = new Dialog(requireContext());
        viewDetails_box.setContentView(R.layout.image_dialog);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        viewDetails_box.show();
        viewDetails_box.setCanceledOnTouchOutside(true);
        ImageView imageView = viewDetails_box.findViewById(R.id.img_profile);
        imageView.setVisibility(View.GONE);
        SVGAImageView svgaImage = viewDetails_box.findViewById(R.id.frames);

        CommonUtils.setAnimationTwo(requireContext(),Cars_Detail.getFrameIMage(),svgaImage);
        ImageView closeBtn = viewDetails_box.findViewById(R.id.close_BTN);
        closeBtn.setOnClickListener(view -> viewDetails_box.dismiss());
    }
}