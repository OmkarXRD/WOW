package com.live.worldsocialintegrationapp.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.annotations.NotNull;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.SpinWheel.LuckyWheelView;
import com.live.worldsocialintegrationapp.SpinWheel.model.LuckyItem;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SpinWheelModelClass;
import com.live.worldsocialintegrationapp.databinding.ActivitySpinnerBinding;
import com.live.worldsocialintegrationapp.games.model.CheckSpinWheel;
import com.live.worldsocialintegrationapp.games.model.SpinModelClass;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.tapadoo.alerter.Alerter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpinnerActivity extends AppCompatActivity {
    ActivitySpinnerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        List<LuckyItem> data = new ArrayList<>();
        LuckyItem luckyItem1 = new LuckyItem();
        luckyItem1.topText = App.getSharedpref().getString("coins");
        luckyItem1.secondaryText = "Coins";
        luckyItem1.textColor = Color.parseColor("#212121");
        luckyItem1.color = Color.parseColor("#eceff1");
        data.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.topText = App.getSharedpref().getString("coinsOne");
        luckyItem2.secondaryText = "COINS";
        luckyItem2.color = Color.parseColor("#00cf00");
        luckyItem2.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.topText =  App.getSharedpref().getString("coinsTwo");
        luckyItem3.secondaryText = "COINS";
        luckyItem3.textColor = Color.parseColor("#212121");
        luckyItem3.color = Color.parseColor("#eceff1");
        data.add(luckyItem3);

        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.topText = App.getSharedpref().getString("diamonds_key");
        luckyItem4.secondaryText = "DIAMONDS";
        luckyItem4.color = Color.parseColor("#7f00d9");
        luckyItem4.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem4);

        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.topText = App.getSharedpref().getString("diamondsOne_key");
        luckyItem5.secondaryText = "DIAMONDS";
        luckyItem5.textColor = Color.parseColor("#212121");
        luckyItem5.color = Color.parseColor("#eceff1");
        data.add(luckyItem5);

        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.topText = "1";
        luckyItem6.secondaryText = "Frame";
        luckyItem6.color = Color.parseColor("#dc0000");
        luckyItem6.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem6);

        LuckyItem luckyItem7 = new LuckyItem();
        luckyItem7.topText = "1";
        luckyItem7.secondaryText = "Entry effect";
        luckyItem7.textColor = Color.parseColor("#212121");
        luckyItem7.color = Color.parseColor("#eceff1");
        data.add(luckyItem7);

        LuckyItem luckyItem8 = new LuckyItem();
        luckyItem8.topText = "7";
        luckyItem8.secondaryText = "Gifts";
        luckyItem8.color = Color.parseColor("#008bff");
        luckyItem8.textColor = Color.parseColor("#ffffff");
        data.add(luckyItem8);

        binding.wheelview.setData(data);
        binding.wheelview.setRound(5);

            binding.spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int randomNumber = r.nextInt(8);

                new Mvvm().checkSpinWheelLiveData(SpinnerActivity.this,AppConstants.USER_ID).observe(SpinnerActivity.this, new Observer<CheckSpinWheel>() {
                    @Override
                    public void onChanged(CheckSpinWheel checkSpinWheel) {
                        if (checkSpinWheel.getSuccess().equalsIgnoreCase("1")){
                            binding.wheelview.startLuckyWheelWithTargetIndex(randomNumber);
                        }else {
                            Alerter.create(SpinnerActivity.this).setTitle("Alert!").setText(checkSpinWheel.getMessage()).setBackgroundResource(R.drawable.wallet_background).show();
                        }
                    }
                });
            }
        });

        binding.wheelview.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                updateCash(index);
            }
        });
    }

    int typpe;
    void updateCash(int index) {
        long cash = 0;
        switch (index) {
            case 0:
                cash = Long.parseLong(App.getSharedpref().getString("coins"));
                typpe=1;
                break;
            case 1:
                cash = Long.parseLong(App.getSharedpref().getString("coinsOne"));
                typpe=1;
                break;
            case 2:
                cash = Long.parseLong(App.getSharedpref().getString("coinsTwo"));
                typpe=1;
                break;
            case 3:
                cash = Long.parseLong(App.getSharedpref().getString("diamonds_key"));
                typpe=2;
                break;
            case 4:
                cash = Long.parseLong(App.getSharedpref().getString("diamondsOne_key"));
                typpe=2;
                break;
            case 5:
                typpe=3;
                break;
            case 6:
                typpe=4;
                break;
            case 7:
                typpe=5;
                break;
        }

        //Toast.makeText(this, "typpe : "+typpe, Toast.LENGTH_SHORT).show();

        new Mvvm().getSpinWheelLiveData(this, AppConstants.USER_ID, String.valueOf(cash), String.valueOf(typpe)).observe(this, new Observer<SpinModelClass>() {
            @Override
            public void onChanged(SpinModelClass spinModelClass) {
                if (spinModelClass.getSuccess().equalsIgnoreCase("1")){

                    if (typpe==1){
                        Toast.makeText(SpinnerActivity.this, "You won "+spinModelClass.getDetails().getCoins()+" coins", Toast.LENGTH_SHORT).show();
                        if (Integer.parseInt(spinModelClass.getDetails().getCoins())>0){
                            Dialog d=new Dialog(SpinnerActivity.this);
                        d.setContentView(R.layout.winnerspindialog);
                        TextView textCoin = d.findViewById(R.id.txtCoin);
                            CircleImageView circleImageView = d.findViewById(R.id.btnProfile);
                        textCoin.setText(spinModelClass.getDetails().getCoins());
                        Button btnClose = d.findViewById(R.id.btnClose);
                        btnClose.setOnClickListener(view -> {
                            d.dismiss();
                        });
                        d.show();

                        if (!App.getSharedpref().getString("image").isEmpty()){
                            Glide.with(SpinnerActivity.this).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img).into(circleImageView);
                        }else {
                            Glide.with(SpinnerActivity.this).load(R.drawable.avtar_a).error(R.drawable.demo_user_profile_img).into(circleImageView);
                        }
                        Window window = d.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        }else {
                            Dialog d=new Dialog(SpinnerActivity.this);
                        d.setContentView(R.layout.looserlayoutdesign);
                        TextView textCoin = d.findViewById(R.id.txtCoin);
                        textCoin.setText(spinModelClass.getDetails().getCoins());
                            CircleImageView circleImageView = d.findViewById(R.id.btnProfile);
                        Button btnClose = d.findViewById(R.id.btnClose);
                        btnClose.setOnClickListener(view -> {
                            d.dismiss();
                        });
                        d.show();
                            if (!App.getSharedpref().getString("image").isEmpty()){
                                Glide.with(SpinnerActivity.this).load(App.getSharedpref().getString("image")).error(R.drawable.demo_user_profile_img).into(circleImageView);
                            }else {
                                Glide.with(SpinnerActivity.this).load(R.drawable.avtar_a).error(R.drawable.demo_user_profile_img).into(circleImageView);
                            }
                        Window window = d.getWindow();
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        }
                        //Toast.makeText(SpinnerActivity.this, ""+spinModelClass.getMessage(), Toast.LENGTH_SHORT).show();
                    } else if (typpe==2) {
                        Dialog diamondialog=new Dialog(SpinnerActivity.this);
                        diamondialog.setContentView(R.layout.diamondswinnerdesign);
                        TextView textDiamonds = diamondialog.findViewById(R.id.diamondsText);
                        textDiamonds.setText(spinModelClass.getDetails().getMyDiamond());
                        RelativeLayout ok = diamondialog.findViewById(R.id.ok);
                        ok.setOnClickListener(view -> {
                            diamondialog.dismiss();
                        });
                        diamondialog.show();
                        Toast.makeText(SpinnerActivity.this, "You won "+spinModelClass.getDetails().getMyDiamond()+" diamonds", Toast.LENGTH_SHORT).show();
                    } else if (typpe==3){
                        SVGAParser parser;
                        Dialog d=new Dialog(SpinnerActivity.this);
                        d.setContentView(R.layout.winnerlayoutdesign);
                        SVGAImageView profieFrame = d.findViewById(R.id.profieFrame);
                        parser = new SVGAParser(SpinnerActivity.this);
                        try {
                            parser.decodeFromURL(new URL( App.getSharedpref().getString("frame")), new SVGAParser.ParseCompletion() {
                                @Override
                                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                                    SVGADrawable drawable = new SVGADrawable(videoItem);
                                    profieFrame.setImageDrawable(drawable);
                                    profieFrame.startAnimation();
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


                        RelativeLayout ok = d.findViewById(R.id.ok);
                        ok.setOnClickListener(view -> {
                            d.dismiss();
                        });
                        d.show();
                        Toast.makeText(SpinnerActivity.this, "You won 1 frame", Toast.LENGTH_SHORT).show();
                    } else if (typpe==4){
                        SVGAParser parser;
                        Dialog d=new Dialog(SpinnerActivity.this);
                        d.setContentView(R.layout.winnerlayoutdesign);
                        SVGAImageView profieFrame = d.findViewById(R.id.profieFrame);
                        parser = new SVGAParser(SpinnerActivity.this);
                        try {
                            parser.decodeFromURL(new URL( App.getSharedpref().getString("entryEffect")), new SVGAParser.ParseCompletion() {
                                @Override
                                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                                    SVGADrawable drawable = new SVGADrawable(videoItem);
                                    profieFrame.setImageDrawable(drawable);
                                    profieFrame.startAnimation();
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


                        RelativeLayout ok = d.findViewById(R.id.ok);
                        ok.setOnClickListener(view -> {
                            d.dismiss();
                        });
                        d.show();
                        Toast.makeText(SpinnerActivity.this, "You won 1 Entry effect", Toast.LENGTH_SHORT).show();
                    }else if (typpe==5){
                        Toast.makeText(SpinnerActivity.this, "You won 7 Gifts", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(SpinnerActivity.this, ""+spinModelClass.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Alerter.create(SpinnerActivity.this).setTitle("").setText(spinModelClass.getMessage()).setBackgroundResource(R.drawable.wallet_background).show();
                }
            }
        });

//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//
//        database
//                .collection("users")
//                .document(FirebaseAuth.getInstance().getUid())
//                .update("coins", FieldValue.increment(cash)).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(SpinnerActivity.this, "Coins added in account.", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}