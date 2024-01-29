package com.live.worldsocialintegrationapp.Fragments.Profile.My_look;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.Adapters.MyLookEntryEffectRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.EntryEffectsRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseFramesRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.App;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Entry_effect extends Fragment implements MyLookEntryEffectRVAdapter.Callback {
    private RecyclerView MyLookEntryEffectsRV;
    private LinearLayout mylookLinarlayout;
    private List<EntryEffectsRoot.Detail> list;
    MyLookEntryEffectRVAdapter myLookFramesRVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__entry_effect, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        getGifsApi();
    }

    private void findIds(View view) {
        MyLookEntryEffectsRV = view.findViewById(R.id.MyLookEntryEffectsRV);
        mylookLinarlayout = view.findViewById(R.id.mylookLinarlayout);
    }

    private void getGifsApi() {

        new Mvvm().getGifs(requireActivity()).observe(requireActivity(), new Observer<EntryEffectsRoot>() {
            @Override
            public void onChanged(EntryEffectsRoot entryEffectsRoot) {

                if (entryEffectsRoot != null) {

                    if (entryEffectsRoot.getSuccess().equalsIgnoreCase("1")) {
                        list = new ArrayList<>();
                        list = entryEffectsRoot.getDetails();

                        if (!list.isEmpty()) {
                            mylookLinarlayout.setVisibility(View.GONE);
                            MyLookEntryEffectsRV.setVisibility(View.VISIBLE);
                            try {
                                myLookFramesRVAdapter = new MyLookEntryEffectRVAdapter(list, requireContext(), Fragment_Entry_effect.this);
                            } catch (Exception exception) {

                            }
                            MyLookEntryEffectsRV.setAdapter(myLookFramesRVAdapter);
                        } else {
                            mylookLinarlayout.setVisibility(View.VISIBLE);
                            MyLookEntryEffectsRV.setVisibility(View.GONE);
                        }
                    } else {

                    }
                } else {
                }
            }
        });
    }

    @Override
    public void enableFrame(EntryEffectsRoot.Detail detail, TextView view) {

    }

    @Override
    public void testCars(EntryEffectsRoot.Detail frame_Detail) {


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
            parser.decodeFromURL(new URL(frame_Detail.getGifUrl()), new SVGAParser.ParseCompletion() {
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