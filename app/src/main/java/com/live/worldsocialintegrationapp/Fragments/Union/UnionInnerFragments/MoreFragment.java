package com.live.worldsocialintegrationapp.Fragments.Union.UnionInnerFragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.live.worldsocialintegrationapp.Adapters.CountryListAdapter;
import com.live.worldsocialintegrationapp.Adapters.NewMoreAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.GiftWall.RootGiftWall;
import com.live.worldsocialintegrationapp.ModelClasses.RootNewUser;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.databinding.FragmentMoreBinding;
import com.live.worldsocialintegrationapp.utils.App;


import java.util.ArrayList;
import java.util.List;

import in.aabhasjindal.otptextview.OtpTextView;


public class MoreFragment extends Fragment implements NewMoreAdapter.Callback {

    FragmentMoreBinding binding;
    NewMoreAdapter newMoreAdapter;
    List<RootNewUser.Detail> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMoreBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAdapter();
        onClicks();
        apiNewUser();
        apiGiftWall();

    }

    private void setGiftWall(RootGiftWall rootGiftWall) {

        try {

            if (rootGiftWall != null) {
                if (rootGiftWall.getDetails().get(0).getDiamond() != null) {
                    binding.coinsGiftWall1.setText(rootGiftWall.getDetails().get(0).getDiamond());
                }
                if (rootGiftWall.getDetails().get(1).getDiamond() != null) {
                    binding.coinsGiftWall2.setText(rootGiftWall.getDetails().get(1).getDiamond());
                }


                Glide.with(binding.senderImage1.getContext()).load(rootGiftWall.getDetails().get(0).getSenderImage()).error(R.drawable.user).into(binding.senderImage1);
                Glide.with(binding.reciverImage1.getContext()).load(rootGiftWall.getDetails().get(0).getReceiverImage()).error(R.drawable.user).into(binding.reciverImage1);
                Glide.with(binding.senderImage2).load(rootGiftWall.getDetails().get(1).getSenderImage()).error(R.drawable.user).into(binding.senderImage2);
                Glide.with(binding.reciverImage2).load(rootGiftWall.getDetails().get(1).getReceiverImage()).error(R.drawable.user).into(binding.reciverImage2);
            }
        }catch (Exception e){

        }
    }

    private void apiGiftWall() {

        new Mvvm().getGiftWall(requireActivity()).observe(requireActivity(), new Observer<RootGiftWall>() {
            @Override
            public void onChanged(RootGiftWall rootGiftWall) {
                if (rootGiftWall != null) {

                    if (rootGiftWall.getSuccess().equalsIgnoreCase("1")) {
                        setGiftWall(rootGiftWall);
                    }
                } else {
                    if(getContext() != null){
                        Toast.makeText(requireActivity(), "Technical issue", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }

    private void apiNewUser() {

        try {
            new Mvvm().getNewUser(requireActivity()).observe(requireActivity(), new Observer<RootNewUser>() {
                @Override
                public void onChanged(RootNewUser usersSearchRoot) {

                    if (usersSearchRoot != null) {
                        if (usersSearchRoot.getSuccess().equalsIgnoreCase("1")) {

                            list = usersSearchRoot.getDetails();
//sumit

                            try {
                                if(requireContext() != null){
                                    NewMoreAdapter newMoreAdapter = new NewMoreAdapter(list, requireContext(), MoreFragment.this);
                                    binding.newRecyclerView.setAdapter(newMoreAdapter);
                                    // newMoreAdapter.loadData(list);
                                }
                            }catch (Exception e){

                            }
                        }
                    } else {
                        if (getContext() != null) {
                            Toast.makeText(requireActivity(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });
        }catch (Exception e){

        }

    }

    private void onClicks() {

        binding.countriesMoreArrow.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_secondMainFragment_to_moreCountriesFragment);
        });

        binding.moreTVgiftWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.giftWallFragment);
            }
        });

        binding.india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", "india");
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.countriesFragment, bundle);
            }
        });
        binding.pakistan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", "pakistan");
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.countriesFragment, bundle);
            }
        });
        binding.bangladesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", "bangladesh");
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.countriesFragment, bundle);
            }
        });

        binding.UAE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", "United Arab Emirates");
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.countriesFragment, bundle);
            }
        });

        binding.USa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", "United States");
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.countriesFragment, bundle);
            }
        });

        binding.canada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("CountryName", "canada");
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.countriesFragment, bundle);
            }
        });
    }

    private void setAdapter() {
        binding.countriesRecyclerview.setAdapter(new CountryListAdapter());
    }

    @Override
    public void callback(RootNewUser.Detail detail, int pos) {
        startLive(detail, pos);


    }

    private void startLive(RootNewUser.Detail detail, int pos) {

        App.getSingletone().setLiveType("");
        Intent intent = new Intent(requireActivity(), CallActivity.class);
        intent.putExtra("channelName", detail.getChannelName());
        intent.putExtra("userId", detail.getUserId());
        intent.putExtra("liveHostIds", detail.getId());

        intent.putExtra("liveType", "multiLive");
        intent.putExtra("liveStatus", "host");
        intent.putExtra("token", detail.getToken());
        intent.putExtra("name", detail.getName());
        intent.putExtra("liveHostId", detail.getId());

        intent.putExtra("status", "1");

        if(detail.getImageTitle()!= null && !detail.getImageTitle().isEmpty()){
            intent.putExtra("broadTitle",detail.getImageTitle());
        }else{
            intent.putExtra("broadTitle",detail.getName());
        }

        intent.putExtra("liveImage", detail.getLiveimage());
        intent.putExtra("image", detail.getImageDp());

        startActivity(intent);

    }

    @Override
    public void enterPassword(RootNewUser.Detail detail, int pos) {
        Dialog dialog_share = new Dialog(requireContext());
        dialog_share.setContentView(R.layout.set_room_password_dialog_box);
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog_share.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog_share.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_share.setCanceledOnTouchOutside(true);

        Window window = dialog_share.getWindow();
        window.setGravity(Gravity.CENTER);
        OtpTextView liveLock = dialog_share.findViewById(R.id.liveLock_view);
        AppCompatButton confirm_pin = dialog_share.findViewById(R.id.confirm_pin);
        AppCompatButton cancel = dialog_share.findViewById(R.id.cancle_pin);
        confirm_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (liveLock.getOTP().equalsIgnoreCase(detail.getPassword())) {
                    startLive(detail, pos);
                    dialog_share.dismiss();
                } else {
                    Toast.makeText(requireContext(), "Wrong Pin Entered", Toast.LENGTH_SHORT).show();
                    dialog_share.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_share.dismiss();
            }
        });
        dialog_share.show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}