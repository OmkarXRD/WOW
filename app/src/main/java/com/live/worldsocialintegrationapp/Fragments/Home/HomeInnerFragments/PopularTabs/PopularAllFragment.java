package com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.PopularTabs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.MainActivity;
import com.live.worldsocialintegrationapp.Activites.SplashActivity;
import com.live.worldsocialintegrationapp.Adapters.BannerSliderAdapter;
import com.live.worldsocialintegrationapp.Adapters.PopularAllItemsAdapter;
import com.live.worldsocialintegrationapp.Adapters.PopularLiveAdapter;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.Detail;
import com.live.worldsocialintegrationapp.ModelClasses.AllPopularUsers.GetAllPopularRoot;
import com.live.worldsocialintegrationapp.ModelClasses.ApplyForHostModelClass;
import com.live.worldsocialintegrationapp.ModelClasses.BannerSliderRoot;
import com.live.worldsocialintegrationapp.ModelClasses.NearByLiveUsers.NearByLiveUsersRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.model.LiveUserModel;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.databinding.FragmentPopularTabBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

import in.aabhasjindal.otptextview.OtpTextView;
import io.reactivex.internal.operators.single.SingleFlatMapIterableFlowable;
public class PopularAllFragment extends Fragment implements PopularAllItemsAdapter.Callback,BannerSliderAdapter.Callback {

    FragmentPopularTabBinding binding;
    List<GetAllPopularRoot.Detail> list = new ArrayList<>();
    //    List<LiveUserModel.Detail>  list = new ArrayList<>();
    public static int CallActvityToFamilyCheck = 0;
    List<BannerSliderRoot.Detail> sliderDataArrayList;
    /// for live ended
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference ChatRequestCountRef = firebaseDatabase.getReference().child("ChatRequestCount");
    private final DatabaseReference liveUsersRef = firebaseDatabase.getReference().child("liveUsersRef");
    private final DatabaseReference cleanUserCommentsRef = firebaseDatabase.getReference().child("cleanUserCommentsRef");
    private final DatabaseReference lockRef = firebaseDatabase.getReference().child("lockRef");
    private final DatabaseReference banChatRef = firebaseDatabase.getReference().child("banChatRef");
    private final DatabaseReference muteMicRef = firebaseDatabase.getReference().child("muteMicRef");
    private final DatabaseReference LuckBagRef = firebaseDatabase.getReference().child("LuckBagRef");
    private final DatabaseReference luckyDivideUsersRef = firebaseDatabase.getReference().child("luckyDivideUsersRef");
    private final DatabaseReference lockSeat = firebaseDatabase.getReference().child("lockSeat");
    private final DatabaseReference emojiRef = firebaseDatabase.getReference().child("emojiRef");
    private final DatabaseReference userLiveAnnouncement = firebaseDatabase.getReference().child("userLiveAnnouncement");
    private final DatabaseReference ref = firebaseDatabase.getReference().child("userInfo");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPopularTabBinding.inflate(inflater, container, false);
        RelativeLayout relativeLayout = requireActivity().findViewById(R.id.rlHomeFragmentTop);
        relativeLayout.setVisibility(View.VISIBLE);
//      getActivity().findViewById(R.id.chiragIV).setVisibility(View.GONE);
        removeLiveUser();
        setUserVisibleHint(isVisible());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if(CallActvityToFamilyCheck==1){
//            Bundle bundle = new Bundle();
//            bundle.putString("familyId",App.getSharedpref().getString("familyId"));
//            Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.familyBatchFragment, bundle);
//        }

        binding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                getAllPopularLiveUsersApi();
                binding.pullToRefresh.setRefreshing(false);
            }
        });

        clisks(view);
//      hitLiveUserApi();
        BannerSlider();
    }

    private void BannerSlider() {

        if(requireContext() != null){

        // we are creating array list for storing our image urls.
        sliderDataArrayList = new ArrayList<>();

        new Mvvm().getBanners(requireActivity()).observe(requireActivity(), new Observer<BannerSliderRoot>() {
            @Override
            public void onChanged(BannerSliderRoot bannerSliderRoot) {
                if (bannerSliderRoot != null) {
                    if (bannerSliderRoot.getSuccess().equalsIgnoreCase("1")) {
                        sliderDataArrayList = bannerSliderRoot.getDetails();
                        // passing this array list inside our adapter class.
                        BannerSliderAdapter adapter = new BannerSliderAdapter(sliderDataArrayList,PopularAllFragment.this::onClickCallback);
                        // below method is used to set auto cycle direction in left to
                        // right direction you can change according to requirement.
                        binding.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                        // below method is used to
                        // setadapter to sliderview.
                        binding.slider.setSliderAdapter(adapter);
                        // below method is use to set
                        // scroll time in seconds.
                        binding.slider.setScrollTimeInSec(3);
                        // to set it scrollable automatically
                        // we use below method.
                        binding.slider.setAutoCycle(true);
                        // to start autocycle below method is used.
                        binding.slider.startAutoCycle();
                    }
                }
            }
          });
        }
    }

    private void clisks(View view) {

        binding.popularFamilyRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.fragment_Family);
            }
        });

        binding.popularEventsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_home)).navigate(R.id.eventsFragment);
            }
        });
    }

    private void hitLiveUserApi() {
//        new Mvvm().getLiveUserApi(requireActivity()).observe(requireActivity(), liveUserModel -> {
//            if (liveUserModel.getSuccess().equalsIgnoreCase("1")) {
////                binding.txtNoUserFound.setText("");
//                setAdapter(liveUserModel.getDetails());
//            } else {
////                binding.txtNoUserFound.setText("No Live Users");
//            }
//        });
    }

    private void setAdapter(List<LiveUserModel.Detail> details) {
        PopularLiveAdapter popularLiveAdapter = new PopularLiveAdapter(details, detail -> {
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
            intent.putExtra("image", detail.getImage());
            intent.putExtra("status", "1");
            startActivity(intent);
        });
        binding.recyclerViewPopularLive.setAdapter(popularLiveAdapter);

    }

    private void getAllPopularLiveUsersApi() {

        try {
            new Mvvm().getAllPopularLiveUsers(requireActivity(), AppConstants.USER_ID, AppConstants.USER_ID).observe(requireActivity(), new Observer<GetAllPopularRoot>() {
                @Override
                public void onChanged(GetAllPopularRoot getAllPopularRoot) {

                    if (getAllPopularRoot != null) {
                        if (getAllPopularRoot.getSuccess().equalsIgnoreCase("1")) {

                            list = getAllPopularRoot.getDetails();

                            if (list.isEmpty()) {
                                binding.noLiveUserTv.setVisibility(View.VISIBLE);
                                binding.pullToRefresh.setVisibility(View.GONE);
                            } else {
                                binding.noLiveUserTv.setVisibility(View.GONE);
                                try {
                                    PopularAllItemsAdapter popularAllItemsAdapter = new PopularAllItemsAdapter(list, PopularAllFragment.this, requireContext());
                                    binding.popularRV.setAdapter(popularAllItemsAdapter);
                                } catch (Exception e) {
                                }
                            }
                        } else {
                            return;
                        }
                    } else {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                            binding.noLiveUserTv.setVisibility(View.VISIBLE);
                        } else {
                        }
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    public void callback(GetAllPopularRoot.Detail detail, int pos) {

        App.getSingletone().setLiveType("");
        Intent intent = new Intent(requireActivity(), CallActivity.class);
        intent.putExtra("channelName", detail.getChannelName());
        intent.putExtra("userId", detail.getUserId());
        if (detail.getHostStatus()!=null){
            intent.putExtra("hostStatusUser", detail.getHostStatus());
        }else {
            
        }
        intent.putExtra("liveHostIds", detail.getUserId());
        intent.putExtra("liveType", "multiLive");
        intent.putExtra("liveStatus", "host");
        intent.putExtra("token", detail.getToken());
        intent.putExtra("name", detail.getName());
        intent.putExtra("liveHostId", detail.getUserId());

    if (detail.getGalleryAppliedImage()!=null){
            intent.putExtra("galleryImageTheme", detail.getGalleryAppliedImage());
        }else {

        }

        if (detail.getImageTitle() != null && !detail.getImageTitle().isEmpty()) {
            intent.putExtra("broadTitle", detail.getImageTitle());
        } else {
            intent.putExtra("broadTitle", detail.getName());
        }

        intent.putExtra("liveImage", detail.getLiveimage());
        intent.putExtra("image", detail.getImageDp());

        intent.putExtra("status", "1");

        intent.putExtra("dob", CommonUtils.ageCalcualte(detail.getDob()));
        intent.putExtra("gender", detail.getGender());

        intent.putExtra("userDob", CommonUtils.ageCalcualte(detail.getUserDob()));
        intent.putExtra("statuslive",detail.getStatus());
        intent.putExtra("UserGender", detail.getUserGender());
        startActivity(intent);

    }

    @Override
    public void enterPassword(GetAllPopularRoot.Detail detail, int pos) {
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
                    callback(detail, pos);
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

    private void removeLiveUser() {

        liveUsersRef.child(AppConstants.USER_ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if(snapshot.hasChild("liveId")){
                        String liveId = snapshot.child("liveId").getValue().toString();
                        String hostId = snapshot.child("hostId").getValue().toString();
                        String liveType = snapshot.child("liveType").getValue().toString();
                        Log.i("LiveCall","in removeLiveUser");
                        //endLiveApi(liveId, hostId, liveType);
                        getAllPopularLiveUsersApi();
                    }
                } else {
                    String liveId = App.getSharedpref().getString("liveId");
                    String liveType = App.getSharedpref().getString("liveType");
                    String hostId = App.getSharedpref().getString("hostId");

                    if (!liveId.equalsIgnoreCase("") && !liveType.equalsIgnoreCase("") && !hostId.equalsIgnoreCase("")) {
                        Log.i("LiveCall","in removeLiveUser else if");
                        endLiveApi(liveId, hostId, liveType);
                        getAllPopularLiveUsersApi();
                    }
                    getAllPopularLiveUsersApi();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void endLiveApi(String liveId, String hostId, String liveType) {
        Log.i("LiveCall","in end Live api");
        try {

            new Mvvm().endLiveCall(requireActivity(), liveId).observe(requireActivity(), new Observer<SendOtpRoot>() {
                @Override
                public void onChanged(SendOtpRoot sendOtpRoot) {
                    if (sendOtpRoot != null) {
                        if (sendOtpRoot.getStatus().equalsIgnoreCase("1")) {
                            try {
                                ref.child(hostId).child(liveType).removeValue();
                                liveUsersRef.child(AppConstants.USER_ID).removeValue();

                                muteMicRef.child(hostId).removeValue();
                                userLiveAnnouncement.child(hostId).removeValue();
                                cleanUserCommentsRef.child(hostId).removeValue();
                                LuckBagRef.child(hostId).removeValue();
                                emojiRef.child(hostId).removeValue();
                                luckyDivideUsersRef.child(hostId).removeValue();
                                lockSeat.child(hostId).removeValue();
                                ref.child(hostId).child(liveType).removeValue();

//                            Toast.makeText(requireContext(), "Live ended", Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                            }
                            App.getSharedpref().saveString("liveId", "");
                            App.getSharedpref().saveString("liveType", "");
                            App.getSharedpref().saveString("hostId", "");
                        }
                    } else {
                        if (getContext() != null) {
                            Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("IsRefresh", "Yes");
        }
    }

    //method to detect touch input on Banners
    @Override
    public void onClickCallback(int pos) {
        if(pos == 2){
            Navigation.findNavController(binding.getRoot()).navigate(R.id.applyforAnchor);
        }
        //Navigation.findNavController(binding.getRoot()).navigate(R.id.apply_for_agency);

            //below condition shows 2 options to user apply for agency and apply for Host
        //        if (pos==2){
//            Dialog dialog = new Dialog(requireContext());
//            dialog.setContentView(R.layout.applyforagencyandanchor);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            dialog.setCancelable(true);
//            TextView applyforAnchor  = dialog.findViewById(R.id.applyforAnchor);
//            TextView applyforagency = dialog.findViewById(R.id.applyforagency);
//            Window window = dialog.getWindow();
//            window.setGravity(Gravity.CENTER);
//            WindowManager.LayoutParams wlp = window.getAttributes();
//            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//            wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//            window.setAttributes(wlp);
//            dialog.show();
//            applyforagency.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    Navigation.findNavController(binding.getRoot()).navigate(R.id.apply_for_agency);
//                }
//            });
//
//            applyforAnchor.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//
//                    // Set the message show for the Alert time
//                    builder.setMessage("Are you sure want to apply for Anchor ?");
//
//                    // Set Alert Title
//                    builder.setTitle("Apply For Anchor");
//
//                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
//                    builder.setCancelable(false);
//
//                    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
//                    builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
//                        // When the user click yes button then app will close
//
//
////                        new Mvvm().applyHost(AppConstants.USER_ID).observe(requireActivity(), new Observer<ApplyForHostModelClass>() {
////                            @Override
////                            public void onChanged(ApplyForHostModelClass applyForHostModelClass) {
////                                if (applyForHostModelClass!=null){
////                                    if (applyForHostModelClass.getStatus()==1){
////                                        Toast.makeText(requireContext(), ""+applyForHostModelClass.getMessage(), Toast.LENGTH_SHORT).show();
////                                    }else {
////                                        Toast.makeText(requireContext(), ""+applyForHostModelClass.getMessage(), Toast.LENGTH_SHORT).show();
////                                    }
////                                }else {
////                                    Toast.makeText(requireContext(), "Technical issue occurred", Toast.LENGTH_SHORT).show();
////                                }
////
////                            }
////                        });
//                        Navigation.findNavController(binding.getRoot()).navigate(R.id.applyForHostFragment);
//                    });
//
//                    // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
//                    builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
//                        // If user click no then dialog box is canceled.
//                        dialog.cancel();
//                    });
//
//                    // Create the Alert dialog
//                    AlertDialog alertDialog = builder.create();
//                    // Show the Alert Dialog box
//                    alertDialog.show();
//
//                }
//            });
//        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getAllPopularLiveUsersApi();
            }
        }, 1000);
    }
}