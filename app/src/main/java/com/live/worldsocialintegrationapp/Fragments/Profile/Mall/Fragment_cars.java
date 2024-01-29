package com.live.worldsocialintegrationapp.Fragments.Profile.Mall;

import android.annotation.SuppressLint;
import android.app.ActionBar;
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
import com.live.worldsocialintegrationapp.Adapters.AdapterCars;
import com.live.worldsocialintegrationapp.Adapters.FriendRVAdapter;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.GiftFirendFragment;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_cars extends Fragment implements AdapterCars.Callback {
    private RecyclerView recyclerView;
    private AdapterCars adapterCars;
    private List<GetLuckIdRoot.Detail> list;
    private   int check = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cars, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findIds(view);
        getLuckyIdApi();

        if (getArguments() != null){
           check = getArguments().getInt("CarsCheck");
        }

        if (check == 0) {
            view.findViewById(R.id.mallCarsTopRL).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.mallCarsTopRL).setVisibility(View.VISIBLE);
            view.findViewById(R.id.mallCarBackImage).setOnClickListener(view1 -> getActivity().onBackPressed());
        }

    }

    private void findIds(View view) {
        recyclerView = view.findViewById(R.id.recycler_view2);
    }

    private void getLuckyIdApi() {

        new Mvvm().getLuckyId(requireActivity(), AppConstants.USER_ID).observe(requireActivity(), new Observer<GetLuckIdRoot>() {
            @Override
            public void onChanged(GetLuckIdRoot getLuckIdRoot) {
                if (getLuckIdRoot != null) {
                    if (getLuckIdRoot.getStatus() == 1) {
                        list = new ArrayList<>();
                        list = getLuckIdRoot.getDetails();
                        if (isAdded()&& getContext()!=null){
                            adapterCars = new AdapterCars(list, requireContext(), Fragment_cars.this);
                            recyclerView.setAdapter(adapterCars);

                        }
                    } else {
                        //Toast.makeText(requireContext(), "0 " + getLuckIdRoot.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (getContext() != null) {
                        //Toast.makeText(getContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void purchaseCarApi(String luckyId,TextView textView) {

        //Toast.makeText(requireContext(), "purchaseUserId : " + AppConstants.USER_ID, Toast.LENGTH_SHORT).show();
        new Mvvm().purchaseCars(requireActivity(), AppConstants.USER_ID, luckyId).observe(requireActivity(), new Observer<PurchaseCarsRoot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(PurchaseCarsRoot purchaseCarsRoot) {
                if (purchaseCarsRoot != null) {
                    if (purchaseCarsRoot.getStatus() == 1) {
                        textView.setText("Bought");
                    } else {
                        notEnoughCoins();
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
    public void purchaseCar(GetLuckIdRoot.Detail detail, TextView textView) {
        if (detail != null) {
            purchaseCarApi(detail.getId(),textView);
        } else {
            //Toast.makeText(requireContext(), "luckyId is empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendCar(GetLuckIdRoot.Detail detail) {

        if (detail != null) {

            Toast.makeText(requireContext(), "Sent", Toast.LENGTH_SHORT).show();
            GiftFirendFragment.check = 1;
            Bundle bundle = new Bundle();
            bundle.putString("validity", detail.getValidity());
            bundle.putString("img", detail.getImage());
            bundle.putString("price", detail.getPrice());
            bundle.putString("giftType", "car");
            bundle.putString("getLuckId", detail.getId());

            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.giftFirendFragment, bundle);
        } else {
            //Toast.makeText(requireContext(), "Technical issue....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void testCars(GetLuckIdRoot.Detail Cardetail) {

        Dialog viewDetails_box = new Dialog(requireContext());
        viewDetails_box.setContentView(R.layout.image_dialog);
        viewDetails_box.getWindow().setBackgroundDrawable(new ColorDrawable());
        Window window = viewDetails_box.getWindow();
        viewDetails_box.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.CENTER);
        ImageView imageView = viewDetails_box.findViewById(R.id.img_profile);
        imageView.setVisibility(View.GONE);
        SVGAImageView svgaImage = viewDetails_box.findViewById(R.id.frames);

        CommonUtils.setAnimationTwo(requireContext(),Cardetail.getImage(),svgaImage);

        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width= ActionBar.LayoutParams.MATCH_PARENT;
        wlp.height= ActionBar.LayoutParams.MATCH_PARENT;
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        //   viewDetails_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewDetails_box.show();
        viewDetails_box.setCanceledOnTouchOutside(true);


//        CircleImageView zoomeImage = viewDetails_box.findViewById(R.id.img_profile);
//        Glide.with(requireContext()).load(App.getSharedpref().getString("image"))
//                .error(R.drawable.user_avatar).into(zoomeImage);

        ImageView closeBtn = viewDetails_box.findViewById(R.id.close_BTN);
        closeBtn.setOnClickListener(view -> viewDetails_box.dismiss());

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
        this.check = 0;
    }
}