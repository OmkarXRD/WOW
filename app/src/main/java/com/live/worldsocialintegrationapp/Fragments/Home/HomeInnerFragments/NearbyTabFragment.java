package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.live.worldsocialintegrationapp.Adapters.LiveNearbyRVAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.NearByLiveUsers.NearByLiveUsersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.RootNewUser;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.databinding.FragmentNearbyTabBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import in.aabhasjindal.otptextview.OtpTextView;


public class NearbyTabFragment extends Fragment implements LiveNearbyRVAdapter.Callback {

    private List<NearByLiveUsersRoot.Detail> list = new ArrayList<>();
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    public static String latitude, longitude;
    FragmentNearbyTabBinding binding;
    LiveNearbyRVAdapter liveTabAllRVAdapter;
//    Detail detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNearbyTabBinding.inflate(inflater, container, false);
        RelativeLayout relativeLayout = requireActivity().findViewById(R.id.rlHomeFragmentTop);
        relativeLayout.setVisibility(View.VISIBLE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ActivityCompat.requestPermissions( requireActivity(),
//                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        getCurrentLatLng(view);
        LiveUsersApi(latitude, longitude);
        ClickListeners(view);

        requireActivity().findViewById(R.id.chiragIV).setVisibility(View.GONE);

    }

    private void getCurrentLatLng(View view) {

        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }

    }

    private void ClickListeners(View view) {

        // SetOnRefreshListener on SwipeRefreshLayout
        binding.swipeRefreshLayoutNearBy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swipeRefreshLayoutNearBy.setRefreshing(false);
                RearrangeItems();
            }
        });

    }


    private void LiveUsersApi(String latitude, String longitude) {

        String id = AppConstants.USER_ID;

        try {
            new Mvvm().getNearByUsers(requireActivity(), id, latitude, longitude,AppConstants.USER_ID).observe(requireActivity(), new Observer<NearByLiveUsersRoot>() {
                @Override
                public void onChanged(NearByLiveUsersRoot nearByLiveUsersRoot) {
                    if (nearByLiveUsersRoot != null) {
                        if (nearByLiveUsersRoot.getSuccess().equalsIgnoreCase("1")) {

                            binding.noNearUser.setVisibility(View.GONE);
                            list = nearByLiveUsersRoot.getDetails();

                            liveTabAllRVAdapter = new LiveNearbyRVAdapter(list, requireContext(),NearbyTabFragment.this);
                            binding.nearByRecyclerView.setAdapter(liveTabAllRVAdapter);
                        } else {
                            return;
                        }
                    } else {

                        if(getContext() != null){
                            binding.noNearUser.setVisibility(View.VISIBLE);
                            Toast.makeText(requireActivity(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            });
        } catch (Exception e) {
        }
    }

    public void RearrangeItems() {
        // Shuffling the data of ArrayList using system time
        Collections.shuffle(list, new Random(System.currentTimeMillis()));
        LiveNearbyRVAdapter liveNearbyRVAdapter = new LiveNearbyRVAdapter(list, requireContext(),NearbyTabFragment.this);
        binding.nearByRecyclerView.setAdapter(liveNearbyRVAdapter);
    }

    //-----------------------------------------------------------------------------------------------------------

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);


            } else {
//                Toast.makeText(requireContext(), "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            requireActivity().findViewById(R.id.chiragIV).setVisibility(View.VISIBLE);
        } catch (Exception e) {
//            Toast.makeText(requireContext(), "Icon not removed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void callback(NearByLiveUsersRoot.Detail detail,int pos) {
        if(detail.getId() != null){
            startLive(detail,pos);
        }

    }

    @Override
    public void enterPassword(NearByLiveUsersRoot.Detail detail, int pos) {
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
                if (liveLock.getOTP().equalsIgnoreCase(detail.getUserLivePassword())) {
                    startLive(detail,pos);
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

    private void startLive(NearByLiveUsersRoot.Detail detail,int pos){
        App.getSingletone().setLiveType("");
        Intent intent = new Intent(requireActivity(), CallActivity.class);
        intent.putExtra("channelName", detail.getChannelName());
        intent.putExtra("userId", detail.getId());

        intent.putExtra("liveHostIds", detail.getId());
        intent.putExtra("liveType", "multiLive");
        intent.putExtra("liveStatus", "host");
        intent.putExtra("token", detail.getToken());
        intent.putExtra("name", detail.getName());
        intent.putExtra("liveHostId", detail.getId());

        if(detail.getImageTitle()!= null && !detail.getImageTitle().isEmpty()){
            intent.putExtra("broadTitle",detail.getImageTitle());
        }else{
            intent.putExtra("broadTitle",detail.getName());
        }

        intent.putExtra("liveImage", detail.getLiveimage());
        intent.putExtra("image", detail.getUserProfileImage());


        intent.putExtra("status", "1");
        startActivity(intent);
    }
}