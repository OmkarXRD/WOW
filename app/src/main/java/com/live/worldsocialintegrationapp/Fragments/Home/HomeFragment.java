package com.live.worldsocialintegrationapp.Fragments.Home;

import static android.app.ProgressDialog.show;
import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.worldsocialintegrationapp.Activites.HomeActivity;
import com.live.worldsocialintegrationapp.Activites.SpinnerActivity;
import com.live.worldsocialintegrationapp.Activites.SplashActivity;
import com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.NearbyTabFragment;
import com.live.worldsocialintegrationapp.Fragments.Home.HomeInnerFragments.PopularTabs.PopularAllFragment;
import com.live.worldsocialintegrationapp.Fragments.Home.Related.ChiragFragment.ChiragBottom_Sheet_dialog;
import com.live.worldsocialintegrationapp.Fragments.Home.Related.RelatedTabFragment;
import com.live.worldsocialintegrationapp.Fragments.Home.Related.SearchUsers;
import com.live.worldsocialintegrationapp.ModelClasses.GeneratedIdClass;
import com.live.worldsocialintegrationapp.ModelClasses.GetUserDetail.GetUserDetailRoot;
import com.live.worldsocialintegrationapp.ModelClasses.SendOtpRoot;
import com.live.worldsocialintegrationapp.R;
import com.live.worldsocialintegrationapp.Retrofit.Mvvm;
import com.live.worldsocialintegrationapp.agora.openvcall.model.SpinWheelModelClass;
import com.live.worldsocialintegrationapp.agora.openvcall.model.TokenGenerateModel;
import com.live.worldsocialintegrationapp.agora.openvcall.ui.CallActivity;
import com.live.worldsocialintegrationapp.databinding.ActivityHomeBinding;
import com.live.worldsocialintegrationapp.databinding.FragmentHomeBinding;
import com.live.worldsocialintegrationapp.utils.App;
import com.live.worldsocialintegrationapp.utils.AppConstants;
import com.live.worldsocialintegrationapp.utils.CommonUtils;
import com.takusemba.spotlight.Spotlight;

import java.util.HashMap;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    String coins, coinsOne, coinsTwo;
    String diamonds, diamondsOne, diamondsTwo;
    String userId, userName, userImage;
    View view;

    public static int backCheck = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    private Mvvm homeFragViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.rlHomeFragmentTop.setVisibility(View.VISIBLE);
        homeFragViewModel = new ViewModelProvider(this).get(Mvvm.class);
//        requireActivity().getWindow().setBackgroundDrawableResource(R.drawable.gradient);
//        firstSelectedTab(view);
        setStatusBarGradiant(requireActivity());
        onClick();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rlHomeFragmentTop.setVisibility(View.VISIBLE);
        backPressed(view);
        banUserStatusCheck();

        new Mvvm().getSpinWheelDetailsViewModel(requireActivity(),"1").observe(this, new Observer<SpinWheelModelClass>() {
            @Override
            public void onChanged(SpinWheelModelClass spinWheelModelClass) {
                if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
                    coins= spinWheelModelClass.getDetails().get(0).getCoins();
                    coinsOne=spinWheelModelClass.getDetails().get(1).getCoins();
                    coinsTwo=spinWheelModelClass.getDetails().get(2).getCoins();
                    App.getSharedpref().saveString("coins",coins);
                    App.getSharedpref().saveString("coinsOne",coinsOne);
                    App.getSharedpref().saveString("coinsTwo",coinsTwo);
                }
            }
        });

        new Mvvm().getSpinWheelDetailsViewModel(requireActivity(),"2").observe(this, new Observer<SpinWheelModelClass>() {
            @Override
            public void onChanged(SpinWheelModelClass spinWheelModelClass) {
                if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
                    diamonds= spinWheelModelClass.getDetails().get(0).getDiamonds();
                    diamondsOne=spinWheelModelClass.getDetails().get(1).getDiamonds();
                    App.getSharedpref().saveString("diamonds_key",diamonds);
                    App.getSharedpref().saveString("diamondsOne_key",diamondsOne);
                }
            }
        });

        new Mvvm().getSpinWheelDetailsViewModel(requireActivity(),"3").observe(this, new Observer<SpinWheelModelClass>() {
            @Override
            public void onChanged(SpinWheelModelClass spinWheelModelClass) {
                if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
                    App.getSharedpref().saveString("frame",spinWheelModelClass.getDetails().get(0).getFrame());

                }
            }
        });

        new Mvvm().getSpinWheelDetailsViewModel(requireActivity(),"4").observe(this, new Observer<SpinWheelModelClass>() {
            @Override
            public void onChanged(SpinWheelModelClass spinWheelModelClass) {
                if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
                    App.getSharedpref().saveString("entryEffect",spinWheelModelClass.getDetails().get(0).getEntryEffect());
                }
            }
        });

        new Mvvm().getSpinWheelDetailsViewModel(requireActivity(),"5").observe(this, new Observer<SpinWheelModelClass>() {
            @Override
            public void onChanged(SpinWheelModelClass spinWheelModelClass) {
                if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
                    App.getSharedpref().saveString("firstgift",spinWheelModelClass.getDetails().get(0).getGift());
                    App.getSharedpref().saveString("Secondgift",spinWheelModelClass.getDetails().get(1).getGift());
                    App.getSharedpref().saveString("thirdgift",spinWheelModelClass.getDetails().get(2).getGift());
                    App.getSharedpref().saveString("fourthgift",spinWheelModelClass.getDetails().get(3).getGift());
                    App.getSharedpref().saveString("fifthgift",spinWheelModelClass.getDetails().get(4).getGift());
                    App.getSharedpref().saveString("fifthgift",spinWheelModelClass.getDetails().get(5).getGift());
                    App.getSharedpref().saveString("fifthgift",spinWheelModelClass.getDetails().get(6).getGift());
                }
            }
        });

        userImage = App.getSharedpref().getString("image");
        userName = App.getSharedpref().getString("name");
        if (userId !=null){
            userId = App.getSharedpref().getString("userId");
        }else {
            userId = AppConstants.USER_ID;
        }

//        new ShowcaseView.Builder(getActivity())
//                .setTarget(new ViewTarget(((View) binding.HomeBroadImg)))
//                .setContentTitle("Pick Your DOB")
//                .setContentText("Please Select your date of birth")
//                .hideOnTouchOutside()
//                .build();

        if (backCheck != 1) {
            firstSelectedTab(view);
            backCheck = 0;
        } else {
            relatedTab(view);
        }

    }

    private void banUserStatusCheck() {
        String id = App.getSharedpref().getString("userId");
        homeFragViewModel.getGeneratedIdClassLiveData(requireActivity(),AppConstants.USER_ID).observe(requireActivity(), new Observer<GeneratedIdClass>() {
            @Override
            public void onChanged(GeneratedIdClass generatedIdClass) {
                if (generatedIdClass.getSuccess().equalsIgnoreCase("1")){
                    if (generatedIdClass.getStatus().equalsIgnoreCase("1")){
                        if (id.length() == 0) {
                            //Toast.makeText(requireContext(), "user id null", Toast.LENGTH_SHORT).show();
                        } else {
                            homeFragViewModel.logoutUser(requireActivity(), id).observe(requireActivity(), new Observer<SendOtpRoot>() {
                                @Override
                                public void onChanged(SendOtpRoot sendOtpRoot) {
                                    if (sendOtpRoot.getSuccess().equalsIgnoreCase("1")) {
                                        App.getSharedpref().clearPreferences();
                                        if(mGoogleSignInClient != null){
                                            mGoogleSignInClient.signOut();
                                        }
                                        disconnectFromFacebook();
                                        AppConstants.USER_ID = "";
                                        startActivity(new Intent(requireContext(), SplashActivity.class));
                                        getActivity().finish();
                                    } else {
                                    }
                                }
                            });
                        }
                        getActivity().finish();
                    } else if (generatedIdClass.getStatus().equalsIgnoreCase("2")) {
                        //Toast.makeText(requireContext(), ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                        getActivity().finishAffinity();
                    } else if (generatedIdClass.getStatus().equalsIgnoreCase("3")) {
                        //Toast.makeText(requireContext(), ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                        getActivity().finishAffinity();
                    }
                }else {
                    //Toast.makeText(getContext(), ""+generatedIdClass.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();
    }

    @Override
    public void onResume() {
        super.onResume();
        disableBottomNavigation();
    }

    private void disableBottomNavigation() {
        View view1 = requireActivity().findViewById(R.id.bottom_lay);
        view1.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onClick() {

        binding.chiragIV.setOnClickListener(view1 -> {
//            homeFragViewModel.getSpinWheelDetailsViewModel(this,"1").observe(this, new Observer<SpinWheelModelClass>() {
//                @Override
//                public void onChanged(SpinWheelModelClass spinWheelModelClass) {
//
//                    if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
//                        coins= spinWheelModelClass.getDetails().get(0).getCoins();
//                        coinsOne=spinWheelModelClass.getDetails().get(1).getCoins();
//                        coinsTwo=spinWheelModelClass.getDetails().get(2).getCoins();
//                        Toast.makeText(SpinnerActivity.this, "coicns"+coins, Toast.LENGTH_SHORT).show();
//                        App.getSharedpref().saveString("coins",coins);
//                        App.getSharedpref().saveString("coinsOne",coinsOne);
//                        App.getSharedpref().saveString("coinsTwo",coinsTwo);
//
//                    }
//                }
//            });
//
//            homeFragViewModel.getSpinWheelDetailsViewModel(this,"2").observe(this, new Observer<SpinWheelModelClass>() {
//                @Override
//                public void onChanged(SpinWheelModelClass spinWheelModelClass) {
//                    if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
//                        diamonds= spinWheelModelClass.getDetails().get(0).getDiamonds();
//                        diamondsOne=spinWheelModelClass.getDetails().get(1).getDiamonds();
//                        diamondsTwo=spinWheelModelClass.getDetails().get(2).getDiamonds();
//                        Toast.makeText(SpinnerActivity.this, "diamonds"+diamonds, Toast.LENGTH_SHORT).show();
//                        App.getSharedpref().saveString("diamonds_key",diamonds);
//                        App.getSharedpref().saveString("diamondsOne_key",diamondsOne);
//                        App.getSharedpref().saveString("diamondsTwo_key",diamondsTwo);
//                    }
//                }
//            });
//
//            homeFragViewModel.getSpinWheelDetailsViewModel(this,"3").observe(this, new Observer<SpinWheelModelClass>() {
//                @Override
//                public void onChanged(SpinWheelModelClass spinWheelModelClass) {
//                    if (spinWheelModelClass.getSuccess().equalsIgnoreCase("1")){
//
//                    }
//                }
//            });
            Intent intent = new Intent(requireContext(), SpinnerActivity.class) ;
            startActivity(intent);
        });


        homeFragViewModel.getUserDetail(requireActivity(), "", AppConstants.USER_ID, AppConstants.USER_ID).observe(requireActivity(), new Observer<GetUserDetailRoot>() {
            @Override
            public void onChanged(GetUserDetailRoot getUserDetailRoot) {
                if (getUserDetailRoot != null) {
                    if (getUserDetailRoot.getStatus().equalsIgnoreCase("1")) {
                        App.getSharedpref().saveString("image", getUserDetailRoot.getDetails().getProfileImage());
                        App.getSharedpref().saveString("name", getUserDetailRoot.getDetails().getName());
                        App.getSharedpref().saveString("bio", getUserDetailRoot.getDetails().getBio());
                        App.getSharedpref().saveString("gender", getUserDetailRoot.getDetails().getGender());
                        App.getSharedpref().saveString("country", getUserDetailRoot.getDetails().getCountry());
                        App.getSharedpref().saveString("dob", getUserDetailRoot.getDetails().getDob());
                    }
                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }

        });

//        Dialog dialog = new Dialog(requireContext());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.getWindow().setGravity(Gravity.END);
//        dialog.getWindow().setGravity(Gravity.TOP);
//        dialog.setContentView(R.layout.guidedtourdesign);
//        LottieAnimationView voiceIndicationLottie = dialog.findViewById(R.id.voiceIndicationLottie);
//        voiceIndicationLottie.playAnimation();
//        dialog.show();
        binding.hotspot.setOnClickListener(view -> {

            binding.hotspot.setClickable(false);

            Log.d("editProfileApi","image : "+App.getSharedpref().getString("image"));
            Log.d("editProfileApi","name : "+App.getSharedpref().getString("name"));
            Log.d("editProfileApi","bio : "+App.getSharedpref().getString("bio"));
            Log.d("editProfileApi","gender : "+App.getSharedpref().getString("gender"));
            Log.d("editProfileApi","country : "+App.getSharedpref().getString("country"));
            Log.d("editProfileApi","dob : "+App.getSharedpref().getString("dob"));

            if (userName.isEmpty()){
                Toast.makeText(requireContext(), "Please Edit your Complete Profile first", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setMessage("Please Edit Your Complete Profile First!");
                builder.setTitle("Profile Alert");
                builder.setCancelable(false);
            }else {
                try {
                    hitGenerateTokenApi();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

//            if (App.getSharedpref().getString("image").isEmpty() ||
//                    App.getSharedpref().getString("name").isEmpty() ||
//                    App.getSharedpref().getString("bio").isEmpty() ||
//                    App.getSharedpref().getString("gender").isEmpty() ||
//                    App.getSharedpref().getString("countryName").isEmpty() ||
//                    App.getSharedpref().getString("dob").isEmpty()){
//                Toast.makeText(requireContext(), "Please Edit your Complete Profile first", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//                builder.setMessage("Please Edit Your Complete Profile First!");
//                builder.setTitle("Profile Alert");
//                builder.setCancelable(false);
//            }else {
//                try {
//                    hitGenerateTokenApi();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        });

        binding.Related.setOnClickListener(view -> relatedTab(view));

        binding.Popular.setOnClickListener(view -> {

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, new PopularAllFragment()).addToBackStack(null).commit();

            binding.hotspot.setClickable(false);
            binding.relatedText.setTypeface(null, Typeface.NORMAL);
            binding.nearbyText.setTypeface(null, Typeface.NORMAL);
            binding.popularText.setTypeface(null, Typeface.BOLD);

            binding.relatedText.setTextSize(15);
            binding.nearbyText.setTextSize(15);
            binding.popularText.setTextSize(17);

            binding.relatedView.setVisibility(View.GONE);
            binding.popularView.setVisibility(View.VISIBLE);
            binding.nearbyView.setVisibility(View.GONE);
            binding.search.setForegroundGravity(Gravity.END);

        });
        binding.Nearby.setOnClickListener(view -> {

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, new NearbyTabFragment()).addToBackStack(null).commit();

            binding.hotspot.setClickable(true);
            binding.relatedText.setTypeface(null, Typeface.NORMAL);
            binding.nearbyText.setTypeface(null, Typeface.BOLD);
            binding.popularText.setTypeface(null, Typeface.NORMAL);

            binding.relatedText.setTextSize(15);
            binding.nearbyText.setTextSize(17);
            binding.popularText.setTextSize(15);

            binding.relatedView.setVisibility(View.GONE);
            binding.popularView.setVisibility(View.GONE);
            binding.nearbyView.setVisibility(View.VISIBLE);
            binding.search.setForegroundGravity(Gravity.END);
        });

        binding.search.setOnClickListener(view -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, new SearchUsers()).addToBackStack(null).commit());
//        binding.chiragIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                bottomSheetDialog();
//            }
//        });
    }

    private void relatedTab(View view) {

        binding.relatedText.setTypeface(null, Typeface.BOLD);
        binding.nearbyText.setTypeface(null, Typeface.NORMAL);
        binding.popularText.setTypeface(null, Typeface.NORMAL);

        binding.relatedText.setTextSize(17);
        binding.nearbyText.setTextSize(15);
        binding.popularText.setTextSize(15);

        binding.relatedView.setVisibility(View.VISIBLE);
        binding.popularView.setVisibility(View.GONE);
        binding.nearbyView.setVisibility(View.GONE);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, new RelatedTabFragment()).commit();

        HomeActivity.binding.bottomLay.homeFragment.setImageResource(R.drawable.icon_home_ftr_activesvg);
        HomeActivity.binding.bottomLay.secondMain.setImageResource(R.drawable.icon_explore_ftsvgr);
        HomeActivity.binding.bottomLay.bottomMessage.setImageResource(R.drawable.message_bubble_chat_icon_154003_1);
        HomeActivity.binding.bottomLay.bottomProfile.setImageResource(R.drawable.icon_profile_ftr);
    }

    private void hitGenerateTokenApi() {

        String lat = App.getSharedpref().getString("latitude");
        String lng = App.getSharedpref().getString("longitude");

        HashMap<String, String> data = new HashMap<>();
        data.put("userId", userId);
        data.put("channelName", userId);
        data.put("longitude", lng);
        data.put("latitude", lat);
        data.put("hostType", "3");

        homeFragViewModel.getGenerateModel(requireActivity(), data).observe(requireActivity(), new Observer<TokenGenerateModel>() {
            @Override
            public void onChanged(TokenGenerateModel tokenGenerateModel) {
                if (tokenGenerateModel != null) {
                    if (tokenGenerateModel.getSuccess().equalsIgnoreCase("1")) {
                        App.getSingletone().setLiveType("");
                        Intent intent = new Intent(requireActivity(), CallActivity.class);
                        intent.putExtra("channelName", tokenGenerateModel.getDetails().getChannelName());
                        intent.putExtra("liveHostIds", tokenGenerateModel.getDetails().getUserId().trim().toString());
                        intent.putExtra("userId", tokenGenerateModel.getDetails().getUserId());
                        intent.putExtra("liveType", "multiLive");
                        intent.putExtra("liveStatus", "hostLive");
                        intent.putExtra("token", tokenGenerateModel.getDetails().getToke());
                        intent.putExtra("name", tokenGenerateModel.getDetails().getName());
                        intent.putExtra("status", "2");
                        intent.putExtra("image", tokenGenerateModel.getDetails().getImage());
                        intent.putExtra("liveId", tokenGenerateModel.getDetails().getMainId());
                        intent.putExtra("hostUsername", tokenGenerateModel.getDetails().getUsername());
                        intent.putExtra("dob", CommonUtils.ageCalcualte(tokenGenerateModel.getDetails().getDob()));
                        intent.putExtra("gender", tokenGenerateModel.getDetails().getGender());
                        startActivity(intent);

                    } else {
                        //Toast.makeText(requireContext(), "" + tokenGenerateModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(requireContext(), "Technical issue", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void firstSelectedTab(View view) {
        binding.relatedText.setTypeface(null, Typeface.NORMAL);
        binding.nearbyText.setTypeface(null, Typeface.NORMAL);
        binding.popularText.setTypeface(null, Typeface.BOLD);

        binding.relatedText.setTextSize(15);
        binding.nearbyText.setTextSize(15);
        binding.popularText.setTextSize(17);

        binding.relatedView.setVisibility(View.GONE);
        binding.popularView.setVisibility(View.VISIBLE);
        binding.nearbyView.setVisibility(View.GONE);


        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrameLayout, new PopularAllFragment()).commit();

    }

    private void bottomSheetDialog() {
        ChiragBottom_Sheet_dialog bottomSheet = new ChiragBottom_Sheet_dialog();
        bottomSheet.show(getFragmentManager(),
                "ModalBottomSheet");
    }

    private void backPressed(View sView) {
//        String exit = App.getSharedpref().getString("exit");
//        if (exit.equalsIgnoreCase("0")) {
            sView.setFocusableInTouchMode(true);

            sView.requestFocus();

            sView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        if (i == KeyEvent.KEYCODE_BACK) {

                            final Dialog dialog = new Dialog(requireActivity());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.exit_app_dialog);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            TextView yesBtn = dialog.findViewById(R.id.yesText);
                            TextView noBtn = dialog.findViewById(R.id.noText);

                            yesBtn.setOnClickListener(view1 -> {
                                dialog.dismiss();
                                requireActivity().finishAffinity();
                            });
                            noBtn.setOnClickListener(view1 -> {
                                dialog.dismiss();
                            });
                            dialog.show();
                            App.getSharedpref().saveString("exit","0");

                            return true;
                        }
                    }
                    return false;
                }
            });

//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // binding = null;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.darker_gray));
            window.setBackgroundDrawable(background);
        }
    }
}