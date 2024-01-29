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

import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.Adapters.MyLookFramesRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_cars;
import com.live.worldsocialintegrationapp.Fragments.Profile.Mall.Fragment_frames;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseFramesRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.tapadoo.alerter.Alerter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Fragment_My_Frames extends Fragment implements MyLookFramesRVAdapter.Callback {
    private RecyclerView framesRV;
    private MyLookFramesRVAdapter myLookFramesRVAdapter;
    private List<PurchaseFramesRoot.Detail> list;
    private LinearLayout mylookFamesLinearLayout;
    private RelativeLayout framesGobuyRL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__my__frames, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
        getPurchaseFrame();
//        myLookFramesRVAdapter = new MyLookFramesRVAdapter(list,requireContext(), Fragment_My_Frames.this);
//        framesRV.setAdapter(myLookFramesRVAdapter);
    }

    private void clicks(View view) {

        framesGobuyRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("frameCheck", 1);
                Navigation.findNavController(view).navigate(R.id.fragment_frames, bundle);
            }
        });
    }

    private void findIds(View view) {
        framesRV = view.findViewById(R.id.framesRV);
        mylookFamesLinearLayout = view.findViewById(R.id.mylookFamesLinearLayout);
        framesGobuyRL = view.findViewById(R.id.framesGobuyRL);
    }

    private void getPurchaseFrame() {

        new Mvvm().getPurchaseFrame(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<PurchaseFramesRoot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(PurchaseFramesRoot purchaseFramesRoot) {

                if (purchaseFramesRoot != null) {
                    if (purchaseFramesRoot.getStatus() == 1) {

                        list = new ArrayList<>();
                        list = purchaseFramesRoot.getDetails();
//                    myLookFramesRVAdapter.loadData(list);


                        if (!list.isEmpty()) {
                            mylookFamesLinearLayout.setVisibility(View.GONE);
                            framesRV.setVisibility(View.VISIBLE);

                            if (isAdded() && getContext()!=null){
                                myLookFramesRVAdapter = new MyLookFramesRVAdapter(list, getContext(), Fragment_My_Frames.this);
                                framesRV.setAdapter(myLookFramesRVAdapter);
                                myLookFramesRVAdapter.notifyDataSetChanged();
                            }
                        } else {
                            framesRV.setVisibility(View.GONE);
                            mylookFamesLinearLayout.setVisibility(View.VISIBLE);
                        }

                    } else {
                        framesRV.setVisibility(View.GONE);
                        mylookFamesLinearLayout.setVisibility(View.VISIBLE);
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
    public void enableFrame(PurchaseFramesRoot.Detail detail, TextView view) {

        if (detail != null) {
            new Mvvm().applyFrame(requireActivity(), AppConstants.USER_ID, detail.getFrameId()).observe(requireActivity(), new Observer<GetFramesRoot>() {
                @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
                @Override
                public void onChanged(GetFramesRoot getFramesRoot) {
                    if (getFramesRoot != null) {
                        if (getFramesRoot.getStatus() == 1) {
                            view.setText("Remove");
                            getPurchaseFrame();
                            myLookFramesRVAdapter.notifyDataSetChanged();
//                            Toast.makeText(requireContext(), "1" + getFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                            Alerter.create(requireActivity()).setTitle("Applied").setText("this frame is Applied").setBackgroundResource(R.drawable.wallet_background).show();
                        } else {
                            view.setText("Apply");
//                            Toast.makeText(requireContext(), "0 " + getFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
            Toast.makeText(requireContext(), "detail is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void testCars(PurchaseFramesRoot.Detail frame_Detail) {


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
        //   viewDetails_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewDetails_box.show();
        viewDetails_box.setCanceledOnTouchOutside(true);

        CircleImageView zoomeImage = viewDetails_box.findViewById(R.id.img_profile);
        Glide.with(requireContext()).load(App.getSharedpref().getString("image"))
                .error(R.drawable.user_avatar).into(zoomeImage);

        SVGAImageView svgaImage = viewDetails_box.findViewById(R.id.frames);
        SVGAParser parser;
        parser = new SVGAParser(requireContext());
        try {
            parser.decodeFromURL(new URL(frame_Detail.getFrameIMage()), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADrawable drawable = new SVGADrawable(videoItem);
                    svgaImage.setImageDrawable(drawable);
                    svgaImage.startAnimation();
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
        ImageView closeBtn = viewDetails_box.findViewById(R.id.close_BTN);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDetails_box.dismiss();
            }
        });

    }
}