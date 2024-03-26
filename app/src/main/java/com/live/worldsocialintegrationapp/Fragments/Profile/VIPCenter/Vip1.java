package com.live.worldsocialintegrationapp.Fragments.Profile.VIPCenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.GiftFirendFragment;
import com.live.worldsocialintegrationapp.ModelClasses.BuyVipRoot;
import com.live.worldsocialintegrationapp.ModelClasses.VipRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.opensource.svgaplayer.SVGAImageView;

public class Vip1 extends Fragment {

    private LinearLayout vip1Llayout, uniqueFrameLlyout, entranceEffectLlayout, getThisCarLlayot, friendLayout, followingLyout, coinsDayLlayout, colorMsgLlayout, prventKickLlayout, antiBanLlayout, hiddenLlayout, MysteryManLlayout, flyingCommentLlayout12, exclusiveGiftlLayout,
            hideCountryOnlineLlayout;
    private Dialog dialog;
    public static VipRoot.Detail detail;
    private TextView vip1CoinsTv,vip1YouAreVipTV;
    private AppCompatButton vip1SendBtn, vip1BuyBtn;
    private SVGAImageView framesImg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vip1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
        setDialogs(view);
        setData();


    }

    private void setData() {
        vip1CoinsTv.setText(detail.getCoins() + "coins/"+detail.getValid()+"days");
        if (detail.isVip_status()){
            vip1YouAreVipTV.setText("You are VIP"+detail.getId());
        }else{
            vip1YouAreVipTV.setText("You are not VIP"+detail.getId());
        }

    }

    private void setDialogs(View view) {

        vip1Llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.vip7);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button exclusiveGiftConfirmBtn = (Button) dialog.findViewById(R.id.exclusiveGiftConfirmBtn);
                ImageView vip1ExclusiveGiftImg = (ImageView) dialog.findViewById(R.id.vip1ExclusiveGiftImg);
                exclusiveGiftConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Glide.with(vip1ExclusiveGiftImg.getContext()).load(detail.getExclusiveGiftImage()).into(vip1ExclusiveGiftImg);
            }
        });
        uniqueFrameLlyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.unique_frames);
                dialog.setCancelable(true);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button uniqueFrameConfirmbtn = (Button) dialog.findViewById(R.id.uniqueFrameConfirmbtn);
                ImageView vip1UninqueFrameImg = (ImageView) dialog.findViewById(R.id.vip1UninqueFrameImg);
                uniqueFrameConfirmbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Glide.with(vip1UninqueFrameImg.getContext()).load(detail.getUniqueFrameImage()).into(vip1UninqueFrameImg);
            }
        });

        entranceEffectLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.enterance_effect);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button entranceEffectConfirmbtn = (Button) dialog.findViewById(R.id.entranceEffectConfirmbtn);
                ImageView vip1EntranceEffectImg = (ImageView) dialog.findViewById(R.id.vip1EntranceEffectImg);
                entranceEffectConfirmbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Glide.with(vip1EntranceEffectImg.getContext()).load(detail.getEntranceEffectImage()).into(vip1EntranceEffectImg);
            }
        });

        getThisCarLlayot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.get_this_car);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button giftCarConfirmBtn = (Button) dialog.findViewById(R.id.giftCarConfirmBtn);
                ImageView vip1GetThisCarImg = (ImageView) dialog.findViewById(R.id.vip1GetThisCarImg);
                giftCarConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Glide.with(vip1GetThisCarImg.getContext()).load(detail.getGetThisCarImage()).into(vip1GetThisCarImg);
            }
        });

        friendLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.friends_dialog_box);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button friendsConfirmBtn = (Button) dialog.findViewById(R.id.friendsConfirmBtn);
//                ImageView vip1FriendsImg = (ImageView) dialog.findViewById(R.id.vip1FriendsImg);

                TextView vip1FriendTv1 = (TextView) dialog.findViewById(R.id.vip1FriendTv1);
                TextView vip1FriendTv2 = (TextView) dialog.findViewById(R.id.vip1FriendTv2);
                TextView vip1FriendTv3 = (TextView) dialog.findViewById(R.id.vip1FriendTv3);

                vip1FriendTv1.setText("x140%");
                vip1FriendTv2.setText("Friends *140%");
                vip1FriendTv3.setText("Increase your Friends limit *x140%");

                friendsConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
//                Glide.with(vip1FriendsImg.getContext()).load(detail.getFriendsImage()).into(vip1FriendsImg);
            }
        });

        followingLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.following_dialog_box);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button followingConfirmBtn = (Button) dialog.findViewById(R.id.followingConfirmBtn);
//                ImageView vip1FollowingImg = (ImageView) dialog.findViewById(R.id.vip1FollowingImg);

                TextView vip2FollowingTv1 = (TextView) dialog.findViewById(R.id.vip2FollowingTv1);
                TextView vip2FollowingTv2 = (TextView) dialog.findViewById(R.id.vip2FollowingTv2);
                TextView vip2FollowingTv3 = (TextView) dialog.findViewById(R.id.vip2FollowingTv3);

                vip2FollowingTv1.setText("x140%");
                vip2FollowingTv2.setText("Following *140%");
                vip2FollowingTv3.setText("Increase your Following limit *140%");

                followingConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
//                Glide.with(vip1FollowingImg.getContext()).load(detail.getFollowingFriends()).into(vip1FollowingImg);
            }
        });

        coinsDayLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.coins_days);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button coinDaysConfirmBtn = (Button) dialog.findViewById(R.id.coinDaysConfirmBtn);
//                ImageView vip1CoinsImg = (ImageView) dialog.findViewById(R.id.vip1CoinsImg);

                TextView vip1CoinsTv1 = (TextView) dialog.findViewById(R.id.vip1CoinsTv1);
                TextView vip1CoinsTv2 = (TextView) dialog.findViewById(R.id.vip1CoinsTv2);
                TextView vip1CoinsTv3 = (TextView) dialog.findViewById(R.id.vip1CoinsTv3);

                vip1CoinsTv1.setText("+600");
                vip1CoinsTv2.setText("600 coins/day");
                vip1CoinsTv3.setText("Get 600 coins/day in Daily Reward. Refresh time. UTC+0");

                coinDaysConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
//                Glide.with(vip1CoinsImg.getContext()).load(detail.getCoinsImage()).into(vip1CoinsImg);
            }
        });


        colorMsgLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.colorfull_messages);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button colorFulMsgConfirmBtn = (Button) dialog.findViewById(R.id.colorFulMsgConfirmBtn);
                colorFulMsgConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        prventKickLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.prevent_from_being_kicked);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();


                Button preventConfirmBtn = (Button) dialog.findViewById(R.id.preventConfirmBtn);
                preventConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        antiBanLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.anti_ban);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button antiBanConfirmBtn = (Button) dialog.findViewById(R.id.antiBanConfirmBtn);
                antiBanConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        flyingCommentLlayout12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.flying_comments);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button flyingConfirmBtn = (Button) dialog.findViewById(R.id.flyingConfirmBtn);
                ImageView vip1FlyingCommentImg = (ImageView) dialog.findViewById(R.id.vip1FlyingCommentImg);
                flyingConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Glide.with(vip1FlyingCommentImg.getContext()).load(detail.getFlyingCommentImage()).into(vip1FlyingCommentImg);
            }
        });

        hiddenLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.hidden_country_and_online_time);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button hiddenConfirmBtn = (Button) dialog.findViewById(R.id.hiddenConfirmBtn);
                hiddenConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        MysteryManLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.mysetry_man_dialog_box);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

            }
        });

        hiddenLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.hidden_dialog_box_vip);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();


                Button hiddenConfirmBtn = (Button) dialog.findViewById(R.id.hiddenConfirmBtn);
                hiddenConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        exclusiveGiftlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.vip7);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button exclusiveGiftConfirmBtn = (Button) dialog.findViewById(R.id.exclusiveGiftConfirmBtn);
                exclusiveGiftConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


            }
        });

        hideCountryOnlineLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.hidden_dialog_box_vip);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button hiddenConfirmBtn = (Button) dialog.findViewById(R.id.hiddenConfirmBtn);
                hiddenConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //Othe drawables----

        //hidden_country_and_online_time
        //hidden_dialog_box
        //anti_ban
        //prevent_from_being_kicked
        //flying_comment
    }

    private void findIds(View view) {

        vip1Llayout = view.findViewById(R.id.vip1Llayout);
        uniqueFrameLlyout = view.findViewById(R.id.uniqueFrameLlyout);
        entranceEffectLlayout = view.findViewById(R.id.entranceEffectLlayout);
        getThisCarLlayot = view.findViewById(R.id.getThisCarLlayot);
        friendLayout = view.findViewById(R.id.friendLayout);
        followingLyout = view.findViewById(R.id.followingLyout);
        coinsDayLlayout = view.findViewById(R.id.coinsDayLlayout);
        colorMsgLlayout = view.findViewById(R.id.colorMsgLlayout);
        prventKickLlayout = view.findViewById(R.id.prventKickLlayout);
        antiBanLlayout = view.findViewById(R.id.antiBanLlayout);
        hiddenLlayout = view.findViewById(R.id.hiddenLlayout);
        MysteryManLlayout = view.findViewById(R.id.MysteryManLlayout);
        flyingCommentLlayout12 = view.findViewById(R.id.flyingCommentLlayout12);
        exclusiveGiftlLayout = view.findViewById(R.id.exclusiveGiftlLayout);
        hideCountryOnlineLlayout = view.findViewById(R.id.hideCountryOnlineLlayout);
        vip1CoinsTv = view.findViewById(R.id.vip1CoinsTv);
        vip1SendBtn = view.findViewById(R.id.vip1SendBtn);
        vip1BuyBtn = view.findViewById(R.id.vip1BuyBtn);
        vip1YouAreVipTV = view.findViewById(R.id.vip1YouAreVipTV);


//        framesImg=view.findViewById(R.id.framesImg);

    }

    private void clicks(View view) {

        vip1SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("price", detail.getCoins());
                bundle.putString("validity", "30");
                bundle.putString("vipId", detail.getId());
                bundle.putString("giftType","VIP 1");
                //bundle.putString("img","30");
                FriendsFragment.check = 1;
                FriendsFragment.getFrameCheck = 2;
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.friendsFragment, bundle);
                Log.i("Vipppp","sendClicked");
            }
        });

        vip1BuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyVip();
            }
        });
    }

    private void buyVip() {
        new Mvvm().buyVip(requireActivity(), AppConstants.USER_ID, detail.getId()).observe(requireActivity(), new Observer<BuyVipRoot>() {
            @Override
            public void onChanged(BuyVipRoot buyVipRoot) {

                if (buyVipRoot.getStatus() == 1) {
                    Toast.makeText(requireContext(), "1 " + buyVipRoot.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    notEnoughCoins();
                }
            }
        });
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

}