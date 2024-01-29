package com.live.worldsocialintegrationapp.Fragments.Profile.Mall;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.Adapters.AdapterFrames;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.GiftFirendFragment;
import com.live.worldsocialintegrationapp.ModelClasses.GetFramesRoot;
import com.live.worldsocialintegrationapp.ModelClasses.PurchaseFramesRoot;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_frames extends Fragment implements AdapterFrames.Callback {

    private RecyclerView recyclerView1;
    private AdapterFrames adapterFrames;
    private List<GetFramesRoot.Detail> list;
    private   int check = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frames, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        getFramesApi();


        if(getArguments() != null){
           check=getArguments().getInt("frameCheck");
        }


        if (check == 0) {
            view.findViewById(R.id.mallFramesTopRL).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.mallFramesTopRL).setVisibility(View.VISIBLE);
            view.findViewById(R.id.mallFramesBackImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
        }

    }

    private void findIds(View view) {
        recyclerView1 = view.findViewById(R.id.recycler_view1);
    }

    private void getFramesApi() {

        new Mvvm().getFrames(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetFramesRoot>() {
            @Override
            public void onChanged(GetFramesRoot getFramesRoot) {

                if (getFramesRoot != null) {
                    if (getFramesRoot.getStatus() == 1) {
                        list = new ArrayList<>();
                        list = getFramesRoot.getDetails();
                        if (isAdded() && getContext() !=null){
                            adapterFrames = new AdapterFrames(list, getContext(), Fragment_frames.this);
                            recyclerView1.setAdapter(adapterFrames);
                        }
                    } else {
//                    Toast.makeText(requireContext(), "0 "+getFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (getContext() != null) {
                        //Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void purchaseFrame(String frameId, TextView textView) {
        if (!frameId.isEmpty()) {
//            Toast.makeText(requireContext(), "purchaseUserId : "+AppConstants.USER_ID, Toast.LENGTH_SHORT).show();
            new Mvvm().purchaseFrames(requireActivity(), AppConstants.USER_ID, frameId).observe(requireActivity(), new Observer<PurchaseFramesRoot>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onChanged(PurchaseFramesRoot purchaseFramesRoot) {

                    if (purchaseFramesRoot != null) {
                        if (purchaseFramesRoot.getStatus() == 1) {
                            textView.setText("Bought");

                        //Toast.makeText(requireContext(), "1 "+purchaseFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            notEnoughCoins();
//                        Toast.makeText(requireContext(), "0 "+purchaseFramesRoot.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (getContext() != null) {
                            //Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } else {
//            Toast.makeText(requireContext(), "frameId is empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendFrame(GetFramesRoot.Detail detail) {
        if (detail != null) {
            Log.d("sendFrame", "sendFrame: "+detail.getFrame_img());
            GiftFirendFragment.check = 2;
            Bundle bundle = new Bundle();
            bundle.putString("validity", detail.getValidity());
            bundle.putString("image", detail.getFrame_img());
            bundle.putString("price", detail.getPrice());
            bundle.putString("giftType", "frame");
            bundle.putString("getFrameId", detail.getId());

            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.giftFirendFragment, bundle);
        } else {
            //Toast.makeText(requireContext(), "Technical issue....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void testWare(GetFramesRoot.Detail frameDetail) {

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
        viewDetails_box.setCanceledOnTouchOutside(true);

        CircleImageView zoomeImage = viewDetails_box.findViewById(R.id.img_profile);
        //Toast.makeText(requireContext(), "ggggggg"+App.getSharedpref().getString("image"), Toast.LENGTH_SHORT).show();
        Log.d("logggg","logggg : "+App.getSharedpref().getString("image"));
        Glide.with(requireContext()).load(App.getSharedpref().getString("image")).error(R.drawable.user_avatar).into(zoomeImage);
        SVGAImageView svgaImage = viewDetails_box.findViewById(R.id.frames);

        CommonUtils.setAnimation(requireContext(),frameDetail.getFrame_img(),svgaImage);
        ImageView closeBtn = viewDetails_box.findViewById(R.id.close_BTN);
        closeBtn.setOnClickListener(view -> viewDetails_box.dismiss());
        viewDetails_box.show();

    }

    private void notEnoughCoins() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Not enough coins, want to recharge?");
        builder.setTitle("Tips");
        builder.setCancelable(false);
        builder.setPositiveButton("Recharge", (DialogInterface.OnClickListener) (dialog, which) -> {
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.rechargeCointsFragment);
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        check = 0;
    }
}