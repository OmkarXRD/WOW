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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Fragments.Profile.Friends.FriendsFragment;
import com.live.worldsocialintegrationapp.ModelClasses.BuyVipRoot;
import com.live.worldsocialintegrationapp.ModelClasses.VipRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.opensource.svgaplayer.SVGAImageView;


public class Vip5 extends Fragment {

    public static VipRoot.Detail detail;
    private TextView vip5CoinsTv, vip5YouAreVipTV;

    private LinearLayout vip1Llayout, uniqueFrameLlyout, entranceEffectLlayout, getThisCarLlayot, friendLayout, followingLyout, coinsDayLlayout, colorMsgLlayout, prventKickLlayout, antiBanLlayout, hiddenLlayout, MysteryManLlayout, flyingCommentLlayout12, exclusiveGiftlLayout,
            hideCountryOnlineLlayout;
    private Dialog dialog;

    private AppCompatButton sendButton, buyButton;
    private SVGAImageView  framesImg5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vip5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findIds(view);
        clicks(view);
        setDialogs(view);

        vip5CoinsTv.setText(detail.getCoins() + "coins/"+detail.getValid()+"days");
        if (detail.isVip_status()){
            vip5YouAreVipTV.setText("You are VIP"+detail.getId());
        }else{
            vip5YouAreVipTV.setText("You are not VIP"+detail.getId());
        }

    }

    private void findIds(View view) {


        vip1Llayout = view.findViewById(R.id.viip5IconLlayout);
        uniqueFrameLlyout = view.findViewById(R.id.vip5UniqueFrameLlyout);
        entranceEffectLlayout = view.findViewById(R.id.vip5EntranceEffectLlayout);
        getThisCarLlayot = view.findViewById(R.id.vip5GetThisCarLlayot);
        friendLayout = view.findViewById(R.id.vip5FriendLayout);
        followingLyout = view.findViewById(R.id.vip5FollowingLyout);
        coinsDayLlayout = view.findViewById(R.id.vip5CoinsDayLlayout);
        colorMsgLlayout = view.findViewById(R.id.vip5ColorMsgLlayout);
        prventKickLlayout = view.findViewById(R.id.vip5PrventKickLlayout);
        antiBanLlayout = view.findViewById(R.id.vip5AntiBanLlayout);
        hiddenLlayout = view.findViewById(R.id.vip5HiddenLlayout);
        MysteryManLlayout = view.findViewById(R.id.vip5MysteryManLlayout);
        flyingCommentLlayout12 = view.findViewById(R.id.vip5FlyingCommentLlayout12);
        exclusiveGiftlLayout = view.findViewById(R.id.vip5ExclusiveGiftlLayout);
        hideCountryOnlineLlayout = view.findViewById(R.id.vip5HideCountryOnlineLlayout);
        sendButton = view.findViewById(R.id.vip5SendBtn);
        buyButton = view.findViewById(R.id.vip5BuyBtn);


        vip5CoinsTv = view.findViewById(R.id.vip5CoinsTv);
        vip5YouAreVipTV = view.findViewById(R.id.vip5YouAreVipTV);

        framesImg5 = view.findViewById(R.id.framesImg5);
    }

    private void clicks(View view) {
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyVip();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("price", detail.getCoins());
                bundle.putString("validity", "30");
                bundle.putString("vipId", detail.getId());
                bundle.putString("giftType","VIP 5");
//                bundle.putString("img","30");
                FriendsFragment.check = 1;
                FriendsFragment.getFrameCheck = 2;
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.friendsFragment, bundle);
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

    private void setDialogs(View view) {


        vip1Llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.vip7);
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
        uniqueFrameLlyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.unique_frames);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button uniqueFrameConfirmbtn = (Button) dialog.findViewById(R.id.uniqueFrameConfirmbtn);
                uniqueFrameConfirmbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        entranceEffectLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.enterance_effect);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button entranceEffectConfirmbtn = (Button) dialog.findViewById(R.id.entranceEffectConfirmbtn);
                entranceEffectConfirmbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        getThisCarLlayot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.get_this_car);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button giftCarConfirmBtn = (Button) dialog.findViewById(R.id.giftCarConfirmBtn);
                giftCarConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        friendLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.friends_dialog_box);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button friendsConfirmBtn = (Button) dialog.findViewById(R.id.friendsConfirmBtn);

                TextView vip1FriendTv1 = (TextView) dialog.findViewById(R.id.vip1FriendTv1);
                TextView vip1FriendTv2 = (TextView) dialog.findViewById(R.id.vip1FriendTv2);
                TextView vip1FriendTv3 = (TextView) dialog.findViewById(R.id.vip1FriendTv3);

                vip1FriendTv1.setText("x600%");
                vip1FriendTv2.setText("Friends *600%");
                vip1FriendTv3.setText("Increase your Friends limit *600%");

                friendsConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        followingLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.following_dialog_box);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button followingConfirmBtn = (Button) dialog.findViewById(R.id.followingConfirmBtn);

                TextView vip2FollowingTv1 = (TextView) dialog.findViewById(R.id.vip2FollowingTv1);
                TextView vip2FollowingTv2 = (TextView) dialog.findViewById(R.id.vip2FollowingTv2);
                TextView vip2FollowingTv3 = (TextView) dialog.findViewById(R.id.vip2FollowingTv3);

                vip2FollowingTv1.setText("x600%");
                vip2FollowingTv2.setText("Following *600%");
                vip2FollowingTv3.setText("Increase your Following limit *600%");

                followingConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        coinsDayLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.coins_days);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button coinDaysConfirmBtn = (Button) dialog.findViewById(R.id.coinDaysConfirmBtn);

                TextView vip1CoinsTv1 = (TextView) dialog.findViewById(R.id.vip1CoinsTv1);
                TextView vip1CoinsTv2 = (TextView) dialog.findViewById(R.id.vip1CoinsTv2);
                TextView vip1CoinsTv3 = (TextView) dialog.findViewById(R.id.vip1CoinsTv3);

                vip1CoinsTv1.setText("+28000");
                vip1CoinsTv2.setText("28000 coins/day");
                vip1CoinsTv3.setText("Get 28000 coins/day in Daily Reward. Refresh time. UTC+0");

                coinDaysConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


        colorMsgLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.colorfull_messages);
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
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.prevent_from_being_kicked);
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
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.anti_ban);
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
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.flying_comments);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                Button flyingConfirmBtn = (Button) dialog.findViewById(R.id.flyingConfirmBtn);
                flyingConfirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        hiddenLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.hidden_country_and_online_time);
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
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.mysetry_man_dialog_box);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

            }
        });

        hiddenLlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.hidden_dialog_box_vip);
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
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.vip7);
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
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.hidden_dialog_box_vip);
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

}